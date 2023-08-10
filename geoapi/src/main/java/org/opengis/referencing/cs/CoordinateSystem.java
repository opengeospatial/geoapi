/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
package org.opengis.referencing.cs;

import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The set of coordinate system axes that spans a given coordinate space. A coordinate system (CS)
 * is derived from a set of (mathematical) rules for specifying how coordinates in a given space
 * are to be assigned to points. The coordinate values in a coordinate tuple shall be recorded in
 * the order in which the coordinate system axes associations are recorded, whenever those
 * coordinates use a coordinate reference system that uses this coordinate system.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CoordinateSystemAxis
 * @see org.opengis.referencing.datum.Datum
 * @see org.opengis.referencing.crs.CoordinateReferenceSystem
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="CS_CoordinateSystem", specification=ISO_19111, version=2007)
public interface CoordinateSystem extends IdentifiedObject {
    /**
     * Returns the dimension of the coordinate system.
     *
     * @return the dimension of the coordinate system.
     */
    int getDimension();

    /**
     * Returns the axis for this coordinate system at the specified dimension.
     * Each coordinate system must have at least one axis.
     *
     * @param  dimension  the zero based index of axis.
     * @return the axis at the specified dimension.
     * @throws IndexOutOfBoundsException if {@code dimension} is out of bounds.
     */
    @UML(identifier="axis", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystemAxis getAxis(int dimension) throws IndexOutOfBoundsException;
}
