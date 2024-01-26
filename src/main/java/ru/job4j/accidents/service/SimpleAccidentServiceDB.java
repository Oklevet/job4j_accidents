package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean update(Accident accident, String[] ids) {
        return false;
    }

    @Override
    public Optional<Accident> getById(int id) {
        return Optional.empty();
    }
}