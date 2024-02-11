package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypesHibernateStore implements TypesStore {

    private final CrudStore crudStore;

    @Override
    public Collection<AccidentType> findAllTypes() {
        return crudStore.query("from AccidentType", AccidentType.class);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudStore.optional("from AccidentType x where x.id = :id", AccidentType.class,
                Map.of("id", id));
    }
}
