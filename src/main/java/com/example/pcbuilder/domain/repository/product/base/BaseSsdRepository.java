package com.example.pcbuilder.domain.repository.product.base;

import com.example.pcbuilder.domain.entity.product.SSD;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseSsdRepository extends DefaultRepository<SSD> {

    @Query("""
            SELECT s FROM SSD s
            JOIN Build b ON s.id = b.ssd.id
            GROUP BY s
            ORDER BY COUNT(b) DESC, SUM(b.averageRate) DESC
            LIMIT 1
            """)
    Optional<SSD> findMostPopular();
}
