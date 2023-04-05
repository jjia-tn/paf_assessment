package ibf2022.paf.assessment.server.services;

import ibf2022.paf.assessment.server.models.Task;

public class TodoException extends Exception {
    
    private Task task;

    public TodoException() {

        super();
    }

    public TodoException(String msg) {

        super(msg);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
