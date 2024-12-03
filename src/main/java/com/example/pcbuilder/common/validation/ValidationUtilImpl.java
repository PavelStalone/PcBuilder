package com.example.pcbuilder.common.validation;

import com.example.pcbuilder.common.log.Log;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator;

    @Autowired
    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <D> boolean isValid(D item) {
        return validator.validate(item).size() == 0;
    }

    @Override
    public <D> Set<ConstraintViolation<D>> violations(D item) {
        return validator.validate(item);
    }

    @Override
    public <D> boolean checkItem(D item) {
        if (!isValid(item)) {
            violations(item)
                    .stream()
                    .map((violation) -> violation.getPropertyPath().toString() + "\t" + violation.getMessage())
                    .forEach(Log::e);

            return false;
        }

        return true;
    }
}
