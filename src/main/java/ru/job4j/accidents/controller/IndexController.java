package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.SimpleAccidentService;

import java.util.concurrent.ConcurrentHashMap;

@Controller
@AllArgsConstructor
public class IndexController {

    private SimpleAccidentService accidentService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        accidentService.save(new Accident());
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
