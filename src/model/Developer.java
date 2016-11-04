package model;

public class Developer {
    private int id;
    private String nameDeveloper;
    private String speciality;
    private int salary;

    public Developer(){}

    public Developer(int id, String name, String speciality, int salary) {
        this.id = id;
        this.nameDeveloper = name;
        this.speciality = speciality;
        this.salary = salary;
    }

    public Developer(String name, String speciality, int salary) {
        this.nameDeveloper = name;
        this.speciality = speciality;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDeveloper() {
        return nameDeveloper;
    }

    public void setNameDeveloper(String name) {
        this.nameDeveloper = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        System.out.printf("\t\t\tid=%d , nameDeveloper=%-20s , \tspeciality=%-10s , \tsalary=%d \r\n",
                id, nameDeveloper, speciality, salary);
        return "";
    }
}
