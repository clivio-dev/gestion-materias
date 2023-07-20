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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private String id;
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<PassedSubject> passedSubjectSet;

    //TODO: lista de planes?
}
