package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RulesStore;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleRulesServiceDB implements RulesServiceDB {

    RulesStore rulesStore;

    @Override
    public Collection<Rule> findAllRules() {
        return rulesStore.findAllRules();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return rulesStore.findById(id);
    }
}
