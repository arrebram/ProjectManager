package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Project implements Comparable<Project> {
    private final String title;
    private ProjectState state;
    private final int id;
    private final String description;
    private final LocalDateTime created;
    private int nextTaskId;
    private final ArrayList<Task> tasks;

    Project(String title, String description, int id){
        this.tasks = new ArrayList<>();
        this.title = title;
        this.description = description;
        this.id = id;
        created = LocalDateTime.now();
        nextTaskId = 0;
    }

    public Task addTask(String description, TaskPrio prio){
        Task myTask = new Task(description, prio, nextTaskId);
        tasks.add(myTask);
        nextTaskId++;
        return  myTask;
    }

    public boolean removeTask(Task task){
        return tasks.remove(task);
    }

    public LocalDateTime getLastupdated() {
        if (tasks.isEmpty()) {
            return created;
        }

        LocalDateTime lastUpdated = created;

        for (Task task : tasks) {
            LocalDateTime taskUpdated = task.getLastUpdated();
            if (taskUpdated.isAfter(lastUpdated)) {
                lastUpdated = taskUpdated;
            }
        }

        return lastUpdated;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }



    public LocalDateTime getCreated() {
        return created;
    }


    public int getNextTaskId() {
        return nextTaskId;
    }

    public void setNextTaskId(int nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    public Task getTaskById(int id){
        for(Task task: tasks){
            if (task.getId()==id){
                return task;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Project other) {
        int result;
        result = this.getTitle().compareTo(other.getTitle());
        return result;
    }

    public ProjectState getProjectState() {
        if (tasks.isEmpty()) {
            return ProjectState.EMPTY;
        }

        boolean allDone = true;
        for (Task task : tasks) {
            if (task.getState() != TaskState.DONE) {
                allDone = false;
                break;
            }
        }

        return allDone ? ProjectState.COMPLETED : ProjectState.ONGOING;
    }


    public ArrayList<Task> findTasks(ITaskMatcher matcher){
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task myTask: tasks){
            if (matcher.match(myTask)){
                result.add(myTask);
            }
        }
        return result;
    }
}
