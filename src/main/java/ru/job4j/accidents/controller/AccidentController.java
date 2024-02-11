package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RulesStore;
import ru.job4j.accidents.repository.TypesStore;
import ru.job4j.accidents.service.AccidentServiceDB;
import ru.job4j.accidents.service.RulesServiceDB;
import ru.job4j.accidents.service.TypesServiceDB;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentServiceDB accidentServiceDB;

    private TypesServiceDB typesServiceDB;

    private RulesServiceDB rulesServiceDB;

    @GetMapping({"/accidents"})
    public String getIndex(Model model) {
        model.addAttribute("accidents", accidentServiceDB.findAll());
        return "accidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", typesServiceDB.findAllTypes());
        model.addAttribute("rules", rulesServiceDB.findAllRules());
        return "create/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req,
                       Model model, @RequestParam(name = "rIds") List<Integer> rulesId) {
        try {
            accidentServiceDB.create(accident, rulesId);
            return "redirect:/accidents";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/editAccident")
    public String edit(@RequestParam("id") int id, Model model) {
        Optional<Accident> optAccident = accidentServiceDB.getById(id);
        if (optAccident.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        model.addAttribute("types", typesServiceDB.findAllTypes());
        model.addAttribute("rules", rulesServiceDB.findAllRules());
        model.addAttribute("accident", optAccident.get());
        return "create/editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model, HttpServletRequest req,
                         @RequestParam(name = "rIds") List<Integer> rulesId) {
        var isUpdated = accidentServiceDB.update(accident, rulesId);
        if (!isUpdated) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        return "redirect:/accidents";
    }
}