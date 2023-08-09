package com.wildevent.WildEventMenager.location.validation;

import com.wildevent.WildEventMenager.location.model.Coordinate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class CoordinateValidator implements ConstraintValidator<ValidCoordinate, Coordinate> {

    @Override
    public boolean isValid(Coordinate coordinate, ConstraintValidatorContext constraintValidatorContext) {
        return isAPercentage(coordinate.getCoordinateY()) && isAPercentage(coordinate.getCoordinateX());
    }

    private boolean isAPercentage(double d) {
        return d >= 0.0 && d<=100.00;
    }
}
