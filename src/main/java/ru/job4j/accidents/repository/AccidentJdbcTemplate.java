package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.mapper.AccidentListMapper;
import ru.job4j.accidents.mapper.AccidentMapper;
import ru.job4j.accidents.mapper.AddressTypeIDMapper;
import ru.job4j.accidents.model.Accident;

import java.util.*;

@Repository
@AllArgsConstructor
@Primary
public class AccidentJdbcTemplate implements AccidentTemplate {
    private final JdbcTemplate jdbc;

    @Override
    public Accident save(Accident accident, String[] ids) {
        jdbc.update("insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId());

        Arrays.stream(ids).forEach(x -> {
                jdbc.update("insert into accident_rules (accidents_id, rule_id) values (?, ?)",
                        AccidentJdbcTemplate.getIdByNameTxtAddressType(accident, jdbc).get(),
                        Integer.parseInt(x));
        });
        return accident;
    }

    public static void deleteRules(int accId, JdbcTemplate jdbc) {
        jdbc.update("delete from accident_rules where accidents_id = ?", accId);
    }

    @Override
    public List<Accident> getAll() {
        RulesTemplate rulesTemplate = new RulesJdbcTemplate(jdbc);
        return jdbc.query("select a.id id, a.name name, a.text text, a.address address, a.type_id type_id, at.name "
                            + "type_name from accidents a, accident_type at where a.type_id = at.id",
                    new AccidentListMapper(rulesTemplate));
    }

    @Override
    public boolean update(Accident accident, String[] ids) {
        try {
            jdbc.update("""
                    UPDATE accidents
                    SET name = ?, text = ?, address = ?, type_id = ?
                    WHERE id = ?
                    """, accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId(),
                    accident.getId());

            AccidentJdbcTemplate.deleteRules(accident.getId(), jdbc);

            Arrays.stream(ids).forEach(x -> {
                jdbc.update("insert into accident_rules (accidents_id, rule_id) values (?, ?)",
                        accident.getId(),
                        Integer.parseInt(x));
            });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Accident> getById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select a.id id, a.name name, a.text text, a.address address, a.type_id type_id, at.name "
                            + "type_name from accidents a, accident_type at where a.id = ? and a.type_id = at.id",
                    new AccidentMapper(), id));
    }

    public static Optional<Integer> getIdByNameTxtAddressType(Accident accident, JdbcTemplate jdbc) {
        return Optional.ofNullable(jdbc.queryForObject("select a.id idd "
                            + "from accidents a where a.name = ? and a.text = ? and a.address = ? and a.type_id = ? ",
                    new AddressTypeIDMapper(), accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId()));
    }
}