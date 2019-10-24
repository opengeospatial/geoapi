/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Objects;
import javax.measure.Unit;
import javax.measure.quantity.Length;

import ucar.unidata.geoloc.Earth;
import ucar.unidata.geoloc.EarthEllipsoid;

import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.crs.GeographicCRS;


/**
 * An {@link Ellipsoid} implementation backed by a netCDF {@link Earth} object.
 * This class implements also the {@link GeographicCRS} interface for allowing
 * use of CRS defined only by the netCDF {@code Earth} object.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class NetcdfEllipsoid extends NetcdfIdentifiedObject
        implements Ellipsoid, GeodeticDatum, EllipsoidalCS, GeographicCRS
{
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 5097453085614236216L;

    /**
     * The WGS84 datum and ellipsoid.
     *
     * @see EarthEllipsoid#WGS84
     */
    public static final NetcdfEllipsoid WGS84 = new NetcdfEllipsoid(EarthEllipsoid.WGS84);

    /**
     * The Airy 1830 datum and ellipsoid.
     *
     * @see EarthEllipsoid#Airy1830
     */
    public static final NetcdfEllipsoid AIRY_1830 = new NetcdfEllipsoid(EarthEllipsoid.Airy1830);

    /**
     * A sphere used when the datum is unknown. This is also the sphere used by the projection
     * code in the {@link ucar.unidata.geoloc.projection} package.
     *
     * @see Earth#getRadius()
     */
    public static final NetcdfEllipsoid SPHERE = new NetcdfEllipsoid(new Earth(Earth.getRadius()));

    /**
     * The netCDF earth wrapped by this {@code NetcdfEllipsoid} instance.
     */
    private final Earth earth;

    /**
     * Creates a new {@code NetcdfEllipsoid} object wrapping the given netCDF earth.
     *
     * @param earth The netCDF earth to wrap.
     */
    public NetcdfEllipsoid(final Earth earth) {
        Objects.requireNonNull(earth);
        this.earth = earth;
    }

    /**
     * Returns the wrapped netCDF earth.
     *
     * @return the earth representation from UCAR library.
     */
    @Override
    public Earth delegate() {
        return earth;
    }

    /**
     * Returns the earth name. The default implementation delegates to {@link Earth#getName()}.
     *
     * @return Earth#getName()
     */
    @Override
    public String getCode() {
        return earth.getName();
    }

    /**
     * Returns the linear unit of the {@linkplain #getSemiMajorAxis semi-major}
     * and {@linkplain #getSemiMinorAxis semi-minor} axis values.
     * The default implementation returns {@link Units#METRE}.
     *
     * <p><b>Do not confuse:</b></p>
     * <ul>
     *   <li>Ellipsoidal coordinate system axes (λ,φ) in degrees</li>
     *   <li>Ellipsoid axes (<var>semi-major</var>, <var>semi-minor</var>) in metres</li>
     * </ul>
     * <p>
     * This method is about the later.
     *
     * @return the axis linear unit.
     */
    @Override
    public Unit<Length> getAxisUnit() {
        return Units.METRE;
    }

    /**
     * Returns the length of the semi-major axis of the ellipsoid.
     * The default implementation delegates to {@link Earth#getMajor()}.
     *
     * @return length of semi-major axis.
     *
     * @see Earth#getMajor()
     */
    @Override
    public double getSemiMajorAxis() {
        return earth.getMajor();
    }

    /**
     * Returns the length of the semi-minor axis of the ellipsoid.
     * The default implementation delegates to {@link Earth#getMinor()}.
     *
     * @return length of semi-minor axis.
     *
     * @see Earth#getMinor()
     */
    @Override
    public double getSemiMinorAxis() {
        return earth.getMinor();
    }

    /**
     * Returns the inverse of the flattening constant.
     * The default implementation delegates to <code>1/{@linkplain Earth#getFlattening()}</code>.
     *
     * @return the inverse flattening value, or {@link Double#POSITIVE_INFINITY} if this
     *         ellipsoid {@linkplain #isSphere() is a sphere}.
     *
     * @see Earth#getFlattening()
     */
    @Override
    public double getInverseFlattening() {
        return 1 / earth.getFlattening();
    }

    /**
     * Indicates if the {@linkplain #getInverseFlattening() inverse flattening} is definitive for
     * this ellipsoid. The default implementation returns {@code false}.
     *
     * @return {@code true} if the {@linkplain #getInverseFlattening() inverse flattening} is
     *         definitive, or {@code false} if the {@linkplain #getSemiMinorAxis() polar radius}
     *         is definitive.
     */
    @Override
    public boolean isIvfDefinitive() {
        return false;
    }

    /**
     * {@code true} if the ellipsoid is degenerate and is actually a sphere.
     * The default implementation returns {@code true} if the {@linkplain Earth#getFlattening()
     * earth flattening} is zero.
     *
     * @return {@code true} if the ellipsoid is degenerate and is actually a sphere.
     */
    @Override
    public boolean isSphere() {
        return earth.getFlattening() == 0;
    }

    /**
     * Returns the prime meridian. The default implementation returns the Greenwich
     * prime meridian.
     *
     * @return the prime meridian.
     */
    @Override
    public PrimeMeridian getPrimeMeridian() {
        return SimplePrimeMeridian.GREENWICH;
    }

    /**
     * Returns the geodetic datum ellipsoid, which is {@code this}.
     *
     * @return This ellipsoid.
     */
    @Override
    public Ellipsoid getEllipsoid() {
        return this;
    }

    /**
     * Returns the geodetic datum, which is {@code this}.
     *
     * @return this datum.
     */
    @Override
    public GeodeticDatum getDatum() {
        return this;
    }

    /**
     * Returns the CRS coordinate system, which is {@code this}.
     *
     * @return this coordinate system.
     *
     * @see #getDimension()
     * @see #getAxis(int)
     */
    @Override
    public EllipsoidalCS getCoordinateSystem() {
        return this;
    }

    /**
     * Returns the axis for this ellipsoidal coordinate system at the specified dimension.
     * The axis at dimension 0 is the <cite>longitude</cite> and the axis at dimension 1 is
     * the <cite>latitude</cite>.
     *
     * <p><b>Do not confuse:</b></p>
     * <ul>
     *   <li>Ellipsoidal coordinate system axes (λ,φ) in degrees</li>
     *   <li>Ellipsoid axes (<var>semi-major</var>, <var>semi-minor</var>) in metres</li>
     * </ul>
     *
     * This method is about the former.
     *
     * @param  dimension  the zero based index of axis.
     * @return the coordinate system axis at the specified dimension.
     * @throws IndexOutOfBoundsException if {@code dimension} is out of bounds.
     */
    @Override
    public CoordinateSystemAxis getAxis(final int dimension) throws IndexOutOfBoundsException {
        switch (dimension) {
            case 0:  return SimpleAxis.LONGITUDE;
            case 1:  return SimpleAxis.LATITUDE;
            default: throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the number of ellipsoidal coordinate system axes, which is 2.
     *
     * @return the dimension of this ellipsoidal coordinate system.
     */
    @Override
    public int getDimension() {
        return 2;
    }
}
