package com.rinat.repository;

import com.rinat.dto.Statistic;
import com.rinat.dto.UserInfo;
import com.rinat.dto.UserStatisticInfo;
import com.rinat.model.MessageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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

    public List<UserStatisticInfo> getUsersStatistic() {
        String sql = """
                    SELECT userid, COUNT(*) AS msg_count FROM message GROUP BY userid;
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            UUID userId = rs.getObject("userid", UUID.class);


            UserInfo user = new UserInfo();
            user.setId(userId.toString());

            Statistic statistic = new Statistic();
            statistic.setSentMessageCount(rs.getLong("msg_count"));

            UserStatisticInfo userStatisticInfo = new UserStatisticInfo();
            userStatisticInfo.setStatistic(statistic);
            userStatisticInfo.setUser(user);

            return userStatisticInfo;
        });
    }
}
