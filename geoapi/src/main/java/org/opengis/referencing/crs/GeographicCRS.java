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

import java.util.Map;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 2- or 3-dimensional <abbr>CRS</abbr> based on an ellipsoidal approximation of the geoid.
 * This provides an accurate representation of the geometry of geographic features for a large portion of the earth's surface.
 * A 2D geographic <abbr>CRS</abbr> is used when positions of features are described on the surface of the reference ellipsoid.
 * A 3D geographic <abbr>CRS</abbr> is used when positions are described on, above or below the reference ellipsoid.
 *
 * <p>A geographic <abbr>CRS</abbr> is not suitable for mapmaking on a planar surface,
 * because it describes geometry on a curved surface.
 * It is impossible to represent such geometry in a Euclidean plane without introducing distortions.
 * The need to control these distortions has given rise to the development of the science of
 * {@linkplain org.opengis.referencing.operation.Projection map projections}.</p>
 *
 * <p>This type of CRS can be used with coordinate systems of type
 * {@link org.opengis.referencing.cs.EllipsoidalCS}.</p>
 *
 * @departure constraint
 *   This interface is kept conformant with the specification published in 2003. The 2007 revision
 *   of ISO 19111 removed the {@code SC_GeographicCRS} and {@code SC_GeocentricCRS} types,
 *   handling both using the {@code SC_GeodeticCRS} parent type. GeoAPI keeps them for two reasons:
 *   <ul>
 *     <li>The distinction between those two types is in wide use.</li>
 *     <li>A distinct geographic type allows GeoAPI to restrict the coordinate system type to {@code EllipsoidalCS}.
 *         ISO 19111 uses a {@code union} for expressing this restriction at the {@code SC_GeodeticCRS} level, but
 *         the Java language does not provide such construct. A distinct geographic type is one way to achieve the
 *         same goal.</li>
 *   </ul>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.0
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createGeographicCRS(String)
 * @see CRSFactory#createGeographicCRS(Map, GeodeticDatum, EllipsoidalCS)
 */
@UML(identifier="SC_GeographicCRS", specification=ISO_19111, version=2003)
public interface GeographicCRS extends GeodeticCRS {
    /**
     * Returns the coordinate system, which shall be ellipsoidal.
     *
     * @return the ellipsoidal coordinate system.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    EllipsoidalCS getCoordinateSystem();
}
