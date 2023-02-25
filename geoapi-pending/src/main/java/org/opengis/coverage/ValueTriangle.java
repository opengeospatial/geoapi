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
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.coordinate.Triangle;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain ValueObject value object} that consists of three {@linkplain PointValuePair
 * point-value pairs} where the points are non-collinear. Value triangles are used for interpolation
 * of a coverage.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_ValueTriangle", specification=ISO_19123)
public interface ValueTriangle extends ValueObject {
    /**
     * Returns the triangle that defines the relative position of the three
     * {@linkplain PointValuePair point-value pairs} at its vertices.
     *
     * @return the geometry as a triangle.
     *
     * @todo The returns type in ISO 19123 is {@link Triangle}.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject<?> getGeometry();

    /**
     * Returns the three <var>point</var>-<var>value</var> pairs at the vertices of this
     * value triangle.
     *
     * @return the control values.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<PointValuePair> getControlValues();

    /**
     * Returns the barycentric coordinates of the specified position as a sequence of numbers.
     *
     * @param p The position where to calculate.
     * @return the barycentric coordinates.
     */
    @UML(identifier="point", obligation=MANDATORY, specification=ISO_19123)
    double[] point(DirectPosition p);
}
