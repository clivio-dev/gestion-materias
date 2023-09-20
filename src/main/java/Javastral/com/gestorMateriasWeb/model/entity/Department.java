package Javastral.com.gestorMateriasWeb.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    private long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Curriculum> curriculumList;

    public Department(long id, String name) {
        this.id = id;
        this.name = name;
        this.curriculumList = new ArrayList<>();
    }
}
