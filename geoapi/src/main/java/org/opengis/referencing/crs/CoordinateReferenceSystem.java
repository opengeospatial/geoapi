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
package org.opengis.referencing.crs;

import org.opengis.referencing.ReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Specification.*;


/**
 * Base type of all Coordinate Reference Systems (<abbr>CRS</abbr>).
 * This is the base interface for two cases:
 *
 * <ul>
 *   <li>{@link SingleCRS}, defined by a
 *       {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate system} and a
 *       {@linkplain org.opengis.referencing.datum.Datum datum} or datum ensemble;</li>
 *   <li>{@link CompoundCRS}, defined as a sequence of {@code SingleCRS}.</li>
 * </ul>
 *
 * <h2>Purpose</h2>
 * A coordinate reference system (<abbr>CRS</abbr>) captures the choice of values
 * for the parameters that constitute the degrees of freedom of the coordinate space.
 * The fact that such a choice has to be made, either arbitrarily or by adopting values from survey measurements,
 * leads to the large number of coordinate reference systems in use around the world.
 * It is also the cause of the little understood fact that the latitude and longitude of a point are not unique.
 * Without the full specification of the coordinate reference system,
 * coordinates are ambiguous at best and meaningless at worst.
 *
 * <h2>Spatiotemporal <abbr>CRS</abbr></h2>
 * The concept of coordinates may be expanded from a strictly spatial context to include time.
 * Time is then added as another coordinate to the coordinate tuple. It is even possible to add
 * two time-coordinates, provided the two coordinates describe different independent quantities.
 * An example of the latter is the time/space position of a subsurface point of which the vertical
 * coordinate is expressed as the two-way travel time of a sound signal in milliseconds, as is
 * common in seismic imaging. A second time-coordinate indicates the time of observation.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="CRS", specification=ISO_19111)
public interface CoordinateReferenceSystem extends ReferenceSystem {
    /**
     * Returns the coordinate axes with specified units of measure.
     * The type of the returned coordinate system <em>should</em> be one of the sub-interfaces defined in
     * the {@link org.opengis.referencing.cs} package. The subtype implies the mathematical rules that define
     * how coordinate values are calculated from distances, angles and other geometric elements and vice versa.
     *
     * <p>An exception to above recommendation is when this <abbr>CRS</abbr> is an instance of {@link CompoundCRS}.
     * In that case, the coordinate system type may be hidden and the implied mathematical rules are unspecified.
     * However the coordinate system object is still useful as a list of axes.</p>
     *
     * @return the coordinate axes with specified units of measure.
     *
     * @departure generalization
     *   ISO 19111 defines this method for {@link SingleCRS} only. GeoAPI declares this method in this parent interface
     *   for user convenience, because <abbr>CRS</abbr> dimension and axes are commonly requested information and are
     *   always available, directly or indirectly, even for {@link CompoundCRS}.
     */
    CoordinateSystem getCoordinateSystem();
}
