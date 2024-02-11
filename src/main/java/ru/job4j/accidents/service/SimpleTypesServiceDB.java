package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypesStore;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTypesServiceDB implements TypesServiceDB {

    TypesStore typesStore;

    @Override
    public Collection<AccidentType> findAllTypes() {
        return typesStore.findAllTypes();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return typesStore.findById(id);
    }
}
