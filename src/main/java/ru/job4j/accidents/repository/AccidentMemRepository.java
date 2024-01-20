package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import java.util.*;

@Repository
public class AccidentMemRepository implements AccidentRepository {

    @Override
    public void save(Accident accident, String[] ids) {
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>();
    }

    @Override
    public boolean update(Accident accident) {
        return true;
    }

    @Override
    public Optional<Accident> getById(int id) {
        return Optional.empty();
    }
}
