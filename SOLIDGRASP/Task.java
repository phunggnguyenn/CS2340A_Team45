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

    // Getters and setters for Task attributes
    // Other generic task methods
}
