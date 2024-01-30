package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleAccidentServiceDB implements AccidentServiceDB {
    private final AccidentJdbcTemplate accidentsRepostiory;

    public void create(Accident accident, String[] ids) {
        Set<Rule> rules = Arrays.stream(ids)
                                    .map(x -> new Rule(Integer.parseInt(x), "plug"))
                                    .collect(Collectors.toSet());
        accident.setRules(rules);
        accidentsRepostiory.save(accident, ids);
    }

    public List<Accident> findAll() {
        return accidentsRepostiory.getAll();
    }

    @Override
    public boolean update(Accident accident, String[] ids) {
        Set<Rule> rules = Arrays.stream(ids)
                .map(x -> new Rule(Integer.parseInt(x), "plug"))
                .collect(Collectors.toSet());
        accident.setRules(rules);
        return accidentsRepostiory.update(accident, ids);
    }

    @Override
    public Optional<Accident> getById(int id) {
        return accidentsRepostiory.getById(id);
    }
}