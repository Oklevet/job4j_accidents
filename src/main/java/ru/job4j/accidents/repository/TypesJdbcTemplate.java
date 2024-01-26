package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypesJdbcTemplate implements TypesTemplate {

    private final JdbcTemplate jdbc;

    @Override
    public Collection<AccidentType> findAllTypes() {
        System.out.println("Type DB findAllTypes");
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        System.out.println("Type DB findById");
        jdbc.query("select id, name from accident_type where id = :id",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return Optional.ofNullable(accidentType);
                });
        return Optional.empty();
    }
}
