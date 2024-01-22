package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RulesRepository {

    Set<Rule> getSetRule(String[] ids);

    List<Rule> getListRules();
}
