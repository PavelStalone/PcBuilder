package com.example.pcbuilder.domain.repository.product.base;

import com.example.pcbuilder.domain.entity.product.Motherboard;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseMotherboardRepository extends DefaultRepository<Motherboard> {

    @Query("""
            SELECT m FROM Motherboard m
            JOIN Build b ON m.id = b.motherboard.id
            GROUP BY m
            ORDER BY COUNT(b) DESC, SUM(b.averageRate) DESC
            LIMIT 1
            """)
    Optional<Motherboard> findMostPopular();
}
