package Javastral.com.gestorMateriasWeb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curriculums")
public class Curriculum {

    @Id
    private int id;
    private String name;
    @ManyToMany
    private List<Subject> subjectList;
}
