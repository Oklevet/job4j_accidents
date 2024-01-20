package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentService {

    void save(Accident accident, String[] ids);

    boolean update(Accident accident, String[] ids);

    Optional<Accident> getById(int id);

    List<Accident> findAll();
}
