package dao;

import java.sql.*;
import java.util.*;

public class DeveloperDAO extends ConnectionUtil {

    PreparedStatement preparedStatement = null;

    private void addDeveloper(int teamId){
        String sql1 = "insert into Developers(name_Developer, speciality, salary) values(?, ?, ?);";
        String sql2 = "insert into TeamDeveloper(id_Team, id_Developer) values(?, ?);";
        String sql3 = "select id_Developer from developers where name_Developer like(?)";
        Calendar calendar = Calendar.getInstance();
        String name = "" + calendar.getTimeInMillis();
        int random = getRandomNumberInRange(1, 5);
        String speciality = getSpeciality(random);
        int salary = getRandomNumberInRange(500, 3000);

        try {

            System.out.println("Executing statement..");
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, speciality);
            preparedStatement.setInt(3, salary);
            Boolean isExecute = preparedStatement.execute();

            System.out.println("Executed: " + isExecute);

            System.out.print("Getting new developer id from table..");
            preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            int idDeveloper = resultSet.getInt("id_Developer");
            System.out.println(idDeveloper);

            System.out.println("Inserting into teamDeveloper table..");
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, teamId);
            preparedStatement.setInt(2, idDeveloper);
            preparedStatement.execute();

            preparedStatement.close();
            resultSet.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    void addDevelopers(int idTeam, int size){
        for(int i = 0; i < size; i++){
            addDeveloper(idTeam);
        }
    }

    private void deleteDeveloper(int id){
        try {
            String sqlDeleteDeveloper = "delete from Developers where id_Developer = ?;";
            String sqlDeleteTeamDeveloper = "delete from TeamDeveloper where id_Developer = ?;";

            System.out.print("Deleting teamdeveloper from DB..");
            preparedStatement = connection.prepareStatement(sqlDeleteTeamDeveloper);
            preparedStatement.setInt(1, id);
            boolean isExecuted = preparedStatement.execute();
            System.out.println(isExecuted);

            isExecuted = false;
            System.out.print("Deleting developer from DB..");
            preparedStatement = connection.prepareStatement(sqlDeleteDeveloper);
            preparedStatement.setInt(1, id);
            isExecuted = preparedStatement.execute();
            System.out.println(isExecuted);

            preparedStatement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void deleteDevelopers(int teamID){

        String sqlGetListIDTeamDevelopers = "select id_developer from teamdeveloper where id_team = ?;";
        try {

            System.out.println("Retriving data from DB..");
            preparedStatement = connection.prepareStatement(sqlGetListIDTeamDevelopers);
            preparedStatement.setInt(1, teamID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println("Deleting dev id:" + resultSet.getInt("id_developer"));
                deleteDeveloper(resultSet.getInt("id_developer"));
            }

            preparedStatement.close();
            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getSpeciality(int random){
        switch (random) {
            case 1:
                return "Java";
            case 2:
                return "C++";
            case 3:
                return "HTML/CSS";
            case 4:
                return "Python";
            case 5:
                return "PHP";
            default:
                return "fullstack";
        }
    }

    public int getRandomNumberInRange(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        int random = (int)(Math.random() * ((max - min) + 1)) + min;
//        System.out.println("Random instance:" + random);
        return random;
    }
}
