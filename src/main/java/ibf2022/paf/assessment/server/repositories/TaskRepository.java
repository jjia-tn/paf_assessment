package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.services.TodoException;

// TODO: Task 6

@Repository
public class TaskRepository {

    public static final String SQL_INSERT_TASK = """
            insert into task (description, priority, due_date, user_id) values (?, ?, ?, ?);
            """;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertTask(Task task, User user) throws TodoException {

        int inserted = jdbcTemplate.update(SQL_INSERT_TASK, task.getDescription(), task.getPriority(), task.getDueDate(), user.getUserId());

        if (inserted <= 0)
            throw new TodoException("failed to insert task into database");
    }
}
