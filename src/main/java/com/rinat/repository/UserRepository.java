package com.rinat.repository;

import com.rinat.dto.CreateChatRequest;
import com.rinat.dto.UserRegistrationRequest;
import com.rinat.model.UserRegistrationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {


    private final JdbcTemplate jdbcTemplate;

    public void save(UserRegistrationInfo registrationInfo) {

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

    // Метод проверяет есть ли такие user'ы в нашей бд
    public boolean exist(List<UUID> userIds) {

        String sql = "SELECT COUNT(*) FROM users WHERE id IN (?, ?)";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                userIds.get(0),
                userIds.get(1)
        );

        return userIds.size() == count;
    }
}
