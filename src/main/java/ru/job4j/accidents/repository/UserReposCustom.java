package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class UserReposCustom implements UserRepositoryCustom {

    private final JdbcTemplate jdbc;

    @Override
    public Integer existByName(String username) {
        return this.jdbc.queryForObject(
                "select count(1) from users where username = ?",
                Integer.class,
                username
        );
    }
}
