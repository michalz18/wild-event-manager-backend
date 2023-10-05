package com.wildevent.WildEventMenager.event.validation;

import com.wildevent.WildEventMenager.event.model.DateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class DateRangeValidator implements ConstraintValidator<ValidDateRange, DateRange> {

    @Override
    public boolean isValid(DateRange value, ConstraintValidatorContext context) {
        return value.getStartsAt().isBefore(value.getEndsAt());
    }
}
