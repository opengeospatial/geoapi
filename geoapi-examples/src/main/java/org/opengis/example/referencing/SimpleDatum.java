/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import tech.uom.seshat.Units;

import org.opengis.metadata.citation.Citation;
import org.opengis.example.metadata.SimpleCitation;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.PrimeMeridian;

import static java.lang.Double.doubleToLongBits;


/**
 * A {@link GeodeticDatum} using the <cite>Greenwich</cite> prime meridian. This class does not make
 * distinction between <cite>Geodetic Datum</cite> and <cite>Ellipsoid</cite>, so we implement
 * the two interfaces by the same class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleDatum extends SimpleIdentifiedObject implements GeodeticDatum, Ellipsoid {
    /**
     * The Greenwich prime meridian, implemented as a separated class because ISO 19111
     * requires the name to be <cite>"Greenwich"</cite>.
     */
    private static final class Greenwich extends SimpleIdentifiedObject implements PrimeMeridian {
        /**
         * The unique instance of the Greenwich class.
         */
        static final Greenwich INSTANCE = new Greenwich();

        /**
         * Creates the unique instance of the Greenwich class.
         */
        private Greenwich() {
            super(SimpleCitation.EPSG, "Greenwich");
        }

        /**
         * Returns the Greenwich longitude, which is 0°.
         */
        @Override
        public double getGreenwichLongitude() {
            return 0;
        }

        /**
         * Returns the units of the {@link #getGreenwichLongitude()} measurement.
         */
        @Override
        public Unit<Angle> getAngularUnit() {
            return SimpleAxis.DEGREE;
        }
    }

    /**
     * The WGS84 datum. The {@linkplain #getSemiMajorAxis() semi major axis} length is
     * 6378137.0 metres and the {@linkplain #getInverseFlattening() inverse flattening}
     * factor is 298.257223563.
     */
    public static final GeodeticDatum WGS84 = new SimpleDatum(SimpleCitation.EPSG,
            "World Geodetic System 1984", 6378137.0, 298.257223563);

    /**
     * A spherical datum used for coordinate reference systems where datum is unknown.
     * The axis length are 6371007 metres.
     */
    public static final GeodeticDatum SPHERE = new SimpleDatum(SimpleCitation.EPSG,
            "GRS 1980 Authalic Sphere", 6371007, Double.POSITIVE_INFINITY);

    /**
     * The semi-major axis length, in metres.
     *
     * @see #getSemiMajorAxis()
     */
    protected final double semiMajorAxis;

    /**
     * The inverse flattening factor.
     *
     * @see #getInverseFlattening()
     */
    protected final double inverseFlattening;

    /**
     * Creates a new geodetic datum for the given authority, name, and ellipsoid axis length.
     *
     * @param authority          organization responsible for definition of the name, or {@code null}.
     * @param name               the name of the new CRS.
     * @param semiMajorAxis      the value to be returned by {@link #getSemiMajorAxis()}, in metres.
     * @param inverseFlattening  the value to be returned by {@link #getInverseFlattening()}.
     */
    public SimpleDatum(final Citation authority, final String name,
            final double semiMajorAxis, final double inverseFlattening)
    {
        super(authority, name);
        this.semiMajorAxis = semiMajorAxis;
        this.inverseFlattening = inverseFlattening;
    }

    /**
     * Returns the ellipsoid, which is represented directly by {@code this} implementation
     * class since it does not distinguish geodetic datum and ellipsoid.
     *
     * @return the ellipsoid.
     */
    @Override
    public Ellipsoid getEllipsoid() {
        return this;
    }

    /**
     * Returns the linear unit of the {@linkplain #getSemiMajorAxis semi-major}
     * and {@linkplain #getSemiMinorAxis semi-minor} axis values. The default
     * implementation returns {@link Units#METRE}.
     *
     * @return the axis linear unit.
     */
    @Override
    public Unit<Length> getAxisUnit() {
        return Units.METRE;
    }

    /**
     * Length of the semi-major axis of the ellipsoid. This is the
     * equatorial radius in {@linkplain #getAxisUnit axis linear unit}.
     *
     * @return Length of semi-major axis.
     */
    @Override
    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    /**
     * Length of the semi-minor axis of the ellipsoid. This is the
     * polar radius in {@linkplain #getAxisUnit axis linear unit}.
     *
     * @return Length of semi-minor axis.
     */
    @Override
    public double getSemiMinorAxis() {
        return semiMajorAxis * (1 - 1/inverseFlattening);
    }

    /**
     * Returns the value of the inverse of the flattening constant.
     *
     * @return the inverse flattening value, or {@linkplain Double#POSITIVE_INFINITY positive infinity}
     *         if this ellipsoid is a sphere.
     */
    @Override
    public double getInverseFlattening() {
        return inverseFlattening;
    }

    /**
     * Indicates if the {@linkplain #getInverseFlattening inverse flattening} is definitive for
     * this ellipsoid. The default implementation returns {@code true} for if this ellipsoid is
     * not a sphere, since the constructor of this {@code SimpleDatum} class expects a
     * {@code inverseFlattening} value.
     *
     * @return {@code true} if the {@linkplain #getInverseFlattening() inverse flattening} is definitive,
     *         or {@code false} if the {@linkplain #getSemiMinorAxis() polar radius} is definitive.
     */
    @Override
    public boolean isIvfDefinitive() {
        return !isSphere();
    }

    /**
     * {@code true} if the ellipsoid is degenerate and is actually a sphere.
     *
     * @return {@code true} if the ellipsoid is degenerate and is actually a sphere.
     */
    @Override
    public boolean isSphere() {
        return Double.isInfinite(inverseFlattening);
    }

    /**
     * Returns the prime meridian. The default implementation returns the Greenwich prime meridian.
     *
     * @return the prime meridian.
     */
    @Override
    public PrimeMeridian getPrimeMeridian() {
        return Greenwich.INSTANCE;
    }

    /**
     * Compares this datum with the given object for equality.
     *
     * @param  object The object to compare with this {@code SimpleDatum}.
     * @return {@code true} if the given object is equal to this object.
     */
    @Override
    public boolean equals(final Object object) {
        if (super.equals(object)) {
            final SimpleDatum other = (SimpleDatum) object;
            return doubleToLongBits(semiMajorAxis)     == doubleToLongBits(other.semiMajorAxis) &&
                   doubleToLongBits(inverseFlattening) == doubleToLongBits(other.inverseFlattening);
        }
        return false;
    }
}
