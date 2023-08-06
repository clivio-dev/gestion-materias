package Javastral.com.gestorMateriasWeb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassedSubjectKey implements Serializable {
    private String userId;
    private String subjectId;

}
