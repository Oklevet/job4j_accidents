package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RulesTemplate;

import java.util.Collection;
import java.util.Optional;

public class SimpleRulesServiceDB implements RulesServiceDB {

    RulesTemplate rulesTemplate;

    @Override
    public Collection<Rule> findAllRules() {
        return rulesTemplate.findAllRules();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return rulesTemplate.findById(id);
    }

    @Override
    public Collection<Rule> findByAcc(int id) {
        return rulesTemplate.findByAcc(id);
    }
}
