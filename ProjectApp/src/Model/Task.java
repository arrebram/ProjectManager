package Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a task within a project or workflow.
 * <p>
 * Each {@code Task} has an ID, description, priority, current state,
 * the person responsible for it, and a timestamp for when it was last updated.
 * The class implements {@link Serializable} to allow persistence
 * and {@link Comparable} to enable sorting by priority (and description as a tie-breaker).
 * </p>
 */
public class Task implements Serializable, Comparable<Task> {

    @Serial
    private static final long serialVersionUID = 1L;

    /** A short description of the task. */
    private String description;

    /** Unique identifier for this task. */
    private final int id;

    /** The name of the person currently assigned to this task. */
    private String takenBy;

    /** The current state of the task (e.g. OPEN, IN_PROGRESS, DONE). */
    private TaskState state;

    /** The last time this task was modified. */
    private LocalDateTime lastUpdated;

    /** The priority level of this task. */
    private TaskPrio prio;

    /**
     * Creates a new {@code Task} with a description, priority, and unique ID.
     *
     * @param description the textual description of the task
     * @param prio the task's priority level
     * @param id the unique identifier of the task
     */
    Task(String description, TaskPrio prio, int id) {
        this.description = description;
        this.prio = prio;
        this.id = id;
        lastUpdated = LocalDateTime.now();
    }

    /**
     * Updates the description of the task and refreshes its last updated timestamp.
     *
     * @param description the new description text
     */
    public void setDescription(String description) {
        this.description = description;
        setLastUpdated();
    }

    /**
     * Assigns this task to a person.
     * <p>
     * This method can only be called once; if the task is already taken,
     * an {@link IllegalStateException} is thrown.
     * </p>
     *
     * @param takenBy the name of the person taking the task
     * @throws IllegalStateException if the task is already assigned
     */
    public void setTakenBy(String takenBy) {
        if (this.takenBy != null) {
            throw new IllegalStateException("Task already taken.");
        }
        this.takenBy = takenBy;
        setLastUpdated();
    }

    /**
     * Changes the current state of the task and refreshes its timestamp.
     *
     * @param state the new {@link TaskState} of the task
     */
    public void setState(TaskState state) {
        this.state = state;
        setLastUpdated();
    }

    /**
     * Updates the {@code lastUpdated} timestamp to the current time.
     */
    public void setLastUpdated() {
        lastUpdated = LocalDateTime.now();
    }

    /**
     * Updates the priority of the task and refreshes its timestamp.
     *
     * @param prio the new {@link TaskPrio} level
     */
    public void setPrio(TaskPrio prio) {
        this.prio = prio;
        setLastUpdated();
    }

    /**
     * @return the textual description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the unique ID of the task
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name of the person assigned to the task, or {@code null} if unassigned
     */
    public String getTakenBy() {
        return takenBy;
    }

    /**
     * @return the current {@link TaskState} of the task
     */
    public TaskState getState() {
        return state;
    }

    /**
     * @return the timestamp of the most recent modification
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @return the {@link TaskPrio} (priority) level of the task
     */
    public TaskPrio getPrio() {
        return prio;
    }

    /**
     * Compares this task to another task, primarily by priority.
     * <p>
     * If both tasks have the same priority, the comparison falls back to
     * their descriptions alphabetically.
     * </p>
     *
     * @param other the other {@code Task} to compare to
     * @return a negative integer, zero, or a positive integer as this task is
     *         less than, equal to, or greater than the specified task
     */
    @Override
    public int compareTo(Task other) {
        int result;
        result = this.getPrio().compareTo(other.getPrio());
        if (result == 0) {
            this.getDescription().compareTo(other.getDescription());
        }
        return result;
    }
}
