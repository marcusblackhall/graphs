package com.iamatum.iamatumgraphs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraphController {

    @GetMapping("/")
    public String homePage(Model model){

        model.addAttribute("heroku","Heroku");
        return "index";
    }
}
