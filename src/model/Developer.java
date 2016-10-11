package model;

public class Developer {
    int idDeveloper;
    String nameDeveloper;
    String speciality;
    int salary;

    public Developer(int id, String name, String speciality, int salary) {
        this.idDeveloper = id;
        this.nameDeveloper = name;
        this.speciality = speciality;
        this.salary = salary;
    }

    public int getId() {
        return idDeveloper;
    }

    public void setId(int id) {
        this.idDeveloper = id;
    }

    public String getName() {
        return nameDeveloper;
    }

    public void setName(String name) {
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
        System.out.printf("\t\t\tidDeveloper=%d , nameDeveloper=%-20s , \tspeciality=%-10s , \tsalary=%d \r\n",
                idDeveloper, nameDeveloper, speciality, salary);
        return "";//System.out.printf("\t\t\tidDeveloper=$n , nameDeveloper=$-20s , \tspeciality=$-10s , \tsalary=$n \r\n",
//                idDeveloper, nameDeveloper, speciality, salary);;
    }
}
//"\t\t\tidDeveloper=" + idDeveloper +
//        ", nameDeveloper='" + nameDeveloper + '\'' +
//        ", \tspeciality='" + speciality + '\'' +
//        ", \tsalary=" + salary + "\r\n";