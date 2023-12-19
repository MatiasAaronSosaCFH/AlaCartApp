
package com.AlaCartApp.repository;

import com.AlaCartApp.models.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 *
 * @author WilderVlz
 */
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    
    @Query("UPDATE Qualification q SET q.score = :score WHERE q.id = :id")
    Optional<Qualification> updateScore(@Param("score")Integer score, @Param("id")Long id);
}
