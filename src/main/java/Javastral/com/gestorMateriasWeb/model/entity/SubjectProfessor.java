package Javastral.com.gestorMateriasWeb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject_professors")
public class SubjectProfessor {

    @EmbeddedId
    private SubjectProfessorId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("professorId")
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    public SubjectProfessor(Subject subject, Professor professor) {
        this.id = new SubjectProfessorId(subject.getId(), professor.getId());
        this.subject = subject;
        this.professor = professor;
    }
}
