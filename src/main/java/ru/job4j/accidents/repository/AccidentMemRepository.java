package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class AccidentMemRepository implements AccidentRepository {

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private Map<Integer, AccidentType> accTypes = new ConcurrentHashMap<>();
    {
        accTypes.put(1, new AccidentType(1, "Две машины"));
        accTypes.put(2, new AccidentType(2, "Машина и человек"));
        accTypes.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    private Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    @Override
    public void save(Accident accident, String[] ids) {
        accident.setType(accTypes.get(accident.getType().getId()));
        Set<Rule> accRules = new HashSet<>();

        Arrays.stream(ids).forEach(x -> accRules.add(rules.get(Integer.parseInt(x))));
        accident.setRules(accRules);

        int id = accidents.keySet().stream().max(Comparator.naturalOrder()).orElse(0);
        accident.setId(++id);
        accidents.put(id, accident);
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public boolean update(Accident accident) {
        accidents.computeIfPresent(accident.getId(), (a, b) -> b = accident);
        accident.setType(accTypes.get(accident.getType()));
        return accidents.get(accident.getId()).equals(accident);
    }

    @Override
    public Optional<Accident> getById(int id) {
        return Optional.ofNullable(accidents.getOrDefault(id, null));
    }
}
