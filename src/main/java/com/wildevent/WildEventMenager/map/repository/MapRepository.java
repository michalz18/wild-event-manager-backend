package com.wildevent.WildEventMenager.map.repository;

import java.io.IOException;

public interface MapRepository {
    byte[] getMap() throws IOException;
}
