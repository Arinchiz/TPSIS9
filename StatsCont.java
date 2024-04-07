package com.autoshop.controller;

import com.autoshop.controller.main.Main;
import com.autoshop.model.Automobile;
import com.autoshop.model.CarModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsCont extends Main {

    @GetMapping
    public String stats(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("automobiles", automobileRepo.findAll());
        return "stats";
    }

    @GetMapping("/income")
    public String income(Model model) {
        getCurrentUserAndRole(model);

        List<Automobile> automobiles = automobileRepo.findAll();

        automobiles.sort(Comparator.comparing(Automobile::getIncomePrice));
        Collections.reverse(automobiles);

        String[] top5String = new String[5];
        float[] top5Float = new float[5];

        for (int i = 0; i < automobiles.size(); i++) {
            if (i == 5) break;
            top5String[i] = automobiles.get(i).getName();
            top5Float[i] = automobiles.get(i).getIncomePrice();
        }

        model.addAttribute("top5String", top5String);
        model.addAttribute("top5Float", top5Float);

        return "stat_income";
    }

    @GetMapping("/count")
    public String count(Model model) {
        getCurrentUserAndRole(model);

        List<Automobile> automobiles = automobileRepo.findAll();

        automobiles.sort(Comparator.comparing(Automobile::getCount));
        Collections.reverse(automobiles);

        String[] countString = new String[5];
        int[] countInt = new int[5];

        for (int i = 0; i < automobiles.size(); i++) {
            if (i == 5) break;
            countString[i] = automobiles.get(i).getName();
            countInt[i] = automobiles.get(i).getCount();
        }

        model.addAttribute("countString", countString);
        model.addAttribute("countInt", countInt);

        return "stat_count";
    }

    @GetMapping("/model")
    public String model(Model model) {
        getCurrentUserAndRole(model);

        List<CarModel> models = carModelRepo.findAll();

        String[] modString = new String[models.size()];
        float[] modFloat = new float[models.size()];

        for (int i = 0; i < models.size(); i++) {
            modString[i] = models.get(i).getName();
            modFloat[i] = models.get(i).getIncomePrice();
        }

        model.addAttribute("modString", modString);
        model.addAttribute("modFloat", modFloat);

        return "stat_model";
    }
}
