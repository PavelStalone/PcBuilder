package com.example.pcbuilder.domain.repository.product.base;

import com.example.pcbuilder.domain.entity.product.PowerUnit;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasePowerRepository extends DefaultRepository<PowerUnit> {

    @Query("""
            SELECT p FROM PowerUnit p
            JOIN Build b ON p.id = b.powerUnit.id
            GROUP BY p
            ORDER BY COUNT(b) DESC, SUM(b.averageRate) DESC
            LIMIT 1
            """)
    Optional<PowerUnit> findMostPopular();
}
