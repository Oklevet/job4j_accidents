package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleAccidentServiceDB implements AccidentServiceDB {
    private final AccidentJdbcTemplate accidentsRepostiory;

    public void create(Accident accident) {
        accidentsRepostiory.save(accident);
    }

    public List<Accident> findAll() {
        return accidentsRepostiory.getAll();
    }
}