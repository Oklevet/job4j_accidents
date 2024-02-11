package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentStore {

    Accident save(Accident accident);

    List<Accident> getAll();

    boolean update(Accident accident);

    Optional<Accident> getById(int id);
}
