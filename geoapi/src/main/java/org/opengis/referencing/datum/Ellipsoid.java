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
package org.opengis.referencing.datum;

import java.util.Map;
import java.util.OptionalDouble;
import javax.measure.Unit;
import javax.measure.quantity.Length;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Geometric figure that can be used to describe the approximate shape of the earth.
 * In mathematical terms, it is a surface formed by the rotation of an ellipse about
 * its minor axis. An ellipsoid requires two or three defining parameters:
 *
 * <ul>
 *   <li>One of the following:
 *     <ul>
 *       <li>{@linkplain #getSemiMajorAxis() semi-major axis} and
 *           {@linkplain #getInverseFlattening() inverse flattening}, or</li>
 *       <li>{@linkplain #getSemiMajorAxis() semi-major axis} and
 *           {@linkplain #getSemiMinorAxis() semi-minor axis}.</li>
 *     </ul>
 *   </li>
 *   <li>Optionally a semi-median axis (for planetary applications).</li>
 * </ul>
 *
 * There is not just one ellipsoid. An ellipsoid is a matter of choice, and therefore many
 * choices are possible. The size and shape of an ellipsoid was traditionally chosen such
 * that the surface of the geoid is matched as closely as possible locally, e.g. in a country.
 * A number of global best-fit ellipsoids are now available. An association of an ellipsoid with
 * the earth is made through the definition of the size and shape of the ellipsoid and the position
 * and orientation of this ellipsoid with respect to the earth. Collectively this choice is captured
 * by the concept of "{@linkplain GeodeticDatum geodetic datum}". A change of size, shape, position
 * or orientation of an ellipsoid will result in a change of geographic coordinates of a point and
 * be described as a different geodetic datum. Conversely geographic coordinates are unambiguous
 * only when associated with a geodetic datum.
 *
 * @departure constraint
 *   ISO 19111 defines the union named {@code secondDefiningParameter}
 *   as being either {@code semiMinorAxis} or {@code inverseFlattening}.
 *   The {@code union} construct (defined in some languages like C/C++) does not exist in Java.
 *   GeoAPI changed the interface to require both ellipsoidal parameters (in addition to the {@code semiMajorAxis}
 *   parameter which is mandatory in any case), as was done in <a href="http://www.opengeospatial.org/standards/ct">OGC 01-009</a>.
 *   However, implementers could readily permit users to only provide one of the two parameters
 *   by creating a class which calculates the second parameter from the first.
 *   For precision, GeoAPI imports the {@code isIvfDefinitive} attribute from OGC 01-009
 *   to enable the user to establish which of the two parameters was used to define the instance.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createEllipsoid(String)
 * @see DatumFactory#createEllipsoid(Map, double, double, Unit)
 * @see DatumFactory#createFlattenedSphere(Map, double, double, Unit)
 */
@UML(identifier="CD_Ellipsoid", specification=ISO_19111, version=2007)
public interface Ellipsoid extends IdentifiedObject {
    /**
     * Returns the linear unit of the {@linkplain #getSemiMajorAxis() semi-major}
     * and {@linkplain #getSemiMinorAxis() semi-minor} axis values.
     *
     * @return the axis linear unit.
     */
    @UML(identifier="getAxisUnit", specification=OGC_01009)
    Unit<Length> getAxisUnit();

    /**
     * Length of the semi-major axis of the ellipsoid. This is the
     * equatorial radius in {@linkplain #getAxisUnit() axis linear unit}.
     *
     * @return length of semi-major axis.
     * @unitof Length
     */
    @UML(identifier="semiMajorAxis", obligation=MANDATORY, specification=ISO_19111)
    double getSemiMajorAxis();

    /**
     * Length of the semi-median axis of a triaxial ellipsoid.
     * This parameter is not required for a biaxial ellipsoid.
     * The default implementation returns an empty value.
     *
     * @return length of the semi-median axis of a triaxial ellipsoid.
     *
     * @since 3.1
     */
    @UML(identifier="semiMedianAxis", obligation=OPTIONAL, specification=ISO_19111)
    default OptionalDouble getSemiMedianAxis() {
        return OptionalDouble.empty();
    }

    /**
     * Length of the semi-minor axis of the ellipsoid. This is the
     * polar radius in {@linkplain #getAxisUnit() axis linear unit}.
     *
     * @return length of semi-minor axis.
     * @unitof Length
     */
    @UML(identifier="secondDefiningParameter.semiMinorAxis", obligation=CONDITIONAL, specification=ISO_19111)
    double getSemiMinorAxis();

    /**
     * Returns the value of the inverse of the flattening constant. The inverse
     * flattening is related to the equatorial/polar radius by the formula
     *
     * <var>ivf</var>&nbsp;=&nbsp;<var>r</var><sub>e</sub>/(<var>r</var><sub>e</sub>-<var>r</var><sub>p</sub>).
     *
     * For perfect spheres (i.e. if {@link #isSphere()} returns {@code true}),
     * the {@link Double#POSITIVE_INFINITY POSITIVE_INFINITY} value is used.
     *
     * @return the inverse flattening value.
     * @unitof Scale
     */
    @UML(identifier="secondDefiningParameter.inverseFlattening", obligation=CONDITIONAL, specification=ISO_19111)
    double getInverseFlattening();

    /**
     * Indicates if the {@linkplain #getInverseFlattening() inverse flattening} is definitive for
     * this ellipsoid. Some ellipsoids use the IVF as the defining value, and calculate the polar
     * radius whenever asked. Other ellipsoids use the polar radius to calculate the IVF whenever
     * asked. This distinction can be important to avoid floating-point rounding errors.
     *
     * @return {@code true} if the {@linkplain #getInverseFlattening() inverse flattening} is
     *         definitive, or {@code false} if the {@linkplain #getSemiMinorAxis() polar radius}
     *         is definitive.
     */
    @UML(identifier="CS_Ellipsoid.isIvfDefinitive", obligation=CONDITIONAL, specification=OGC_01009)
    boolean isIvfDefinitive();

    /**
     * {@code true} if the ellipsoid is degenerate and is actually a sphere. The sphere is
     * completely defined by the {@linkplain #getSemiMajorAxis() semi-major axis}, which is
     * the radius of the sphere.
     *
     * @return {@code true} if the ellipsoid is degenerate and is actually a sphere.
     */
    @UML(identifier="secondDefiningParameter.isSphere", obligation=CONDITIONAL, specification=ISO_19111)
    boolean isSphere();
}
