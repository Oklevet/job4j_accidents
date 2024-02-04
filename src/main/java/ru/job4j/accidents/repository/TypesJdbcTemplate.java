package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.mapper.TypesListMapper;
import ru.job4j.accidents.mapper.TypesMapper;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypesJdbcTemplate implements TypesTemplate {

    private final JdbcTemplate jdbc;

    @Override
    public Collection<AccidentType> findAllTypes() {
        try {
            return jdbc.query("select id, name from accident_type", new TypesListMapper());
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("select id, name from accident_type where id = :id",
                    new TypesMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
