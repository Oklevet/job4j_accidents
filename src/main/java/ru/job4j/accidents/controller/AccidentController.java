package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping({"/accidents"})
    public String getIndex(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "create/createAccident";
    }

    @PostMapping({"/saveAccident", "/updateAccident"})
    public String save(@ModelAttribute Accident accident) {
        accidentService.save(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        Accident accident = accidentService.getById(id);
        if (accident == null) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        model.addAttribute("task", accident);
        return "create/editAccident";
    }

    @GetMapping("/editAccident")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.getById(id));
        return "create/editAccident";
    }
}