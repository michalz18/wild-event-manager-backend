package com.wildevent.WildEventMenager.location.model;

import java.util.UUID;


public record LocationPointDto (UUID id, double coordinateX, double coordinateY) {
}
