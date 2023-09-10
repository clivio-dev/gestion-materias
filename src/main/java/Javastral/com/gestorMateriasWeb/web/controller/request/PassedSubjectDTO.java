package Javastral.com.gestorMateriasWeb.web.controller.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassedSubjectDTO {

    private long subjectId;
    private int score;

}
