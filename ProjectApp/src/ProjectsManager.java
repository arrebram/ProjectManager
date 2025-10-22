import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectsManager {
    private int nextProjectId;
    private List<Project> projects;

    public ProjectsManager(int nextProjectId) {
        this.nextProjectId = nextProjectId;
        this.projects = new ArrayList<>();
    }

    public void setProjects(List<Project> incomingProjects) {
        projects.clear();
        this.projects = new ArrayList<>(incomingProjects);
    }

    public boolean isTitleUnique(String title){
        int index = 0;
        for(Project p : projects){
            if(p.getTitle == title){
                index = 1;
                break;
            }
        }
        if(index == 1){
            return false;
        }
        return true;
    }

    public Project addProject(String title, String descr) throws TitleNotUniqueException{
        Project p = new Project(title, descr);
        if(!isTitleUnique(title)){
            throw new TitleNotUniqueException("Titel är ej unik");
        }
        projects.add(p);
        return p;
    }

    public void removeProject(Project project){
        projects.remove(project);
    }

    public Project getProjectById(int id){
        return projects.get(id);
    }

    public List<Project> findProjects(String titleStr) {
        List<Project> found = new ArrayList<>();
        for(Project p : projects){
            if(p.getTitle == titleStr){
                found.add(p);
            }
        }
        return found;
    }

    public int getHighestId() {
        int highest = 0;
        for (Project p : projects) {
            if (p.getId() > highest) {
                highest = p.getId();
            }
        }
        return highest;
    }
}
