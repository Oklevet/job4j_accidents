package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.mapper.RulesListByAccMapper;
import ru.job4j.accidents.mapper.RulesListMapper;
import ru.job4j.accidents.mapper.RuleMapper;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RulesJdbcTemplate implements RulesTemplate {

    private final JdbcTemplate jdbc;

    @Override
    public Collection<Rule> findAllRules() {
        try {
            return jdbc.query("select id, name from rules", new RulesListMapper());
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }

    @Override
    public Optional<Rule> findById(int id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("select id, name from rules where id = ?",
                    new RuleMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Rule> findByAcc(int id) {
        try {
            return jdbc.query("select r.id idd, r.name name from rules r, accident_rules a where a.rule_id = r.id and "
                        + " a.accidents_id = ?",
                new RulesListByAccMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }
}
