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
import org.opengis.geometry.primitive.Curve;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Limits of a value segment specified by two values of the arc-length parameter of the
 * {@linkplain Curve curve} underlying its parent {@linkplain ValueCurve value curve}.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_ValueSegment", specification=ISO_19123)
public interface ValueSegment {
    /**
     * Returns the value of the arc-length parameter of the parent
     * curve at the start of this value segment.
     *
     * @return the value at the start of this segment.
     */
    @UML(identifier="startParameter", obligation=MANDATORY, specification=ISO_19123)
    double getStartParameter();

    /**
     * Returns the value of the arc-length parameter of the parent
     * curve at the end of this value segment.
     *
     * @return the value at the end of this segment.
     */
    @UML(identifier="endParameter", obligation=MANDATORY, specification=ISO_19123)
    double getEndParameter();

    /**
     * Returns the set of <var>point</var>-<var>value</var> pairs that provide control
     * values for the interpolation. Linear interpolation requires a minimum of two control
     * values, usually those at the beginning and end of the value segment. Additional
     * control values are required to support interpolation by higher order functions.
     *
     * @return the set of control points and values.
     */
    @UML(identifier="controlPoint", obligation=MANDATORY, specification=ISO_19123)
    Set<PointValuePair> getControlPoints();
}
