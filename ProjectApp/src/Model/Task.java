package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements Serializable, Comparable<Task>{
    private String description;
    private final int id;
    private String takenBy;
    private TaskState state;
    private LocalDateTime lastUpdated;
    private TaskPrio prio;

    Task(String description, TaskPrio prio, int id){
        this.description = description;
        this.prio = prio;
        this.id = id;
        lastUpdated = LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
        setLastUpdated();
    }

    public void setTakenBy(String takenBy) {
        if (takenBy!=null){
            throw new IllegalStateException("Task already taken.");
        }
        this.takenBy = takenBy;
        setLastUpdated();
    }

    public void setState(TaskState state) {
        this.state = state;
        setLastUpdated();

    }

    public void setLastUpdated() {
        lastUpdated = LocalDateTime.now();
    }

    public void setPrio(TaskPrio prio) {
        this.prio = prio;
        setLastUpdated();
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public TaskState getState() {
        return state;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public TaskPrio getPrio() {
        return prio;
    }

    @Override
    public int compareTo(Task other){
        int result;
        result = this.getPrio().compareTo(other.getPrio());
        if (result==0) {
            this.getDescription().compareTo(other.getDescription());
        }
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", takenBy='" + takenBy + '\'' +
                ", state=" + state +
                ", lastUpdated=" + lastUpdated +
                ", prio=" + prio +
                '}';
    }
}
