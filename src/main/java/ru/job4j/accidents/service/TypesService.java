package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypesRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TypesService {

    TypesRepository typesRepository;

    public Collection<AccidentType> findAllTypes() {
        return (Collection<AccidentType>) typesRepository.findAll();
    }

    public Optional<AccidentType> findById(int id) {
        return typesRepository.findById(id);
    }
}
