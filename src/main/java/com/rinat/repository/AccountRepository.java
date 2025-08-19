package com.rinat.repository;

import com.rinat.model.UserRegistrationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveAccount(UserRegistrationInfo registrationInfo) {

        String sql = """
                INSERT INTO users(id, name, surname, nickname, dateofbirth, email, password)
                VALUES(gen_random_uuid(),?, ?, ?, ?, ?, ?)
                ON CONFLICT (nickname) DO NOTHING;
                """;

        jdbcTemplate.update(sql,
                registrationInfo.getName(),
                registrationInfo.getSurname(),
                registrationInfo.getNickname(),
                registrationInfo.getDateOfBirth(),
                registrationInfo.getEmail(),
                registrationInfo.getPassword());
    }

    public boolean exist(String nickname) {
        String sql = """
                    SELECT EXISTS (SELECT 1 FROM users WHERE nickname = ?);
                """;
        return jdbcTemplate. queryForObject(sql, Boolean.class, nickname);
    }

    public List<String> getNicknames() {
        String sql = """
                    SELECT nickname FROM users;
                """;
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
