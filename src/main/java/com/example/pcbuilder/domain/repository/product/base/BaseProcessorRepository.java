package com.example.pcbuilder.domain.repository.product.base;

import com.example.pcbuilder.domain.entity.product.Processor;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseProcessorRepository extends DefaultRepository<Processor> {

    @Query("""
            SELECT p FROM Processor p
            JOIN Build b ON p.id = b.cpu.id
            GROUP BY p
            ORDER BY COUNT(b) DESC, SUM(b.averageRate) DESC
            LIMIT 1
            """)
    Optional<Processor> findMostPopular();
}
