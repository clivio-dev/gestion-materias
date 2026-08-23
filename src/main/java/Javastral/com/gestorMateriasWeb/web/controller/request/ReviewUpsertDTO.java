package Javastral.com.gestorMateriasWeb.web.controller.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpsertDTO {

    @Min(1)
    @Max(5)
    private int rating;

    @NotBlank
    @Size(max = 2000)
    private String comment;
}
