package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RulesJdbcTemplate implements RulesTemplate {

    private final JdbcTemplate jdbc;

    @Override
    public Collection<Rule> findAllRules() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    @Override
    public Optional<Rule> findById(int id) {
        jdbc.query("select id, name from rules where id = :?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return Optional.ofNullable(rule);
                }, id);
        return Optional.empty();
    }

    @Override
    public Collection<Rule> findByAcc(int id) {
        return jdbc.query("select r.id idd, r.name name from rules r, accident_rules a where a.rule_id = r.id and "
                        + " a.accidents_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("idd"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }
}
