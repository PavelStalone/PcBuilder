package com.example.pcbuilder.domain.repository;

import com.example.pcbuilder.domain.entity.product.PowerUnit;
import com.example.pcbuilder.domain.entity.product.Processor;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePowerRepository extends DefaultRepository<PowerUnit> {
}
