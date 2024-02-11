package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentServiceDB {

    Accident create(Accident accident, List<Integer> rulesId);

    List<Accident> findAll();

    boolean update(Accident accident, List<Integer> rulesId);

    Optional<Accident> getById(int id);
}
