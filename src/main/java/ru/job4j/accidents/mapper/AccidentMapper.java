package ru.job4j.accidents.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentMapper implements RowMapper<Accident> {

    @Override
    public Accident mapRow(ResultSet rs, int rowNum) throws SQLException {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setType(new AccidentType(rs.getInt("type_id"),
                rs.getString("type_name")));
        return accident;
    }
}
