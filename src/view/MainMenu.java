package view;

/**
 * Created by Lenovo on 04.10.2016.
 */
public class MainMenu {
    String mainMenuItem1 = "1. Add Project - 1;\r\n";
    String mainMenuItem2 = "2. Delete project - 2\r\n";
    String mainMenuItem3 = "3. view all projects - 3\r\n";
    String mainMenuItem4 = "3. Exit - 4\r\n";

    public String showMainMenu(){
        return "\r\n----------------------MAIN MENU-------------------------------\r\n" + mainMenuItem1 + mainMenuItem2 + mainMenuItem3 + mainMenuItem4;
    }
}
