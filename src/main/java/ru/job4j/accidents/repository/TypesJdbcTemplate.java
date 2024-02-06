package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.mapper.TypesListMapper;
import ru.job4j.accidents.mapper.TypesMapper;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypesJdbcTemplate implements TypesTemplate {

    private final JdbcTemplate jdbc;

    @Override
    public Collection<AccidentType> findAllTypes() {
        return jdbc.query("select id, name from accident_type", new TypesListMapper());
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select id, name from accident_type where id = :id",
                    new TypesMapper(), id));
    }
}
