package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMemRepository;
import ru.job4j.accidents.utility.AccidentTypeUtility;
import ru.job4j.accidents.utility.RulesUtility;

import java.util.*;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private AccidentMemRepository accidentMemRepository;

    @Override
    public void save(Accident accident, String[] ids) {
        AccidentTypeUtility accTypeUtil = new AccidentTypeUtility();
        RulesUtility rulesUtility = new RulesUtility();
        Set<Rule> accRules = new HashSet<>();

        accident.setType(accTypeUtil.getAccTypes().get(accident.getType().getId()));

        Arrays.stream(ids).forEach(x -> accRules.add(rulesUtility.getRules().get(Integer.parseInt(x))));
        accident.setRules(accRules);
        accidentMemRepository.save(accident, ids);
    }

    @Override
    public boolean update(Accident accident) {
        AccidentTypeUtility accTypeUtil = new AccidentTypeUtility();

        accident.setType(accTypeUtil.getAccTypes().get(accident.getType()));
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
