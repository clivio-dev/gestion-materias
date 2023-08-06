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
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "subject_id")
    private String subjectId;

    @Basic
    @Column(name = "score")
    private int score;


}
