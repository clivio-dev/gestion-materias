package Javastral.com.gestorMateriasWeb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passed_subject")
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
