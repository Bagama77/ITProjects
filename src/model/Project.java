package model;

public class Project {
    int idProject;
    String nameProject;
    Team team;

    public Team getTeam() {
        return team;
    }

    public Project(int idProject, String nameProject) {
        this.idProject = idProject;
        this.nameProject = nameProject;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Project: " +
                "idProject=" + idProject +
                ", nameProject='" + nameProject + '\'';// +
//                ", \r\n";//3 + "\t" + team + "\r\n";
    }
}
