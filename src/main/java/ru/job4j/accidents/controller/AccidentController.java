package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.RulesService;
import ru.job4j.accidents.service.TypesService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;

    private TypesService typesService;

    private RulesService rulesService;

    @GetMapping({"/accidents"})
    public String getIndex(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidentService.getAll());
        return "accidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("types", typesService.findAllTypes());
        model.addAttribute("rules", rulesService.findAllRules());
        return "create/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req,
                       Model model, @RequestParam(name = "rIds") List<Integer> rulesId) {
        try {
            accidentService.create(accident, rulesId);
            return "redirect:/accidents";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/editAccident")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Optional<Accident> optAccident = accidentService.getById(id);
        if (optAccident.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        model.addAttribute("types", typesService.findAllTypes());
        model.addAttribute("rules", rulesService.findAllRules());
        model.addAttribute("accident", optAccident.get());
        return "create/editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model, HttpServletRequest req,
                         @RequestParam(name = "rIds") List<Integer> rulesId) {
        var isUpdated = accidentService.update(accident, rulesId);
        if (!isUpdated) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        return "redirect:/accidents";
    }
}