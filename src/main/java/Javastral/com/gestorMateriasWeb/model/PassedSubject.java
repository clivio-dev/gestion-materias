package Javastral.com.gestorMateriasWeb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passed_subjects")
@IdClass(PassedSubjectKey.class)
public class PassedSubject {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @Basic
    @Column(name = "score")
    private int score;
}
