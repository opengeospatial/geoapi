/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Takes a standard geometric construction and places it in geographic space.
 * This interface defines a transformation from a constructive parameter space
 * to the coordinate space of the coordinate reference system being used. Parameter
 * spaces in formulae are given as (<var>u</var>, <var>v</var>) in 2D and
 * (<var>u</var>, <var>v</var>, <var>w</var>) in 3D. Coordinate reference systems
 * positions are given in formulae by either (<var>x</var>, <var>y</var>) in 2D,
 * or (<var>x</var>, <var>y</var>, <var>z</var>) in 3D.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @todo Retrofit in {@link org.opengis.referencing.operation.MathTransform}?
 */
@UML(identifier="GM_Placement", specification=ISO_19107)
public interface Placement {
    /**
     * Return the dimension of the input parameter space.
     */
    @UML(identifier="inDimension", obligation=MANDATORY, specification=ISO_19107)
    int getInDimension();

    /**
     * Return the dimension of the output coordinate reference system.
     * Normally, {@code outDimension} (the dimension of the coordinate reference system)
     * is larger than {@code inDimension}. If this is not the case, the transformation is
     * probably singular, and may be replaceable by a simpler one from a smaller dimension
     * parameter space.
     */
    @UML(identifier="outDimension", obligation=MANDATORY, specification=ISO_19107)
    int getOutDimension();

    /**
     * Maps the parameter coordinate points to the coordinate points in the output Cartesian space.
     *
     * @param  in Input coordinate points. The length of this vector shall be equal to {@link #getInDimension inDimension}.
     * @return the output coordinate points. The length of this vector is equal to {@link #getOutDimension outDimension}.
     */
    @UML(identifier="transform", obligation=MANDATORY, specification=ISO_19107)
    double[] transform (double[] in);
}
