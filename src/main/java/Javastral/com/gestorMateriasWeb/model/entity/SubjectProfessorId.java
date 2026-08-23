package Javastral.com.gestorMateriasWeb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SubjectProfessorId implements Serializable {

    @Column(name = "subject_id")
    private long subjectId;

    @Column(name = "professor_id")
    private long professorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectProfessorId that)) return false;
        return subjectId == that.subjectId && professorId == that.professorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, professorId);
    }
}
