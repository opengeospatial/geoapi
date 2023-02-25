/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import org.opengis.referencing.ReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Specification.*;


/**
 * Base type of all Coordinate Reference Systems (CRS).
 * This is the base interface for two cases:
 *
 * <ul>
 *   <li>{@link SingleCRS}, defined by a
 *       {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate system} and a
 *       {@linkplain org.opengis.referencing.datum.Datum datum};</li>
 *   <li>{@link CompoundCRS}, defined as a sequence of {@code SingleCRS}.</li>
 * </ul>
 *
 * <h2>Purpose</h2>
 * A coordinate reference system (CRS) captures the choice of values for the parameters that constitute
 * the degrees of freedom of the coordinate space. The fact that such a choice has to be made,
 * either arbitrarily or by adopting values from survey measurements, leads to the large number
 * of coordinate reference systems in use around the world. It is also the cause of the little
 * understood fact that the latitude and longitude of a point are not unique. Without the full
 * specification of the coordinate reference system, coordinates are ambiguous at best and
 * meaningless at worst.
 *
 * <h2>Spatio-temporal CRS</h2>
 * The concept of coordinates may be expanded from a strictly spatial context to include time.
 * Time is then added as another coordinate to the coordinate tuple. It is even possible to add
 * two time-coordinates, provided the two coordinates describe different independent quantities.
 * An example of the latter is the time/space position of a subsurface point of which the vertical
 * coordinate is expressed as the two-way travel time of a sound signal in milliseconds, as is
 * common in seismic imaging. A second time-coordinate indicates the time of observation, usually
 * expressed in whole years.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SC_CRS", specification=ISO_19111)
public interface CoordinateReferenceSystem extends ReferenceSystem {
    /**
     * Returns the coordinate system of a single CRS, or a view over all coordinate systems of a compound CRS.
     * More specifically:
     *
     * <ul>
     *   <li>If the CRS instance on which this method is invoked is an instance of the
     *       {@link SingleCRS} interface, then the CS instance which is returned shall
     *       be one of the defined sub-interfaces of {@link CoordinateSystem}.</li>
     *
     *   <li>If the CRS instance on which this method is invoked is an instance of the
     *       {@link CompoundCRS} interface, then the CS instance which is returned shall
     *       have a {@linkplain CoordinateSystem#getDimension() dimension} equals to the
     *       sum of the dimensions of all {@linkplain CompoundCRS#getComponents() components},
     *       and axes obtained from the coordinate system of each component.</li>
     * </ul>
     *
     * @return the coordinate system.
     *
     * @departure generalization
     *   ISO 19111 defines this method for {@code SC_SingleCRS} only. GeoAPI declares this method in
     *   this parent interface for user convenience, since CS dimension and axes are commonly requested
     *   information and will always be available, directly or indirectly, even for {@code SC_CompoundCRS}.
     */
    CoordinateSystem getCoordinateSystem();
}
