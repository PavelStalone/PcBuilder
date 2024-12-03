package com.example.pcbuilder.domain.repository.product.base;

import com.example.pcbuilder.domain.entity.product.PowerUnit;
import com.example.pcbuilder.domain.entity.product.Processor;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePowerRepository extends DefaultRepository<PowerUnit> {
}
