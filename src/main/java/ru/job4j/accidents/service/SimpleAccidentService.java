package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMemRepository;
import ru.job4j.accidents.repository.AccidentRepository;
import ru.job4j.accidents.utility.AccidentTypeUtility;
import ru.job4j.accidents.utility.RulesUtility;

import java.util.*;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private AccidentRepository accidentRepository;

    @Override
    public void save(Accident accident, String[] ids) {
        AccidentTypeUtility accTypeUtil = new AccidentTypeUtility();
        RulesUtility rulesUtility = new RulesUtility();
        Set<Rule> accRules = new HashSet<>();

        accident.setType(accTypeUtil.getAccTypes().get(accident.getType().getId()));

        Arrays.stream(ids).forEach(x -> accRules.add(rulesUtility.getRules().get(Integer.parseInt(x))));
        accident.setRules(accRules);

        accidentRepository.save(accident, ids);
    }

    @Override
    public boolean update(Accident accident, String[] ids) {
        AccidentTypeUtility accTypeUtil = new AccidentTypeUtility();
        RulesUtility rulesUtility = new RulesUtility();
        Set<Rule> accRules = new HashSet<>();

        accident.setType(accTypeUtil.getAccTypes().get(accident.getType().getId()));

        Arrays.stream(ids).forEach(x -> accRules.add(rulesUtility.getRules().get(Integer.parseInt(x))));
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
