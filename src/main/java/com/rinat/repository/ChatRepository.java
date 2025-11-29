package com.rinat.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class ChatRepository {

    private final JdbcTemplate jdbcTemplate;

    // TODO Сделать запрос который создат чат и второй запрос который соеденит наших пользователей и чат
    public UUID saveToChat() {
        String insertChatSql = """
                    INSERT INTO chats (id, createddate, deleted)
                    VALUES (gen_random_uuid(), now(), false)
                    RETURNING id
                """;
        return jdbcTemplate.queryForObject(insertChatSql, UUID.class);
    }

    public void saveToChatUsers(List<UUID> users, UUID chatId) {
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

    public boolean exist(List<UUID> userIds) {
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
