package dao;

import model.Developer;
import model.Project;
import model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProjectDAO extends ConnectionUtil {

    PreparedStatement preparedStatement;
    TeamDAO teamDAO = new TeamDAO();
    ResultSet resultSet = null;

    public void addProject(String name, String nameOfTeam, int sizeOfTeam){
        try {
            System.out.println("Inserting new project..");
            String sqlInsertProject = "insert into Projects(name_Project) values(?);";
            preparedStatement = connection.prepareStatement(sqlInsertProject);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            System.out.println("Executed");

            System.out.println("Getting id of new project..");
            String sqlGetIdOfNewProject = "SELECT id_project from Projects where name_project like(?);";
            preparedStatement = connection.prepareStatement(sqlGetIdOfNewProject);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            int idProject = resultSet.getInt("id_project");
            System.out.println("Id of new project: " + idProject);

            int idTeam = teamDAO.addTeam(nameOfTeam, sizeOfTeam);
            if(idTeam != 0) {
                System.out.println("Inserting into projectteam..");
                String sqlInsertProjectteam = "insert into projectteam(id_project, id_team) values(?, ?);";
                preparedStatement = connection.prepareStatement(sqlInsertProjectteam);
                preparedStatement.setInt(1, idProject);
                preparedStatement.setInt(2, idTeam);
                preparedStatement.execute();

            }

            preparedStatement.close();
            resultSet.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteProject(int idProject){

        try {
            System.out.println("Getting id_Team for deleting project");
            String sqlGetTeamIdProject = "select id_Team from projectteam WHERE id_project = ?;";
            preparedStatement = connection.prepareStatement(sqlGetTeamIdProject);
            preparedStatement.setInt(1, idProject);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            int idTeam = resultSet.getInt("id_Team");
            System.out.println("Team id is: " + idTeam);

            System.out.println("Deleting projectteam with project id: " + idProject);
            String sqlDeleteProjectTeam = "DELETE FROM projectteam WHERE id_project = ?;";
            preparedStatement = connection.prepareStatement(sqlDeleteProjectTeam);
            preparedStatement.setInt(1, idProject);
            preparedStatement.execute();

            System.out.println("Deleting project with project id: " + idProject);
            String sqlDeleteProject = "DELETE FROM projects WHERE id_project = ?;";
            preparedStatement = connection.prepareStatement(sqlDeleteProject);
            preparedStatement.setInt(1, idProject);
            preparedStatement.execute();

            teamDAO.deleteTeam(idTeam);

            resultSet.close();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void listProjects(){
        try {
            String sqlListProjectTeam = "SELECT P.id_Project, name_Project, T.id_Team, name_Team, size_Team " +
                    "FROM Projects AS P, Teams AS T, ProjectTeam AS PT WHERE P.id_Project = PT.id_Project and " +
                    "T.id_Team = PT.id_Team;";
            preparedStatement = connection.prepareStatement(sqlListProjectTeam);
            resultSet = preparedStatement.executeQuery();
//            resultSet.first();
            int idProject, idTeam, sizeTeam;
            String nameProject, nameTeam;
            Team teamTemp;
            Project projectTemp;
            String nameDeveloper, speciality;
            int idDeveloper, salary;
//-------------GETTING INFO ABOUT PROJECTS, TEAMS-----------------------------------------------------------
            while (resultSet.next()){
                idProject = resultSet.getInt("id_Project");
                idTeam = resultSet.getInt("id_Team");
                nameProject = resultSet.getString("name_Project");
                nameTeam = resultSet.getString("name_Team");
                sizeTeam = resultSet.getInt("size_Team");

                projectTemp = new Project(idProject, nameProject);
                teamTemp = new Team(idTeam, nameTeam, sizeTeam);
//--------------GETTING DEVELOPERS FROM DATABASE-------------------------------------------------------------
                String sqlGetDevelopersByIdTeam = "SELECT D.id_developer, name_developer, speciality, salary " +
                        "FROM Developers AS D, TeamDeveloper AS TD WHERE D.id_Developer = TD.id_Developer AND " +
                        "id_Team = ?;";
                preparedStatement = connection.prepareStatement(sqlGetDevelopersByIdTeam);
                preparedStatement.setInt( 1, idTeam);
                ResultSet resultSet2 = preparedStatement.executeQuery();
                resultSet2.first();
//-------------ADDING DEVELOPERS TO TEAM---------------------------------------------------------------------
                while (resultSet2.next()){
                    nameDeveloper = resultSet2.getString("name_Developer");
                    idDeveloper = resultSet2.getInt("id_developer");
                    speciality = resultSet2.getString("speciality");
                    salary = resultSet2.getInt("salary");

                    teamTemp.addDeveloper(new Developer(idDeveloper, nameDeveloper, speciality, salary));
                }
//----------------------------------------------------------------------------------------------------------
//------------------ADDING TEAM TO PROJECT -----------------------------------------------------------------
                projectTemp.setTeam(teamTemp);
//---------------DISPLAYING THE PROJECT INFO----------------------------------------------------------------
                System.out.println(projectTemp);
                System.out.println(projectTemp.getTeam());
                System.out.println(teamTemp.getDevelopers());
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
