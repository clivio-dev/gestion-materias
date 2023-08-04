package Javastral.com.gestorMateriasWeb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curriculums")
public class Curriculum {

    @Id
    private String id;
    @Column(name = "name")
    private String name;
     @ManyToMany
    @JoinTable(
            name = "curriculums_subjects",
            joinColumns = @JoinColumn(name = "curriculum_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjectSet;

}

