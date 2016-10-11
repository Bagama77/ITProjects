package dao;

import java.sql.*;

public class TeamDAO extends ConnectionUtil {
    PreparedStatement preparedStatement;
    DeveloperDAO developerDAO = new DeveloperDAO();
    ResultSet resultSet = null;

    int addTeam(String name, int size) {
        int idTeam = 0;
        try {
            System.out.println("Inserting new team..");
            String sqlInsertTeam = "insert into Teams(name_Team, size_Team) values(?, ?);";
            preparedStatement = connection.prepareStatement(sqlInsertTeam);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, size);
            preparedStatement.execute();
            System.out.println("Executed");

            System.out.println("Getting id of new Team..");
            String sqlGetIdOfNewTeam = "select id_Team from Teams where name_Team like(?);";
            preparedStatement = connection.prepareStatement(sqlGetIdOfNewTeam);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            idTeam = resultSet.getInt("id_Team");
            System.out.println("Executed, team id:" + idTeam);
            System.out.println("Adding developers");
            developerDAO.addDevelopers(idTeam, size);

            preparedStatement.close();
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idTeam;
    }

    void deleteTeam(int idTeam){
        try {
            System.out.println("Getting id developers from team " + idTeam);
            String sqlGetIdDevelopers = "select id_developer from teamdeveloper where id_team = ?";
            preparedStatement = connection.prepareStatement(sqlGetIdDevelopers);
            preparedStatement.setInt(1, idTeam);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int[] idDevelopers = new int[resultSet.getRow()];
            resultSet.first();
            for(int i = 0; i < idDevelopers.length; i++){
                idDevelopers[i] = resultSet.getInt("id_Developer");
                resultSet.next();
            }

            String sqlDeleteTeamDeveloper = "delete from teamdeveloper where id_team = ?;";
            preparedStatement = connection.prepareStatement(sqlDeleteTeamDeveloper);
            preparedStatement.setInt(1, idTeam);
            preparedStatement.execute();

            System.out.println("Deleting Team..");
            String sqlDeleteTeam = "delete from Teams where id_team = ?";
            preparedStatement = connection.prepareStatement(sqlDeleteTeam);
            preparedStatement.setInt(1, idTeam);
            preparedStatement.execute();

            System.out.print("Deleting developer with id:");
            String sqlDeleteDeveloper = "delete from developers where id_developer = ?;";
            for(int i = 0; i < idDevelopers.length; i++){
                preparedStatement = connection.prepareStatement(sqlDeleteDeveloper);
                preparedStatement.setInt(1, idDevelopers[i]);
                preparedStatement.execute();
                System.out.print(idDevelopers[i] + ", ");
            }
            System.out.println();

            preparedStatement.close();
            resultSet.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
