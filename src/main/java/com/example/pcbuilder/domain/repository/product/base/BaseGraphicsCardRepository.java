package com.example.pcbuilder.domain.repository.product.base;

import com.example.pcbuilder.domain.entity.product.GraphicsCard;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseGraphicsCardRepository extends DefaultRepository<GraphicsCard> {

    @Query("""
            SELECT g FROM GraphicsCard g
            JOIN Build b ON g.id = b.gpu.id
            GROUP BY g
            ORDER BY COUNT(b) DESC, SUM(b.averageRate) DESC
            LIMIT 1
            """)
    Optional<GraphicsCard> findMostPopular();
}
