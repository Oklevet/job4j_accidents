package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.utility.AccidentTypeUtility;
import ru.job4j.accidents.utility.RulesUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
    public String viewCreateAccident(Model model) {
        AccidentTypeUtility accidentTypeUtility = new AccidentTypeUtility();
        model.addAttribute("types", accidentTypeUtility.getListAccTypes());

        RulesUtility rulesUtility = new RulesUtility();
        model.addAttribute("rules", rulesUtility.getListRules());
        return "create/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidentService.save(accident, ids);
        return "redirect:/accidents";
    }

    @GetMapping("/editAccident")
    public String edit(@RequestParam("id") int id, Model model) {
        Optional<Accident> optAccident = accidentService.getById(id);
        if (optAccident.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        AccidentTypeUtility accidentTypeUtility = new AccidentTypeUtility();
        model.addAttribute("types", accidentTypeUtility.getListAccTypes());

        RulesUtility rulesUtility = new RulesUtility();
        model.addAttribute("rules", rulesUtility.getListRules());
        model.addAttribute("accident", optAccident.get());
        return "create/editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        var isUpdated = accidentService.update(accident, ids);
        if (!isUpdated) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        return "redirect:/accidents";
    }
}