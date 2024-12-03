package com.example.pcbuilder.domain.repository;

import com.example.pcbuilder.domain.entity.product.Processor;
import com.example.pcbuilder.domain.entity.product.RAM;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRamRepository extends DefaultRepository<RAM> {
}
