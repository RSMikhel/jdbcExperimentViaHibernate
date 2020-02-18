package core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "subscriptions")
@IdClass(Subscriptions.SubscriptionsId.class)
@Data
public class Subscriptions {
    @Id

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @Id

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;


    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscriptions() {
    }

    @EqualsAndHashCode
    @Data
    public static class SubscriptionsId implements Serializable {

        private Course course;
        private Student student;


    }
}
