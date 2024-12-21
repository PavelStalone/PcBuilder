package com.example.pcbuilder.domain.repository.product.base;

import com.example.pcbuilder.domain.entity.product.RAM;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseRamRepository extends DefaultRepository<RAM> {

    @Query("""
            SELECT r FROM RAM r
            JOIN Build b ON r.id = b.ram.id
            GROUP BY r
            ORDER BY COUNT(b) DESC, SUM(b.averageRate) DESC
            LIMIT 1
            """)
    Optional<RAM> findMostPopular();
}
