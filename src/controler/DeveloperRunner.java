package controler;
import model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Adding new developer to team..");
        Integer idDeveloper = developerRunner.addDeveloper("HibernateDeveloper", "Hibernate", 1567);


        System.out.println("List of developers");
        List<Developer> developers = developerRunner.listDevelopers();
        for (Developer developer : developers) {
            System.out.println(developer);
        }
        System.out.println("===================================");
        System.out.println("Removing Some Developer and updating developer");
        developerRunner.updateDeveloper(10, 3);
        developerRunner.removeDeveloper(41);

        System.out.println("Final list of developers");
        developers = developerRunner.listDevelopers();
        for (Developer developer : developers) {
            System.out.println(developer);
        }
        System.out.println("===================================");
    }
        public Integer addDeveloper(String nameDeveloper, String speciality, int salary) {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;

            transaction = session.beginTransaction();
            Developer developer = new Developer(nameDeveloper, speciality, salary);
            Integer idDeveloper = (Integer) session.save(developer);
            transaction.commit();
            session.close();
            return idDeveloper;
        }

        public List<Developer> listDevelopers() {
            Session session = this.sessionFactory.openSession();
            Transaction transaction = null;

            transaction = session.beginTransaction();
            List<Developer> developers = session.createQuery("FROM model.Developer").list();

            transaction.commit();
            session.close();
            return developers;
        }

        public void updateDeveloper(int developerId, int salary) {
            Session session = this.sessionFactory.openSession();
            Transaction transaction = null;

            transaction = session.beginTransaction();
            Developer developer = (Developer) session.get(Developer.class, developerId);
            developer.setSalary(salary);
            session.update(developer);
            transaction.commit();
            session.close();
        }

        public void removeDeveloper(int developerId) {
            Session session = this.sessionFactory.openSession();
            Transaction transaction = null;

            transaction = session.beginTransaction();
            Developer developer = (Developer) session.get(Developer.class, developerId);
            session.delete(developer);
            transaction.commit();
            session.close();
        }





}


