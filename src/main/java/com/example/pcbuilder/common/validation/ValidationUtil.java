package com.example.pcbuilder.common.validation;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {

    <D> boolean isValid(D item);

    <D> Set<ConstraintViolation<D>> violations(D item);

    <D> boolean checkItem(D item);
}
