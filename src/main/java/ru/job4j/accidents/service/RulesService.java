package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RulesRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RulesService {

    RulesRepository rulesRepository;

    @Transactional
    public Collection<Rule> findAllRules() {
        return (Collection<Rule>) rulesRepository.findAll();
    }

    @Transactional
    public Optional<Rule> findById(int id) {
        return rulesRepository.findById(id);
    }
}
