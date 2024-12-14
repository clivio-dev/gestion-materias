package Javastral.com.gestorMateriasWeb.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passed_subjects")
public class PassedSubject {
    @EmbeddedId
    private UserSubjectId id;
    
    private Integer grade;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserEntity user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subjectId")
    private Subject subject;
}