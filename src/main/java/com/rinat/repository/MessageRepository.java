package com.rinat.repository;

import com.rinat.model.MessageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(MessageInfo info) {
        String sql =
                """
                        INSERT INTO message (id, text, time, status, deleted, chatid, userid) VALUES (
                        gen_random_uuid(), ?, now(), 'delivered', false, ?, ?);
                       """;

        jdbcTemplate.update(sql, info.getMessage().getText(), info.getChatId(), info.getSenderUserId());
    }
}
