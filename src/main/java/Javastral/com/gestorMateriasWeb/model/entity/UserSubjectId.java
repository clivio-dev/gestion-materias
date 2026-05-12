package Javastral.com.gestorMateriasWeb.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubjectId implements Serializable {
    private Long userId;
    private Long subjectId;
} 