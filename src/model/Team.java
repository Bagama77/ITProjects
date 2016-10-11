package model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int idTeam;
    private String nameTeam;
    private List<Developer> developers = new ArrayList<>(10);
    private int size;


    public Team(int idTeam, String nameTeam, int size) {
        this.idTeam = idTeam;
        this.nameTeam = nameTeam;
        this.size = size;
    }

    public Team(int idTeam, String nameTeam) {
        this.idTeam = idTeam;
        this.nameTeam = nameTeam;
        this.size = developers.size();
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addDeveloper(Developer developer){
        developers.add(developer);
        size = developers.size();
    }

    @Override
    public String toString() {
//        String devs = "";
//        for(Developer d: developers){
//            devs += d.toString();
//        }
        return "Team: " +
                "idTeam=" + idTeam +
                ", nameTeam='" + nameTeam + '\'' +
                ", size=" + size;// + "\r\n";// + "\t\tDevelopers:\r\n" + devs;
    }
}
