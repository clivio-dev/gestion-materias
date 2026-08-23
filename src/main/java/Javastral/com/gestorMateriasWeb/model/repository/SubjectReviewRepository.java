package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.SubjectReview;
import Javastral.com.gestorMateriasWeb.model.proyection.ReviewSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubjectReviewRepository extends JpaRepository<SubjectReview, Long> {

    Optional<SubjectReview> findByUserIdAndSubjectId(long userId, long subjectId);

    List<SubjectReview> findBySubjectIdOrderByCreatedAtDesc(long subjectId);

    List<SubjectReview> findBySubjectIdOrderByRatingDescCreatedAtDesc(long subjectId);

    @Query("""
            SELECT COALESCE(AVG(sr.rating), 0) as avgRating,
                   COUNT(sr) as totalReviews
            FROM SubjectReview sr
            WHERE sr.subject.id = :subjectId
            """)
    ReviewSummaryProjection summarizeBySubjectId(@Param("subjectId") long subjectId);
}
