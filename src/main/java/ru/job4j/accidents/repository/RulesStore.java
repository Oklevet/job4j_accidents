package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;

public interface RulesStore {

    Collection<Rule> findAllRules();

    Optional<Rule> findById(int id);
}
