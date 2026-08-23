package Javastral.com.gestorMateriasWeb.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "professor_reviews",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_professor_reviews_user_professor", columnNames = {"user_id", "professor_id"})
        },
        indexes = {
                @Index(name = "idx_professor_reviews_professor_id", columnList = "professor_id"),
                @Index(name = "idx_professor_reviews_professor_rating", columnList = "professor_id, rating")
        }
)
public class ProfessorReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @Min(1)
    @Max(5)
    @Column(nullable = false, columnDefinition = "TINYINT")
    private int rating;

    @NotBlank
    @Size(max = 2000)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ProfessorReview(UserEntity user, Professor professor, int rating, String comment) {
        this.user = user;
        this.professor = professor;
        this.rating = rating;
        this.comment = comment;
    }
}
