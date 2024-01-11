package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.concurrent.ConcurrentHashMap;

public interface AccidentService {

    void save(Accident accident);

    ConcurrentHashMap<Integer, Accident> findAll();
}
