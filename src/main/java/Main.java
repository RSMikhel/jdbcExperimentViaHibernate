import core.Course;
import org.hibernate.Session;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        Session session = HibernateUtils.sessionFactory.openSession();

        Course course = session.get(Course.class, 3);

        System.out.println("Курс: " + course.getName()
                + "\nДлительность: " + course.getDuration() + " часов. "
                + "\nОписание: " + course.getDescription()
                + "\nПреподаватель: " + course.getTeacher().getName());

        System.out.println("Список студентов: ");
        course.getStudents().stream().map(s -> s.getName()).forEach(System.out::println);




        session.close();


    }
}