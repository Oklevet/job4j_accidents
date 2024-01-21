package ru.job4j.accidents.repository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
@Repository
@NoArgsConstructor
public class MemoryTypesRepository implements TypesRepository {

    private Map<Integer, AccidentType> accTypes = new ConcurrentHashMap<>();

    {
        accTypes.put(1, new AccidentType(1, "Две машины"));
        accTypes.put(2, new AccidentType(2, "Машина и человек"));
        accTypes.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    @Override
    public AccidentType getAccType(int id) {
        return accTypes.get(id);
    }

    @Override
    public List<AccidentType> getListAccTypes() {
        return new ArrayList<>(accTypes.values());
    }
}
