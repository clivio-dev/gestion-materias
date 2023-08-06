package Javastral.com.gestorMateriasWeb.model.from;

import lombok.Data;
import java.util.List;

@Data
public class UserSubjectUpdateForm {
    private String userId;
    private List<PassedSubjectScoreForm> save;
    private List<String> delete;
}
