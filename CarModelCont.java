package com.autoshop.controller;

import com.autoshop.controller.main.Main;
import com.autoshop.model.CarModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/models")
public class CarModelCont extends Main {
    @GetMapping
    public String models(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("models", carModelRepo.findAll());
        return "car_models";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name) {
        carModelRepo.save(new CarModel(name));
        return "redirect:/models";
    }

    @PostMapping("/{id}/edit")
    public String edit(@RequestParam String name, @PathVariable Long id) {
        CarModel carModel = carModelRepo.getReferenceById(id);
        carModel.setName(name);
        carModelRepo.save(carModel);
        return "redirect:/models";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        carModelRepo.deleteById(id);
        return "redirect:/models";
    }
}