package com.autoshop.controller;

import com.autoshop.controller.main.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont extends Main {

    @GetMapping("/")
    public String index1() {
        return "redirect:/automobiles";
    }

    @GetMapping("/index")
    public String index2() {
        return "redirect:/automobiles";
    }
}
