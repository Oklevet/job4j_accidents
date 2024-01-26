package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.RulesTemplate;
import ru.job4j.accidents.repository.TypesTemplate;
import ru.job4j.accidents.service.AccidentServiceDB;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentServiceDB accidentServiceDB;

    private RulesTemplate rulesTemplate;

    private TypesTemplate typesTemplate;

    @GetMapping({"/accidents"})
    public String getIndex(Model model) {
        model.addAttribute("accidents", accidentServiceDB.findAll());
        return "accidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", typesTemplate.findAllTypes());
        model.addAttribute("rules", rulesTemplate.findAllRules());
        return "create/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidentServiceDB.create(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/editAccident")
    public String edit(@RequestParam("id") int id, Model model) {
        Optional<Accident> optAccident = accidentServiceDB.getById(id);
        if (optAccident.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        model.addAttribute("types", typesTemplate.findAllTypes());
        model.addAttribute("rules", rulesTemplate.findAllRules());
        model.addAttribute("accident", optAccident.get());
        return "create/editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        var isUpdated = accidentServiceDB.update(accident, ids);
        if (!isUpdated) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        return "redirect:/accidents";
    }
}