package com.autoshop.controller;

import com.autoshop.controller.main.Main;
import com.autoshop.model.Application;
import com.autoshop.model.Automobile;
import com.autoshop.model.enums.EngineType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/automobiles")
public class AutomobileCont extends Main {
    @GetMapping
    public String automobiles(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("automobiles", automobileRepo.findAll());
        return "automobiles";
    }

    @GetMapping("/{id}")
    public String automobile(Model model, @PathVariable Long id) {
        getCurrentUserAndRole(model);
        model.addAttribute("automobile", automobileRepo.getReferenceById(id));
        return "automobile";
    }

    @GetMapping("/{id}/application")
    public String application(@PathVariable Long id) {
        applicationRepo.save(new Application(automobileRepo.getReferenceById(id),getUser()));
        return "redirect:/automobiles/{id}";
    }

    @GetMapping("/add")
    public String add(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("engineTypes", EngineType.values());
        model.addAttribute("carModels", carModelRepo.findAll());
        return "automobile_add";
    }

    @PostMapping("/add")
    public String add(Model model, @RequestParam String name, @RequestParam MultipartFile photo, @RequestParam float price, @RequestParam String origin, @RequestParam int count, @RequestParam EngineType engineType, @RequestParam Long carModelId) {
        String resultPhoto = "";
        try {
            if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                resultPhoto = "automobile/" + uuidFile + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadImg + "/" + resultPhoto));
            }
        } catch (IOException e) {
            model.addAttribute("message", "Некорректные данные!");
            getCurrentUserAndRole(model);
            model.addAttribute("engineTypes", EngineType.values());
            model.addAttribute("carModels", carModelRepo.findAll());
            return "automobile_add";
        }

        Automobile automobile = automobileRepo.save(new Automobile(name, resultPhoto, price, origin, count, engineType, carModelRepo.getReferenceById(carModelId)));

        return "redirect:/automobiles/" + automobile.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable Long id) {
        getCurrentUserAndRole(model);
        model.addAttribute("engineTypes", EngineType.values());
        model.addAttribute("carModels", carModelRepo.findAll());
        model.addAttribute("automobile", automobileRepo.getReferenceById(id));
        return "automobile_edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(Model model, @PathVariable Long id, @RequestParam String name, @RequestParam MultipartFile photo, @RequestParam float price, @RequestParam String origin, @RequestParam int count, @RequestParam EngineType engineType, @RequestParam Long carModelId) {
        Automobile automobile = automobileRepo.getReferenceById(id);
        automobile.set(name, price, origin, count, engineType, carModelRepo.getReferenceById(carModelId));
        try {
            if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String resultPhoto = "automobile/" + uuidFile + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadImg + "/" + resultPhoto));
                automobile.setPhoto(resultPhoto);
            }
        } catch (IOException e) {
            getCurrentUserAndRole(model);
            model.addAttribute("message", "Некорректные данные!");
            model.addAttribute("engineTypes", EngineType.values());
            model.addAttribute("carModels", carModelRepo.findAll());
            model.addAttribute("automobile", automobile);
            return "automobile_edit";
        }

        automobileRepo.save(automobile);

        return "redirect:/automobiles/" + automobile.getId();
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        automobileRepo.deleteById(id);
        return "redirect:/automobiles";
    }
}