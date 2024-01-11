package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.concurrent.ConcurrentHashMap;

public interface AccidentRepository {

    void save(Accident accident);

    ConcurrentHashMap<Integer, Accident> findAll();
}
