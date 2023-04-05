package ibf2022.paf.assessment.server.controllers;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoException;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
@RequestMapping
public class TasksController {

    @Autowired
    private TodoService todoSvc;

    @PostMapping(path="/task")
    public ModelAndView saveTask(@RequestBody MultiValueMap<String, String> form, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        List<Task> listOfTasks = new LinkedList<>();

        int taskCount = 0;

        String username = form.getFirst("username");

        for (int i = 0; i < form.size(); i++) {

            if (null != form.getFirst("description-" + i)) {

                Task task = new Task();

                task.setDescription(form.getFirst("description-" + i));
                task.setPriority(Integer.parseInt(form.getFirst("priority-" + i)));
                task.setDueDate(Date.valueOf(form.getFirst("dueDate-" + i)));

                listOfTasks.add(task);
                taskCount++;
            
            }
        }

        try {

            todoSvc.upsertTask(username, listOfTasks);
            model.addAttribute("username", username);
            model.addAttribute("taskCount", taskCount);

            modelAndView.setViewName("result");
            modelAndView.setStatus(HttpStatusCode.valueOf(200));

            return modelAndView;

        } catch (TodoException ex) {

            modelAndView.setViewName("error");
            modelAndView.setStatus(HttpStatusCode.valueOf(500));

            return modelAndView;
        }
    }
    
}
