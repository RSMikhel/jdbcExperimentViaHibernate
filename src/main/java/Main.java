import core.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class Main {
    public static Session session = HibernateUtils.sessionFactory.openSession();

    public static void main(String[] args) throws SQLException {


        //читаем таблицу PurchaseList, сохраняем все объекты в List<PurchaseList>

        List<PurchaseList> purchaseLists = getPurchaseList();

        for(PurchaseList item : purchaseLists)
            System.out.println(item.getKey().getCourseName());


    Course courseRow;
    Student studentRow;

        Transaction transaction = session.beginTransaction();

        for(PurchaseList purchase : purchaseLists) {

            courseRow = selectCourse(purchase);
            studentRow = selectStudent(purchase);

            LinkedPurchaseList.PK pk = new LinkedPurchaseList.PK();
            pk.setCourseId(courseRow.getId());
            pk.setStudentId(studentRow.getId());

            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setKey(pk);
            session.save(linkedPurchaseList);
            

        }


       transaction.commit();

        session.close();


    }

    private static List<PurchaseList> getPurchaseList() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    private static Course selectCourse(PurchaseList purchase ) {
        String hqlCourse;
        Query queryCourse;
        hqlCourse = "from " + Course.class.getSimpleName() + " where name = :paramCourseName" ;
        queryCourse = session.createQuery(hqlCourse);
        queryCourse.setParameter("paramCourseName", purchase.getKey().getCourseName());
        return (Course) queryCourse.getSingleResult();
    }

    /* Первый способ получения Course.Id по названию с помощью Hibernate query builder
        CriteriaQuery<Course> queryCourse = builder.createQuery(Course.class);
        Root<Course> rootCourse = queryCourse.from(Course.class);
        queryCourse.select(rootCourse).where(builder.equal(rootCourse.get("name"),"Управление продуктом"));
        List<Course> courseList = session.createQuery(queryCourse).getResultList();

        for(Course item : courseList)
            System.out.println(item.getId());
*/

    private static Student selectStudent(PurchaseList purchase ) {
        String hqlStudent;
        Query queryStudent;
        hqlStudent = "from " + Student.class.getSimpleName() + " where name = :paramStudentName" ;
        queryStudent = session.createQuery(hqlStudent);
        queryStudent.setParameter("paramStudentName", purchase.getKey().getStudentName());
        return (Student) queryStudent.getSingleResult();
    }

    //вывод с помощью таблицы Subscriptions, определённой как класс
    private static void getStudentsAndSubscriptions (){
        Course course = session.get(Course.class, 3);

        System.out.println("Курс: " + course.getName()
                + "\nДлительность: " + course.getDuration() + " часов. "
                + "\nОписание: " + course.getDescription()
                + "\nПреподаватель: " + course.getTeacher().getName());

        System.out.println("Список студентов и даты подписки на курс: ");

        for(int i = 0; i < course.getSubscriptions().size(); i++) {
            Subscription subscription = course.getSubscriptions().get(i);
            Student student = subscription.getStudent();
            Date subscriptionDate = subscription.getSubscriptionDate();

            System.out.println(student.getName() + " " + subscriptionDate);

        }
    }
}