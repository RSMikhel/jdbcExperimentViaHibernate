import core.Course;
import core.Student;
import core.Subscriptions;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws SQLException {


        Session session = HibernateUtils.sessionFactory.openSession();

        Course course = session.get(Course.class, 3);

        System.out.println("Курс: " + course.getName()
                + "\nДлительность: " + course.getDuration() + " часов. "
                + "\nОписание: " + course.getDescription()
                + "\nПреподаватель: " + course.getTeacher().getName());

        System.out.println("Список студентов и даты подписки на курс: ");

        for(int i = 0; i < course.getSubscriptions().size(); i++) {
            Subscriptions subscription = course.getSubscriptions().get(i);
            Student student = subscription.getStudent();
            Date subscriptionDate = subscription.getSubscriptionDate();

            System.out.println(student.getName() + " " + subscriptionDate);

        }
//
//        for(int i = 0; i < course.getStudents().size(); i++) {
//            for(int j = 0; j < course.getStudents().get(j).getPurchaseList().size(); j++)
//                System.out.println(course.getStudents().get(i).getName() + " "
//                        + course.getStudents().get(i).getPurchaseList().get(j).getSubscriptionDate());
//        }

       // course.getStudents().stream().map(s -> s.getName()).forEach(System.out::println);




        session.close();


    }
}