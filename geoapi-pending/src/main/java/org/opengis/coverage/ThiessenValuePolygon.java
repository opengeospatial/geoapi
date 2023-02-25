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
package org.opengis.coverage;

import java.util.Set;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A value from a {@linkplain ThiessenPolygonCoverage Thiessen polygon coverage}.
 * Individual Thiessen value polygons may be generated during the evaluation of a
 * Thiessen polygon coverage, and need not to be persistent.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_ThiessenValuePolygon", specification=ISO_19123)
public interface ThiessenValuePolygon extends ValueObject {
    /**
     * Returns the <var>point</var>-<var>value</var> pair at the polygon centre.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends PointValuePair> getControlValues();

    /**
     * Returns the geometry of the Thiessen polygon centred on the {@linkplain PointValuePair
     * point-value pair} identified by the {@linkplain #getControlValues control values}.
     *
     * @todo The ISO 19123 specification returns a {@link org.opengis.geometry.coordinate.Polygon}.
     *       We will have some trouble here, since {@code Polygon} is not a
     *       {@link org.opengis.geometry.Geometry}.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject getGeometry();
}
