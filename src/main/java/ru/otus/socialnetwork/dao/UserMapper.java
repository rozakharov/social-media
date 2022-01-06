package ru.otus.socialnetwork.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.socialnetwork.dto.Sex;
import ru.otus.socialnetwork.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    String sex = rs.getString(User.Fields.sex);
    return User.builder()
        .id(rs.getLong(User.Fields.id))
        .email(rs.getString(User.Fields.email))
        .password(rs.getString(User.Fields.password))
        .firstName(rs.getString("first_name"))
        .lastName(rs.getString("last_name"))
        .age(rs.getInt(User.Fields.age))
        .sex(sex == null ? null : Sex.valueOf(sex))
        .hobby(rs.getString(User.Fields.hobby))
        .city(rs.getString(User.Fields.city))
        .build();
  }
}
