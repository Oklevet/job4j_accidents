package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
@Primary
public class AccidentJdbcTemplate implements AccidentTemplate {
    private final JdbcTemplate jdbc;

    @Override
    public Accident save(Accident accident) {
        System.out.println("DB insert");
        System.out.println("accident = " + accident.toString());
        jdbc.update("insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId());
        return accident;
    }

    @Override
    public List<Accident> getAll() {
        RulesTemplate rulesTemplate = new RulesJdbcTemplate(jdbc);
        System.out.println("DB getAll");
        return jdbc.query("select a.id id, a.name name, a.text text, a.address address, a.type_id type_id, at.name "
                        + "type_name from accidents a, accident_type at where a.type_id = at.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    Set<Rule> rules = new HashSet<>(rulesTemplate.findByAcc(rs.getInt("id")));
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(new AccidentType(rs.getInt("type_id"),
                            rs.getString("type_name")));
                    accident.setRules(rules);
                    return accident;
                });
    }

    @Override
    public boolean update(Accident accident, String[] ids) {
        System.out.println("DB update");
        try {
            jdbc.update("""
                    UPDATE accidents
                    SET name = :name, text = :text, address = :address
                    WHERE id = :id
                    """, accident.getName(), accident.getText(), accident.getAddress(), accident.getId());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Accident> getById(int id) {
        System.out.println("DB getById");
        jdbc.query("select a.id id, a.name name, a.text text, a.address address, a.type_id type_id, at.name "
                        + "type_name from accidents a, accident_type at where a.id = :id and a.type_id = at.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(new AccidentType(rs.getInt("type_id"),
                            rs.getString("type_name")));
                    return Optional.ofNullable(accident);
                });
        return Optional.empty();
    }
}