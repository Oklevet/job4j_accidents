package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository {

    void save(Accident accident, String[] ids);

    boolean update(Accident accident);

    Optional<Accident> getById(int id);

    List<Accident> findAll();
}
