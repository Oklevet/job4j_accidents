package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.SimpleAccidentServiceDB;

@Controller
@AllArgsConstructor
public class IndexController {

    private SimpleAccidentServiceDB accidentService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
