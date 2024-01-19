package ru.job4j.accidents.utility;

import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccidentTypeUtility {

    private Map<Integer, AccidentType> accTypes = new ConcurrentHashMap<>();

    {
        accTypes.put(1, new AccidentType(1, "Две машины"));
        accTypes.put(2, new AccidentType(2, "Машина и человек"));
        accTypes.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    public Map<Integer, AccidentType> getAccTypes() {
        return accTypes;
    }

    public List<AccidentType> getListAccTypes() {
        return new ArrayList<>(accTypes.values());
    }
}
