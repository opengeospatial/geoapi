/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.coverage.grid;

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Contains information for mapping {@linkplain GridCoordinates grid coordinates} to a position
 * within the sequence of records of feature attribute values.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_SequenceRule", specification=ISO_19123)
public interface SequenceRule {
    /**
     * Identifies the type of sequencing method that shall be used.
     * The default value shall be "{@linkplain SequenceType#LINEAR linear}".
     *
     * @return the type of sequencing method.
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19123)
    SequenceType getType();

    /**
     * Returns a list of signed {@linkplain Grid#getAxisNames axis names} that indicates the order
     * in which {@linkplain GridPoint grid points} shall be mapped to position within the sequence
     * of records of feature attribute values. An additional element may be included in the list to
     * allow for interleaving of feature attribute values.
     * Example: <code>{"x", "-y"}</code>
     *
     * @return an ordered list of axis names that indicates the scanning direction.
     */
    @UML(identifier="scanDirection", obligation=MANDATORY, specification=ISO_19123)
    List<String> getScanDirection();
}
