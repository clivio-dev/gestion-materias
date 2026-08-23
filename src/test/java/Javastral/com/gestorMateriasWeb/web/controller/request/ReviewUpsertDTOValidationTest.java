package Javastral.com.gestorMateriasWeb.web.controller.request;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewUpsertDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validatesRatingAndCommentConstraints() {
        var valid = new ReviewUpsertDTO(5, "Muy buena");
        var lowRating = new ReviewUpsertDTO(0, "Comentario");
        var blankComment = new ReviewUpsertDTO(3, "   ");
        var longComment = new ReviewUpsertDTO(4, "a".repeat(2001));

        assertThat(validator.validate(valid)).isEmpty();
        assertThat(validator.validate(lowRating))
                .anyMatch(v -> "rating".equals(v.getPropertyPath().toString()));
        assertThat(validator.validate(blankComment))
                .anyMatch(v -> "comment".equals(v.getPropertyPath().toString()));
        assertThat(validator.validate(longComment))
                .anyMatch(v -> "comment".equals(v.getPropertyPath().toString()));
    }
}
