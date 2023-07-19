package Javastral.com.gestorMateriasWeb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class PassedSubjectKey implements Serializable {
    @Column(name = "user_id")
    int StudentId;
    @Column(name= "subject_id")
    int subjectId;
}
