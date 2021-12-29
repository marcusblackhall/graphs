package com.iamatum.iamatumgraphs.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraphController {

    @RequestMapping("/")
    public String homePage(Model model){
       return showCards(model);
    }

    @GetMapping("/home")
    public String showCards(Model model) {
        model.addAttribute("left", "card");
        return "index";
    }

    @GetMapping("/lcps")
    public String showLcpsData(Model model) {
        model.addAttribute("left", "lcps");
        return "index";
    }

    @GetMapping("/lcpstable")
    public String showLcpsTable(Model model) {
        model.addAttribute("left", "lcpstable");
        return "index";
    }





}
