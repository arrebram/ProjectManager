package ui;

import Model.Project;
import Model.ProjectsManager;

import java.util.List;
import java.util.Scanner;

/**
 * User interactions for managing projects.
 * The user selects actions in the mainLoop method.
 */
public class MainUI {

    private final Scanner scan;
    private final ProjectsManager manager;
    private final CurrentProjectUI currentProjectUI;

    public MainUI(ProjectsManager manager) {
        this.manager = manager;
        this.scan = new Scanner(System.in);
        // create object handling user interaction for current project
        this.currentProjectUI = new CurrentProjectUI(scan);
    }

    public void mainLoop() {
        char choice;

        do {
            printMainMenu();
            choice = InputUtils.scanAndReturnFirstChar(scan);

            switch (choice) {
                case 'F':
                    findProjects();
                    break;
                case 'A':
                    addProject();
                    break;
                case 'M':
                    manageProject();
                    break;
                case 'R':
                    removeProject();
                    break;
                case 'L':
                    listOfProjects();
                    break;
                case 'X':
                    break;
                default:
                    System.out.println("Unknown command");
            }

        } while (choice != 'X');
    }

    private void findProjects() {
        System.out.print("Project name? ");
        String name = scan.nextLine();
        List<Project> result = manager.findProjects(name);
        if (result.isEmpty()) {
            System.out.println("No matches.");
        } else {
            for (Project project : result) {
                System.out.println(project.toString());
            }
        }
    }

    private void addProject() {
        try {
            System.out.print("Project title: ");
            String title = scan.nextLine();
            System.out.print("Description: ");
            String description = scan.nextLine();
            Project newProject = manager.addProject(title, description);
            System.out.println("Project created: " + title);
        } catch (IllegalArgumentException e) {
            System.out.println("A project with that title already exists.");
        }
    }

    private void manageProject() {
        System.out.print("Project id? ");
        int id = scan.nextInt();
        scan.nextLine(); //remove "new line" from scanner buffer
        Project currentProject = manager.getProjectById(id);
        if (currentProject != null) {
            System.out.println(currentProject.getTitle());
            currentProjectUI.setCurrentProject(currentProject);
        } else {
            System.out.println("Project not found");
        }
    }

    private void removeProject(){
        System.out.println("Project id? ");
        int id = scan.nextInt();
        scan.nextLine();
        Project currentProject = manager.getProjectById(id);
        if(currentProject != null){
            System.out.println("Project removed: " + currentProject.getTitle());
            manager.removeProject(currentProject);
        }
        else {
            System.out.println("Project not found");
        }
    }

    private void listOfProjects(){
        if(!manager.getProjects().isEmpty()){
            for(Project p : manager.getProjects()){
                System.out.println(p.getTitle() + ": " + p.getDescription());
            }
        }
        else {
            System.out.println("No projects created.");
        }
    }

    private void printMainMenu() {
        System.out.println("---Main menu---");
        System.out.println("F - find project");
        System.out.println("A - add project");
        System.out.println("M - manage project");
        System.out.println("R - Remove project");
        System.out.println("L - List of projects");
        System.out.println("X - exit");
        System.out.println("----------");
    }
}
