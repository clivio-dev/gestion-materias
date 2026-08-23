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
        name = "subject_reviews",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_subject_reviews_user_subject", columnNames = {"user_id", "subject_id"})
        },
        indexes = {
                @Index(name = "idx_subject_reviews_subject_id", columnList = "subject_id"),
                @Index(name = "idx_subject_reviews_subject_rating", columnList = "subject_id, rating")
        }
)
public class SubjectReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

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

    public SubjectReview(UserEntity user, Subject subject, int rating, String comment) {
        this.user = user;
        this.subject = subject;
        this.rating = rating;
        this.comment = comment;
    }
}
