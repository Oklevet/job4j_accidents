package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;

    @Transactional
    public void create(Accident accident, List<Integer> rulesId) {
        Set<Rule> rules = rulesId.stream()
                .map(x -> new Rule(x, "plug"))
                .collect(Collectors.toSet());
        accident.setRules(rules);
        accidentRepository.save(accident);
    }

    @Transactional
    public List<Accident> getAll() {
        return (List<Accident>) accidentRepository.findAll();
    }

    @Transactional
    public Optional<Accident> getById(int id) {
        return accidentRepository.findById(id);
    }

    @Transactional
    public boolean update(Accident accident, List<Integer> rulesId) {
        Set<Rule> rules = rulesId.stream()
                .map(x -> new Rule(x, "plug"))
                .collect(Collectors.toSet());
        accident.setRules(rules);
        try {
            accidentRepository.save(accident);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}