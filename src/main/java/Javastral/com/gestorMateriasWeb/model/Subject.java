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
    private String name;
    @ManyToMany(mappedBy = "subjectSet")
    private Set<Curriculum> curriculumList;



    @ElementCollection
    @CollectionTable(
            name = "subject_prerequisites", // Nombre de la tabla para almacenar los códigos correlativos
            joinColumns = @JoinColumn(name = "subject_id")
    )
    @Column(name = "subject_prerequisite_id")
    private Set<Integer> prerequisiteSubjects; // Lista de códigos de materias correlativas

    //TODO: es necesario esto?
    /*@OneToMany(mappedBy = "subject")
    Set<PassedSubject> passedSubjectSet;*/
}
