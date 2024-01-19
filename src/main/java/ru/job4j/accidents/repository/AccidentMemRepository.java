package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class AccidentMemRepository implements AccidentRepository {

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    @Override
    public void save(Accident accident, String[] ids) {

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
        return accidents.get(accident.getId()).equals(accident);
    }

    @Override
    public Optional<Accident> getById(int id) {
        return Optional.ofNullable(accidents.getOrDefault(id, null));
    }
}
