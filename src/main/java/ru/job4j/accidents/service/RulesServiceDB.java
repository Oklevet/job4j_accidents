package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RulesServiceDB {

    Collection<Rule> findAllRules();

    Optional<Rule> findById(int id);

    Collection<Rule> findByAcc(int id);
}
