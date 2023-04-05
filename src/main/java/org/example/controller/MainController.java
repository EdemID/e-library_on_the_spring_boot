package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e-library")
public class MainController {

    @GetMapping
    public void home() {
//        model.addAttribute("title", "Main page");
//        return "home";
    }
}
