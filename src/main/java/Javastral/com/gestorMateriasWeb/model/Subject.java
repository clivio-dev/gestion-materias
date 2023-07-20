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
@Table(name = "subjects")
public class Subject {

    @Id
    private String id;
    @Column(name = "subject_name")
    private String name;
    @ManyToMany(mappedBy = "subjectSet")
    private Set<Curriculum> curriculumList;



    @ElementCollection
    @CollectionTable(
            name = "subject_prerequisites",
            joinColumns = @JoinColumn(name = "subject_id")
    )
    @Column(name = "subject_prerequisite_id")
    private Set<Integer> prerequisiteSubjects;

}
