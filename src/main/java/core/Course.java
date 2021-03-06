package core;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Courses")
@Data
public class Course {
    @Id  //указываем первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) //фиксируем, что он автоинкрементный
    private int id;

    private String name;

    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;
    private int price;

    @Column(name = "price_per_hour") //для полей, название которых не совпадает с полем в БД добавляем аннтоацию
    private float pricePerHour;

//   предыдущая версия без создания класса Subcriptions
//    @ManyToMany
//    @JoinTable(name = "subscriptions",
//    joinColumns = {@JoinColumn(name = "course_id")},
//    inverseJoinColumns = {@JoinColumn(name = "student_id")})
//    private List<Student> students;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "course")
    private List<Subscription> subscriptions;


}
