package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMemRepository;

import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private AccidentMemRepository accidentMemRepository;

    @Override
    public void save(Accident accident) {
        accidentMemRepository.save(accident);
    }

    @Override
    public ConcurrentHashMap<Integer, Accident> findAll() {
        return accidentMemRepository.findAll();
    }
}