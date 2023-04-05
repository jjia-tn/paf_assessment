package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TaskRepository taskRepo;

    @Transactional(rollbackFor = TodoException.class)
    public void upsertTask(String username, List<Task> listOfTasks) throws TodoException {

        Optional<User> optUser = userRepo.findUserByUsername(username);

        User user = new User();

        if (optUser.isEmpty()) {

            user.setUsername(username);
            userRepo.insertUser(user);
        } 
        else {

            user = optUser.get();
        }

        for (Task t : listOfTasks) {
            taskRepo.insertTask(t, user);
        }
    }
}
