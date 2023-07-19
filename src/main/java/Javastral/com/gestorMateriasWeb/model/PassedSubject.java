package Javastral.com.gestorMateriasWeb.model;

import jakarta.persistence.*;

@Entity
public class PassedSubject {
    @EmbeddedId
    PassedSubjectKey id;

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private int score;
}
