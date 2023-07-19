package Javastral.com.gestorMateriasWeb.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    private int id;
    private String name;
    @ManyToMany
    private List<Curriculum> curriculumList;
    /*@OneToMany(mappedBy = "subject")
    Set<PassedSubject> passedSubjectSet;*/
}
