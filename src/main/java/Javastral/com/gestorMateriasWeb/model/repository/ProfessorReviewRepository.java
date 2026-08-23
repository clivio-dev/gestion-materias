package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.ProfessorReview;
import Javastral.com.gestorMateriasWeb.model.proyection.ReviewSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorReviewRepository extends JpaRepository<ProfessorReview, Long> {

    Optional<ProfessorReview> findByUserIdAndProfessorId(long userId, long professorId);

    List<ProfessorReview> findByProfessorIdOrderByCreatedAtDesc(long professorId);

    List<ProfessorReview> findByProfessorIdOrderByRatingDescCreatedAtDesc(long professorId);

    @Query("""
            SELECT COALESCE(AVG(pr.rating), 0) as avgRating,
                   COUNT(pr) as totalReviews
            FROM ProfessorReview pr
            WHERE pr.professor.id = :professorId
            """)
    ReviewSummaryProjection summarizeByProfessorId(@Param("professorId") long professorId);
}
