package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentServiceDB {

    void create(Accident accident, String[] ids);

    List<Accident> findAll();

    boolean update(Accident accident, String[] ids);

    Optional<Accident> getById(int id);
}
