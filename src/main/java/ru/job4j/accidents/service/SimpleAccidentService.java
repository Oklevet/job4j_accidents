package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMemRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private AccidentMemRepository accidentMemRepository;

    @Override
    public void save(Accident accident) {
        accidentMemRepository.save(accident);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentMemRepository.update(accident);
    }

    @Override
    public List<Accident> findAll() {
        return accidentMemRepository.findAll();
    }

    @Override
    public Optional<Accident> getById(int id) {
        return accidentMemRepository.getById(id);
    }
}
