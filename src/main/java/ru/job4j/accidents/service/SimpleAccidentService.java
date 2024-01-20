package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;
import ru.job4j.accidents.repository.RulesRepository;
import ru.job4j.accidents.repository.MemoryTypesRepository;
import ru.job4j.accidents.repository.TypesRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private AccidentRepository accidentRepository;

    private RulesRepository rulesRepository;

    private TypesRepository typesRepository;

    @Override
    public void save(Accident accident, String[] ids) {
        Set<Rule> accRules = new HashSet<>();

        accident.setType(typesRepository.getAccTypes().get(accident.getType().getId()));

        Arrays.stream(ids).forEach(x -> accRules.add(rulesRepository.getRules().get(Integer.parseInt(x))));
        accident.setRules(accRules);

        accidentRepository.save(accident, ids);
    }

    @Override
    public boolean update(Accident accident, String[] ids) {
        MemoryTypesRepository accTypeUtil = new MemoryTypesRepository();
        Set<Rule> accRules = new HashSet<>();

        accident.setType(typesRepository.getAccTypes().get(accident.getType().getId()));

        Arrays.stream(ids).forEach(x -> accRules.add(rulesRepository.getRules().get(Integer.parseInt(x))));
        accident.setRules(accRules);

        return accidentRepository.update(accident, ids);
    }

    @Override
    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }

    @Override
    public Optional<Accident> getById(int id) {
        return accidentRepository.getById(id);
    }
}
