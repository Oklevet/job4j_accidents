package ru.job4j.accidents.mapper;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RulesTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class AccidentListMapper implements RowMapper<Accident> {

    private RulesTemplate rulesTemplate;

    @Override
    public Accident mapRow(ResultSet rs, int rowNum) throws SQLException {
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
    }
}
