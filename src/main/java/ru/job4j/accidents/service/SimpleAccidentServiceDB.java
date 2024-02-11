package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentHibernateStore;
import ru.job4j.accidents.utility.RulesUtility;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleAccidentServiceDB implements AccidentServiceDB {
    private final AccidentHibernateStore accidentsRepostiory;

    public Accident create(Accident accident, List<Integer> rulesId) {
        RulesUtility.setRulesToAccident(accident, rulesId);
        return accidentsRepostiory.save(accident);
    }

    public List<Accident> findAll() {
        return accidentsRepostiory.getAll();
    }

    @Override
    public boolean update(Accident accident, List<Integer> rulesId) {
        RulesUtility.setRulesToAccident(accident, rulesId);
        return accidentsRepostiory.update(accident);
    }

    @Override
    public Optional<Accident> getById(int id) {
        return accidentsRepostiory.getById(id);
    }
}