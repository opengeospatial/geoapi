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

import org.opengis.coverage.GeometryValuePair;
import org.opengis.util.Record;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain GeometryValuePair geometry value pair} that has a {@linkplain GridPoint grid point}
 * as the value of its geometry attribute.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_GridPointValuePair", specification=ISO_19123)
public interface GridPointValuePair extends GeometryValuePair {
    /**
     * The grid point that is a member of this <var>grid point</var>-<var>value</var> pair.
     * It is one of the {@linkplain GridPoint grid points} linked to the
     * {@linkplain GridValuesMatrix grid value matrix} through {@link Grid#getIntersections}.
     *
     * @return the geometry member of the pair.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    GridPoint getGeometry();

    /**
     * Holds the record of feature attribute values associated with the grid point.
     *
     * @return the value member of the pair.
     */
    @UML(identifier="value", obligation=MANDATORY, specification=ISO_19123)
    Record getValue();
}
