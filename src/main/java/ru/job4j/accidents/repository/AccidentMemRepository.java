package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class AccidentMemRepository implements AccidentRepository {

    private ConcurrentHashMap<Integer, Accident> accidents = new ConcurrentHashMap<>();

    @Override
    public void save(Accident accident) {
        accidents.put(1, new Accident(1, "ДТП", "Участие 2-х машин", "Ул. Егой-то, 13"));
        accidents.put(2, new Accident(2, "ДТП, уголовка", "Сбит пешеход", "Ул. Егой-то, 5"));
        accidents.put(3, new Accident(3, "ДТП", "Участие 3-х машин", "Ул. Тогой-то, 45"));
        accidents.put(4, new Accident(4, "ДТП, административка", "Авария, драка участников дтп", "Ул. Тогой-то, 87"));
    }

    @Override
    public ConcurrentHashMap<Integer, Accident> findAll() {
        return accidents;
    }
}
