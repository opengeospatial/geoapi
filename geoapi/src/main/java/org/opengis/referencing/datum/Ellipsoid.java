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
 * Geometric figure that can be used to describe the approximate shape of a planet.
 * For the Earth the ellipsoid is bi-axial with rotation about the polar axis.
 * For other planet, the ellipsoid may be tri-axial.
 * An ellipsoid requires two or three defining parameters:
 *
 * <ul>
 *   <li>{@linkplain #getSemiMajorAxis() Semi-major axis}.</li>
 *   <li>One of the following:
 *     <ul>
 *       <li>{@linkplain #getSemiMinorAxis() semi-minor axis}, or</li>
 *       <li>{@linkplain #getInverseFlattening() inverse flattening}.</li>
 *     </ul>
 *   </li>
 *   <li>Optionally a semi-median axis (for planetary applications).</li>
 * </ul>
 *
 * For some applications, for example small-scale mapping in atlases, a spherical approximation
 * of the geoid's surface is used, requiring only the radius of the sphere to be specified.
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
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createEllipsoid(String)
 * @see DatumFactory#createEllipsoid(Map, double, double, Unit)
 * @see DatumFactory#createFlattenedSphere(Map, double, double, Unit)
 */
@UML(identifier="Ellipsoid", specification=ISO_19111)
public interface Ellipsoid extends IdentifiedObject {
    /**
     * Returns the linear unit of the semi-major, semi-minor and semi-median axis values.
     *
     * @return the axis linear unit.
     */
    @UML(identifier="getAxisUnit", specification=OGC_01009)
    Unit<Length> getAxisUnit();

    /**
     * Length of the semi-major axis of the ellipsoid.
     * This is the equatorial radius in {@linkplain #getAxisUnit() axis linear unit}.
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
     * Length of the semi-minor axis of the ellipsoid.
     * This is the polar radius in {@linkplain #getAxisUnit() axis linear unit}.
     *
     * @return length of semi-minor axis.
     * @unitof Length
     */
    @UML(identifier="secondDefiningParameter.semiMinorAxis", obligation=CONDITIONAL, specification=ISO_19111)
    double getSemiMinorAxis();

    /**
     * Returns the value of the inverse of the flattening constant.
     * The inverse flattening is related to the equatorial/polar radius by the formula
     *
     * <var>ivf</var>&nbsp;=&nbsp;<var>r</var><sub>e</sub>/(<var>r</var><sub>e</sub>-<var>r</var><sub>p</sub>).
     *
     * For perfect spheres (i.e. if {@link #isSphere()} returns {@code true}),
     * the {@link Double#POSITIVE_INFINITY} value is used.
     *
     * @return the inverse flattening value.
     * @unitof Scale
     */
    @UML(identifier="secondDefiningParameter.inverseFlattening", obligation=CONDITIONAL, specification=ISO_19111)
    double getInverseFlattening();

    /**
     * Indicates if the inverse flattening (<abbr>IVF</abbr>) is definitive for this ellipsoid.
     * Some ellipsoids use the <abbr>IVF</abbr> as the defining value, and calculate the polar
     * radius whenever asked. Other ellipsoids use the polar radius to calculate the <abbr>IVF</abbr>
     * whenever asked. This distinction can be important to avoid floating-point rounding errors.
     *
     * @return {@code true} if the {@linkplain #getInverseFlattening() inverse flattening} is definitive,
     *         or {@code false} if the {@linkplain #getSemiMinorAxis() polar radius} is definitive.
     */
    @UML(identifier="CS_Ellipsoid.isIvfDefinitive", obligation=MANDATORY, specification=OGC_01009)
    boolean isIvfDefinitive();

    /**
     * {@code true} if the ellipsoid is degenerate and is actually a sphere.
     * The sphere is completely defined by the {@linkplain #getSemiMajorAxis() semi-major axis},
     * which is the radius of the sphere.
     *
     * @return {@code true} if the ellipsoid is degenerate and is actually a sphere.
     */
    @UML(identifier="secondDefiningParameter.isSphere", obligation=CONDITIONAL, specification=ISO_19111)
    default boolean isSphere() {
        return getSemiMedianAxis().isEmpty() && (isIvfDefinitive()
                ? Double.isInfinite(getInverseFlattening())
                : getSemiMinorAxis() == getSemiMajorAxis());
    }
}
