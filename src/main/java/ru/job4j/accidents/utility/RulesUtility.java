package ru.job4j.accidents.utility;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.util.HashSet;
import java.util.List;

public class RulesUtility {

    public static Accident setRulesToAccident(Accident accident, List<Integer> rulesId) {
        accident.setRules(new HashSet<>());
        rulesId.stream().forEach(x -> {
            Rule rule = new Rule();
            rule.setId(x);
            accident.getRules().add(rule);
        });
        return accident;
    }
}
