package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RulesHibernateStore implements RulesStore {

    private final CrudStore crudStore;

    @Override
    public Collection<Rule> findAllRules() {
        return crudStore.query("from Rule", Rule.class);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return crudStore.optional("from Rule x where x.id = :id", Rule.class, Map.of("id", id));
    }
}
