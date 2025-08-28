package com.rinat.repository;

import com.rinat.model.CreateChatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Repository
public class ChatRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveChat(CreateChatRequest request) {
        // TODO Сделать запрос который создат чат и второй запрос который соеденит наших пользователей и чат
        List<UUID> users = request.getUsersIds();

        String insertChatSql = """
                    INSERT INTO chats (id, createddate, deleted)
                    VALUES (gen_random_uuid(), now(), false)
                    RETURNING id
                """;
        UUID chatId = jdbcTemplate.queryForObject(insertChatSql, UUID.class);

        // 2) вставляем всех пользователей в chat_users
        String insertChatUsersSql = """
                    INSERT INTO chat_users (chatid, userid)
                    VALUES (?, ?)
                """;

        jdbcTemplate.batchUpdate(
                insertChatUsersSql,
                users,
                users.size(),
                (ps, userId) -> {
                    ps.setObject(1, chatId);
                    ps.setObject(2, userId);
                }
        );
    }

    public boolean exist(CreateChatRequest request) {
        List<UUID> userIds = request.getUsersIds();

        String sql = """
        SELECT EXISTS (
            SELECT 1
            FROM chat_users c1
            JOIN chat_users c2 ON c1.chatid = c2.chatid
            WHERE c1.userid = ? AND c2.userid = ?
        )
        """;

        return Boolean.TRUE.equals(
                jdbcTemplate.queryForObject(sql, Boolean.class, userIds.get(0), userIds.get(1))
        );
    }
}
