import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        Session session = HibernateUtils.sessionFactory.openSession();

        Course course = session.get(Course.class, 3);

        System.out.println("Курс " + course.getName() + " длится всего " + course.getDuration() + " часов. Имеет описание:\n" + course.getDescription());

        System.out.println(course.getName());


        session.close();


    }
}