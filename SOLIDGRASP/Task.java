import java.util.Date;

public class Task {
    private String title;
    private String description;
    private Date dueDate;
    private TaskStatus status;
    private TaskPriority priority;

    public Task(String title, String description, Date dueDate, TaskStatus status, TaskPriority priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
    }


    public void markAsComplete() {
        this.status = TaskStatus.COMPLETED;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }


}