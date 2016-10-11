package controler;

import dao.ProjectDAO;
import view.MainMenu;
import view.MenuAddProject;
import view.MenuDeleteProject;
import view.ViewAllProjects;

import java.util.Scanner;

public class MainController {
    MainMenu mainMenu = new MainMenu();
    MenuAddProject menuAddProject = new MenuAddProject();
    MenuDeleteProject menuDeleteProject = new MenuDeleteProject();
    ViewAllProjects viewAllProjects = new ViewAllProjects();

    ProjectDAO projectDAO = new ProjectDAO();

    public void mainLogic(){
        while(true) {
            System.out.println(mainMenu.showMainMenu());
            System.out.println("Please enter your choice:");
            int mainMenuItem = getChoice(1);
            switch (mainMenuItem) {
                case 1:
                    System.out.println(menuAddProject.showMenuAddProject());
                    menuAddProjectLogic();
                    break;
                case 2:
                    System.out.println(menuDeleteProject.showMenuDeleteProject());
                    menuDeleteProjectLogic();
                    break;
                case 3:
                    System.out.println(viewAllProjects.showViewAllProjects());
                    menuListProjects();
                    break;
                case 4:
                    System.out.println("Exit..");
                    projectDAO.closeConnection();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose item from 1 to 4");
                    break;
            }
        }
    }

    public void menuAddProjectLogic(){
        String[] newProjectInitialData = getChoice();
        String tempSizeString = newProjectInitialData[2].trim();
        int size = Integer.valueOf(tempSizeString);
        projectDAO.addProject(newProjectInitialData[0].trim(), newProjectInitialData[1].trim(), size);
    }

    public void menuDeleteProjectLogic(){
        int idOfDeletingProject = getChoice(1);
        projectDAO.deleteProject(idOfDeletingProject);
    }

    public void menuListProjects(){
        projectDAO.listProjects();
    }

    public int getChoice(int integer){
        Scanner sc=new Scanner(System.in);
        int choice = sc.nextInt();
        return choice;
    }

    public String[] getChoice(){
        Scanner sc=new Scanner(System.in);
        String choice = sc.nextLine();
        String[] strArr = choice.split(",", 3);
        return strArr;
    }

}
