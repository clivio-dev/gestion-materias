package Javastral.com.gestorMateriasWeb.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    private long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "subject_prerequisites",
            joinColumns = @JoinColumn(name = "subject_id")
    )
    @Column(name = "prerequisite_id")
    private Set<Long> prerequisiteSubjects;

    public Subject(long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject subject)) return false;
        return id == subject.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
