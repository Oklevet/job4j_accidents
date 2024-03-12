package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AccidentService {

    private final AccidentRepository accidentRepository;

    public void create(Accident accident, List<Integer> rulesId) {
        Set<Rule> rules = rulesId.stream()
                .map(x -> new Rule(x, "plug"))
                .collect(Collectors.toSet());
        accident.setRules(rules);
        accidentRepository.save(accident);
    }

    public List<Accident> getAll() {
        return accidentRepository.findAll();
    }

    public Optional<Accident> getById(int id) {
        return accidentRepository.findById(id);
    }

    public boolean update(Accident accident, List<Integer> rulesId) {
        Set<Rule> rules = rulesId.stream()
                .map(x -> new Rule(x, "plug"))
                .collect(Collectors.toSet());
        accident.setRules(rules);
        try {
            accidentRepository.save(accident);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}