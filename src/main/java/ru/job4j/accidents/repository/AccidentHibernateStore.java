package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.*;

@Repository
@AllArgsConstructor
@Primary
public class AccidentHibernateStore implements AccidentStore {

    private final CrudStore crudStore;

    @Override
    public Accident save(Accident accident) {
        return crudStore.run(session ->
                session.save(accident)) ? accident : null;
    }

    @Override
    public List<Accident> getAll() {
        return crudStore.query("from Accident x join fetch x.type", Accident.class);
    }

    @Override
    public boolean update(Accident accident) {
        return crudStore.run(session -> session.merge(accident));
    }

    @Override
    public Optional<Accident> getById(int id) {
        return crudStore.optional("from Accident x where x.id = :id", Accident.class,
                Map.of("id", id));
    }
}