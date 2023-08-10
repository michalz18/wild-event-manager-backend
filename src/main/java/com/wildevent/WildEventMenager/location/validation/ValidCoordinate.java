package com.wildevent.WildEventMenager.location.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CoordinateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCoordinate {

    String message() default "Coordinate must be given in percentage";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
