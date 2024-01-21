package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;
import java.util.List;
import java.util.Map;

public interface RulesRepository {

    Rule getRule(int id);

    List<Rule> getListRules();
}
