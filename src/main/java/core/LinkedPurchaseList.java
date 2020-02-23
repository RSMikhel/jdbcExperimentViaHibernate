package core;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class LinkedPurchaseList {
    @EmbeddedId
    private PK key;

    @Embeddable
    @EqualsAndHashCode
    @ToString
    public static class PK implements Serializable {
        @Column(name = "course_id", nullable = false)
        @Getter
        @Setter
        private int courseId;

        @Column(name = "student_id", nullable = false)
        @Getter
        @Setter
        private  int studentId;
    }

}
