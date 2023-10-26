import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<Task> tasks = new ArrayList<>();
    private List<TeamMember> teamMembers = new ArrayList<>();

    public Project(String name, String description, Date startDate, Date endDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void addTeamMember(TeamMember teamMember) {
        teamMembers.add(teamMember);
    }

    public void removeTeamMember(TeamMember teamMember) {
        teamMembers.remove(teamMember);
    }
    
    // Other project-related methods here
}
