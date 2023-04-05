package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.Utils;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.services.TodoException;

// TODO: Task 3

@Repository
public class UserRepository {

    public static final String SQL_FIND_USER_BY_USERNAME = """
            select * from user where username = ?
            """;

    public static final String SQL_INSERT_USER = """
            insert into user (user_id, username, name) values (?, ?, ?);
            """;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> findUserByUsername(String username) {

        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_USER_BY_USERNAME, username);

        if (!rs.next()) {

            return Optional.empty();
        }
        
        return Optional.of(Utils.toUser(rs));
    }

    public String insertUser(User user) throws TodoException {

        String userId = UUID.randomUUID().toString().substring(0, 8);

        int inserted = jdbcTemplate.update(SQL_INSERT_USER, userId, user.getUsername(), user.getName());

        if (inserted <= 0) {

            throw new TodoException("failed to insert user into database");
        }
        
        return userId;
    }

}
