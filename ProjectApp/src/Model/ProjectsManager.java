package Model;
import Model.Project;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Manages a collection of {@link Project} objects.
 * <p>
 * This class is responsible for adding, removing, and searching projects.
 * It also checks that project titles are unique and can retrieve the highest project ID.
 * </p>
 */
public class ProjectsManager {
    private int nextProjectId;
    private List<Project> projects;


    /**
     * Constructs a new {@code ProjectsManager} with the specified next project ID.
     *
     */
    public ProjectsManager(){
        this.nextProjectId = 0;
        this.projects = new ArrayList<>();
    }

    /**
     * Replaces the current list of projects with a new one.
     *
     * @param incomingProjects the list of projects to set
     */
    public void setProjects(List<Project> incomingProjects) {
        projects.clear();
        this.projects = new ArrayList<>(incomingProjects);
    }

    /**
     * Checks if a given title is unique among existing projects.
     *
     * @param title the title to check
     * @return {@code true} if the title is unique, otherwise {@code false}
     */
    public boolean isTitleUnique(String title){
        int index = 0;
        for(Project p : projects){
            if(p.getTitle().equals(title)){
                index = 1;
                break;
            }
        }
        if(index == 1){
            return false;
        }
        return true;
    }

    /**
     * Adds a new project with the given title and description.
     *
     * @param title the title of the new project
     * @param descr the description of the new project
     * @return the newly created {@link Project} object
     * @throws TitleNotUniqueException if the title already exists
     */
    public Project addProject(String title, String descr) throws TitleNotUniqueException{
        Project p = new Project(title, descr, nextProjectId);
        if(!isTitleUnique(title)){
            throw new TitleNotUniqueException("Titel är ej unik");
        }
        projects.add(p);
        nextProjectId++;
        return p;
    }

    /**
     * Removes a specific project from the list.
     *
     * @param project the project to remove
     */
    public void removeProject(Project project){
        projects.remove(project);
    }

    /**
     * Retrieves a project by its ID (index in the list).
     *
     * @param id the index of the project in the list
     * @return the {@link Project} at the given index
     */
    public Project getProjectById(int id){
        return projects.get(id);
    }

    /**
     * Finds all projects with a title that matches the given string.
     *
     * @param titleStr the title to search for
     * @return a list of matching {@link Project} objects
     */
    public List<Project> findProjects(String titleStr) {
        List<Project> found = new ArrayList<>();
        for(Project p : projects){
            if(Objects.equals(p.getTitle(), titleStr)){
                found.add(p);
            }
        }
        return found;
    }

    /**
     * Finds the highest project ID among all stored projects.
     *
     * @return the highest project ID found
     */
    public int getHighestId() {
        int highest = 0;
        for (Project p : projects) {
            if (p.getId() > highest) {
                highest = p.getId();
            }
        }
        return highest;
    }

    /**
     * Returns a copy of the list of projects.
     *
     * @return a new {@link List} containing all projects
     */
    public List<Project> getProjects() {
        return new ArrayList<>(projects);
    }

    /**
     * Returns a copy on the various states of the registered projects.
     *
     * @return a new {@link List} containing all states.
     */
    public List<Project> getState(){
        List<Project> stateList = new ArrayList<>();
        for(Project p : projects){
            //stateList = p.getProjectState();
        }
        return stateList;
    }
}