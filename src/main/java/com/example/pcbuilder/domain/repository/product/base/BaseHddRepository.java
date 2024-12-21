package com.example.pcbuilder.domain.repository.product.base;

import com.example.pcbuilder.domain.entity.product.HDD;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseHddRepository extends DefaultRepository<HDD> {

    @Query("""
            SELECT h FROM HDD h
            JOIN Build b ON h.id = b.hdd.id
            GROUP BY h
            ORDER BY COUNT(b) DESC, SUM(b.averageRate) DESC
            LIMIT 1
            """)
    Optional<HDD> findMostPopular();
}
