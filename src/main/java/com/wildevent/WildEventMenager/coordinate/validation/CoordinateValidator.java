package com.wildevent.WildEventMenager.coordinate.validation;

import com.wildevent.WildEventMenager.coordinate.model.CoordinateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class CoordinateValidator implements ConstraintValidator<ValidCoordinate, CoordinateDTO> {

    @Override
    public boolean isValid(CoordinateDTO coordinate, ConstraintValidatorContext constraintValidatorContext) {
        return isLatitude(coordinate.longitude()) && isLongitude(coordinate.latitude());
    }

    private boolean isLatitude(double d) {
        return d >= -90.0 && d<=90.00;
    }
    private boolean isLongitude(double d) {
        return d >= -180.0 && d<=180.00;
    }
}
