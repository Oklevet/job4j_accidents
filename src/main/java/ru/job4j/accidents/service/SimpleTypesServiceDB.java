package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypesTemplate;

import java.util.Collection;
import java.util.Optional;

public class SimpleTypesServiceDB implements TypesServiceDB {

    TypesTemplate typesTemplate;

    @Override
    public Collection<AccidentType> findAllTypes() {
        return typesTemplate.findAllTypes();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return typesTemplate.findById(id);
    }
}
