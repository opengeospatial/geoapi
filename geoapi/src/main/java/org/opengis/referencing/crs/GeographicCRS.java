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
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 2- or 3-dimensional <abbr>CRS</abbr> based on an ellipsoidal approximation of the geoid.
 * This provides an accurate representation of the geometry of geographic features for a large portion of the planet's surface.
 * A 2D geographic <abbr>CRS</abbr> is used when positions of features are described on the surface of the reference ellipsoid.
 * A 3D geographic <abbr>CRS</abbr> is used when positions are described on, above or below the reference ellipsoid.
 *
 * <p>A geographic <abbr>CRS</abbr> is not suitable for mapmaking on a planar surface,
 * because it describes geometry on a curved surface.
 * It is impossible to represent such geometry in a Euclidean plane without introducing distortions.
 * The need to control these distortions has given rise to the development of {@link ProjectedCRS}s.</p>
 *
 * <p>The {@link GeodeticDatum} associated to this <abbr>CRS</abbr> must have an {@link Ellipsoid}.
 * I.e., the ellipsoid is generally optional but become mandatory in the context of {@code GeographicCRS}.</p>
 *
 * <h2>Permitted coordinate systems</h2>
 * This type of <abbr>CRS</abbr> can be used with coordinate systems of type {@link EllipsoidalCS} only.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createGeographicCRS(String)
 * @see CRSFactory#createGeographicCRS(Map, GeodeticDatum, EllipsoidalCS)
 */
@UML(identifier="GeographicCRS", specification=ISO_19111)
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
