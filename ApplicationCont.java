package com.autoshop.controller;

import com.autoshop.controller.main.Main;
import com.autoshop.model.AppUser;
import com.autoshop.model.Application;
import com.autoshop.model.Automobile;
import com.autoshop.model.enums.ApplicationStatus;
import com.autoshop.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/applications")
public class ApplicationCont extends Main {
    @GetMapping
    public String application(Model model) {
        getCurrentUserAndRole(model);
        List<Application> applications;
        AppUser user = getUser();
        if (user.getRole() == Role.ADMIN) {
            applications = applicationRepo.findAll();
        } else {
            applications = user.getApplications();
        }
        model.addAttribute("applications", applications);
        return "applications";
    }

    @GetMapping("/{id}/done")
    public String done(@PathVariable Long id) {
        Application application = applicationRepo.getReferenceById(id);
        application.setStatus(ApplicationStatus.DONE);
        applicationRepo.save(application);
        Automobile automobile = application.getAutomobile();
        automobile.setCount(automobile.getCount() - 1);
        automobileRepo.save(automobile);
        return "redirect:/applications";
    }

    @GetMapping("/{id}/reject")
    public String reject(@PathVariable Long id) {
        Application application = applicationRepo.getReferenceById(id);
        application.setStatus(ApplicationStatus.REJECT);
        applicationRepo.save(application);
        return "redirect:/applications";
    }
}