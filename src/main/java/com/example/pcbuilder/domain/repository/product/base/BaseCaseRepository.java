package com.example.pcbuilder.domain.repository.product.base;

import com.example.pcbuilder.domain.entity.product.Case;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseCaseRepository extends DefaultRepository<Case> {

    @Query("""
            SELECT c FROM Case c
            JOIN Build b ON c.id = b.pcCase.id
            GROUP BY c
            ORDER BY COUNT(b) DESC, SUM(b.averageRate) DESC
            LIMIT 1
            """)
    Optional<Case> findMostPopular();
}
