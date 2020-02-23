package core;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "purchaselist")

public class PurchaseList {

    @EmbeddedId
    @Getter
    private PK key;

    @Getter
    @Setter
    private int price;

    @Column(name = "subscription_date")
    @Getter
    @Setter
    private Date subscriptionDate;

    @Embeddable
    @ToString
    public static class PK implements Serializable {


        @Column(name = "student_name")
        @Setter
        @Getter
        private String studentName;

        @Column(name = "course_name")
        @Setter
        @Getter
        private String courseName;


    }

    }

