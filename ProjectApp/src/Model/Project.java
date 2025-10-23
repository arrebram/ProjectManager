package Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a project that contains multiple {@link Task} objects.
 * <p>
 * Each {@code Project} has a unique ID, title, description, creation timestamp,
 * and a list of tasks. It supports adding, removing, and searching for tasks,
 * and can determine its own state (e.g., EMPTY, ONGOING, COMPLETED)
 * based on the states of its tasks.
 * </p>
 */
public class Project implements Comparable<Project>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** The title of the project. */
    private final String title;

    /** The current state of the project. */
    private ProjectState state;

    /** The unique identifier of this project. */
    private final int id;

    /** A textual description of the project. */
    private final String description;

    /** The timestamp when this project was created. */
    private final LocalDateTime created;

    /** The ID to assign to the next task added to this project. */
    private int nextTaskId;

    /** The list of tasks that belong to this project. */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new {@code Project} with the specified title, description, and ID.
     *
     * @param title       the title of the project
     * @param description a short description of the project
     * @param id          the unique project ID
     */
    Project(String title, String description, int id) {
        this.tasks = new ArrayList<>();
        this.title = title;
        this.description = description;
        this.id = id;
        created = LocalDateTime.now();
        nextTaskId = 0;
    }

    /**
     * Adds a new task to this project with the given description and priority.
     *
     * @param description the textual description of the task
     * @param prio        the priority level of the task
     * @return the newly created {@link Task}
     */
    public Task addTask(String description, TaskPrio prio) {
        Task myTask = new Task(description, prio, nextTaskId);
        tasks.add(myTask);
        nextTaskId++;
        return myTask;
    }

    /**
     * Removes a specific task from the project.
     *
     * @param task the task to remove
     * @return {@code true} if the task was successfully removed, otherwise {@code false}
     */
    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    /**
     * Returns the most recent update time across all tasks in the project.
     * <p>
     * If no tasks exist, the creation time of the project is returned.
     * </p>
     *
     * @return the {@link LocalDateTime} of the most recent update
     */
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

    /**
     * @return the title of this project
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the unique identifier of this project
     */
    public int getId() {
        return id;
    }

    /**
     * @return a short textual description of this project
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the timestamp when this project was created
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * @return the ID that will be assigned to the next task created
     */
    public int getNextTaskId() {
        return nextTaskId;
    }

    /**
     * Updates the next available task ID.
     *
     * @param nextTaskId the new next task ID
     */
    public void setNextTaskId(int nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task to retrieve
     * @return the matching {@link Task}, or {@code null} if not found
     */
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    /**
     * Compares this project to another project alphabetically by title.
     *
     * @param other the other {@code Project} to compare to
     * @return a negative integer, zero, or a positive integer as this project
     *         title is lexicographically less than, equal to, or greater than the other's
     */
    @Override
    public int compareTo(Project other) {
        int result;
        result = this.getTitle().compareTo(other.getTitle());
        return result;
    }

    /**
     * Determines the current state of the project based on its tasks.
     * <ul>
     *   <li>If there are no tasks → {@link ProjectState#EMPTY}</li>
     *   <li>If all tasks are DONE → {@link ProjectState#COMPLETED}</li>
     *   <li>Otherwise → {@link ProjectState#ONGOING}</li>
     * </ul>
     *
     * @return the {@link ProjectState} representing the project's overall state
     */
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

    /**
     * Finds all tasks in this project that match a given condition.
     *
     * @param matcher an {@link ITaskMatcher} used to check each task
     * @return a list of tasks that satisfy the matcher condition
     */
    public ArrayList<Task> findTasks(ITaskMatcher matcher) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task myTask : tasks) {
            if (matcher.match(myTask)) {
                result.add(myTask);
            }
        }
        return result;
    }
    /**
     * Finds all tasks in this project that match a given condition.
     * @return the different members of the task class
     */
    @Override
    public String toString() {
        return "created=" + created +
            ", title='" + title +
            ", id=" + id +
            ", description='" + description;
    }
}
