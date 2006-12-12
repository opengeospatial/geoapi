package org.opengis.catalog.discovery;

import java.util.Set;

public interface QueryResponse {

    Set getRetrievedData();

    int getCursorPosition();

    int getHits();

}