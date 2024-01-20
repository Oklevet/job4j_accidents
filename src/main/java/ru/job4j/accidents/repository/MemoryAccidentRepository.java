package ru.job4j.accidents.repository;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
@NoArgsConstructor
@Primary
public class MemoryAccidentRepository implements AccidentRepository {

    private final AtomicInteger nextId = new AtomicInteger(1);

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    @Override
    public void save(Accident accident, String[] ids) {
        accident.setId(nextId.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public boolean update(Accident accident, String[] ids) {
        accidents.computeIfPresent(accident.getId(), (a, b) -> b = accident);
        return accidents.get(accident.getId()).equals(accident);
    }

    @Override
    public Optional<Accident> getById(int id) {
        return Optional.ofNullable(accidents.getOrDefault(id, null));
    }
}
