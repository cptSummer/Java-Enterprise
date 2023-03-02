package com.example.hometask2.service;

import com.example.hometask2.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RequiredArgsConstructor
@Component
public class UserDaoImpl implements UserDao{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public static final RowMapper<User> USER_ROW_MAPPER = (rs, i)->User.builder()
            .id(rs.getLong("id"))
            .firstName(rs.getString("firstname"))
            .phone(rs.getString("phone"))
            .email(rs.getString("email"))
            .build();

    private static final String GET_ALL_USERS = "SELECT id, firstname, phone, email FROM users";

    private static final String GET_BY_NAME = "SELECT id, firstname, phone, email FROM users " +
            "WHERE firstname =:firstname";

    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id =:id";

    private static final String INSERT = "INSERT INTO users (firstname, phone, email) " +
            "VALUES (:firstname,:phone,:email)";


    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(
                GET_ALL_USERS,
                USER_ROW_MAPPER
        );
    }

    @Override
    public List<User> getByName(String name) {
        return jdbcTemplate.query(
                GET_BY_NAME,
                new MapSqlParameterSource()
                        .addValue("firstname",name),
                USER_ROW_MAPPER
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(
                DELETE_BY_ID,
                new MapSqlParameterSource("id",id)
        );
    }

    @Override
    public void createUser(User user) {
        jdbcTemplate.update(
                INSERT,
                new MapSqlParameterSource()
                        .addValue("firstname",user.getFirstName())
                        .addValue("phone",user.getPhone())
                        .addValue("email",user.getEmail())
        );
    }
}
