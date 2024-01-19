package ru.job4j.accidents.utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@NoArgsConstructor
public class RulesUtility {

    private Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    public Map<Integer, Rule> getRules() {
        return rules;
    }

    public List<Rule> getListRules() {
        return new ArrayList<>(rules.values());
    }
}
