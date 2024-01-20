package ru.job4j.accidents.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
@Repository
@NoArgsConstructor
public class MemoryRulesRepository implements RulesRepository {

    private Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    @Override
    public Map<Integer, Rule> getRules() {
        return new ConcurrentHashMap<>(rules);
    }

    @Override
    public List<Rule> getListRules() {
        return new ArrayList<>(rules.values());
    }
}
