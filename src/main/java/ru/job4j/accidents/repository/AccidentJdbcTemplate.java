package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentTemplate {
    private final JdbcTemplate jdbc;

    @Override
    public Accident save(Accident accident) {
        jdbc.update("insert into accidents (name) values (?)",
                accident.getName());
        return accident;
    }

    @Override
    public List<Accident> getAll() {
        return jdbc.query("select id, name from accidents",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }
}