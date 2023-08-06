package Javastral.com.gestorMateriasWeb.model;

import Javastral.com.gestorMateriasWeb.model.proyection.PassedSubjectProyectionIdScore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private String id;
    @Column(name = "name")
    private String name;

    @Transient
    private Set<PassedSubjectProyectionIdScore> passedSubjects;
}
