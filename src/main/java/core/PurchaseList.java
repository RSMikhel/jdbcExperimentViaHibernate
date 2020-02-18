package core;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
@Data
public class PurchaseList {
    @EmbeddedId
    private PurchaseListId purchaseListId;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Embeddable
    public class PurchaseListId implements Serializable {

        @ManyToOne
        @JoinColumn(name = "student_name")
        private Student student;

        @ManyToOne
        @JoinColumn(name = "course_name")
        private Course course;

        public PurchaseListId(Student student, Course course) {
            this.student = student;
            this.course = course;
        }

    }



}
