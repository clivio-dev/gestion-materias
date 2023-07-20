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
@Table(name = "departments")
public class Department {

    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany
    private List<Curriculum> curriculumList;


}
