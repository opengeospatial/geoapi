/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.datum;

// J2SE direct dependencies and extensions
import org.opengis.crs.Info;
import javax.units.Unit;

/**
 * Geometric figure that can be used to describe the approximate shape of the earth.
 * In mathematical terms, it is a surface formed by the rotation of an ellipse about
 * its minor axis. An ellipsoid requires two defining parameters:
 * <ul>
 *   <li>{@linkplain #getSemiMajorAxis semi-major axis} and
 *       {@linkplain #getInverseFlattening inverse flattening}, or</li>
 *   <li>{@linkplain #getSemiMajorAxis semi-major axis} and
 *       {@linkplain #getSemiMinorAxis semi-minor axis}.</li>
 * </ul>
 *
 * @UML abstract CD_Ellipsoid
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface Ellipsoid extends Info {
    /**
     * Returns the linear unit of the {@linkplain #getSemiMajorAxis semi-major}
     * and {@linkplain #getSemiMinorAxis semi-minor} axis values.
     *
     * @return The axis linear unit.
     */
    public Unit getAxisUnit();

    /**
     * Length of the semi-major axis of the ellipsoid. This is the
     * equatorial radius in {@linkplain #getAxisUnit axis linear unit}.
     *
     * @return Length of semi-major axis.
     * @unitof Length
     * @UML mandatory semiMajorAxis
     */
    public double getSemiMajorAxis();

    /**
     * Length of the semi-minor axis of the ellipsoid. This is the
     * polar radius in {@linkplain #getAxisUnit axis linear unit}.
     *
     * @return Length of semi-minor axis.
     * @unitof Length
     * @UML conditional secondDefiningParameter.semiMinorAxis
     */
    public double getSemiMinorAxis();

    /**
     * Returns the value of the inverse of the flattening constant. The inverse
     * flattening is related to the equatorial/polar radius by the formula
     *
     * <var>ivf</var>&nbsp;=&nbsp;<var>r</var><sub>e</sub>/(<var>r</var><sub>e</sub>-<var>r</var><sub>p</sub>).
     *
     * For perfect spheres (i.e. if {@link #isSphere} returns <code>true</code>),
     * the {@link Double#POSITIVE_INFINITY} value is used.
     *
     * @return The inverse flattening value.
     * @unitof Scale
     * @UML conditional secondDefiningParameter.inverseFlattening
     */
    public double getInverseFlattening();

    /**
     * Indicates if the {@linkplain #getInverseFlattening inverse flattening} is definitive for
     * this ellipsoid. Some ellipsoids use the IVF as the defining value, and calculate the polar
     * radius whenever asked. Other ellipsoids use the polar radius to calculate the IVF whenever
     * asked. This distinction can be important to avoid floating-point rounding errors.
     *
     * @return <code>true</code> if the {@linkplain #getInverseFlattening inverse flattening} is
     *         definitive, or <code>false</code> if the {@linkplain #getSemiMinorAxis polar radius}
     *         is definitive.
     */
    public boolean isIvfDefinitive();

    /**
     * <code>true</code> if the ellipsoid is degenerate and is actually a sphere. The sphere is
     * completely defined by the {@linkplain #getSemiMajorAxis semi-major axis}, which is the
     * radius of the sphere.
     *
     * @return <code>true</code> if the ellipsoid is degenerate and is actually a sphere.
     * @UML conditional secondDefiningParameter.isSphere
     */
    public boolean isSphere();
}
