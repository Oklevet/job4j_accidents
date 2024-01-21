package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;
import java.util.List;

public interface TypesRepository {

    List<AccidentType> getListAccTypes();

    AccidentType getAccType(int id);
}
