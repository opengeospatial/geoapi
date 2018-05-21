/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import javax.measure.Unit;
import javax.measure.quantity.Angle;

import org.opengis.referencing.datum.PrimeMeridian;


/**
 * The Greenwich prime meridian, implemented as a separated class because ISO 19111
 * requires the name to be <cite>"Greenwich"</cite>.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimplePrimeMeridian extends NetcdfIdentifiedObject implements PrimeMeridian {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 7605367861806200503L;

    /**
     * The unique instance of the Greenwich class.
     */
    static final SimplePrimeMeridian GREENWICH = new SimplePrimeMeridian();

    /**
     * Creates the unique instance of the Greenwich class.
     */
    private SimplePrimeMeridian() {
        super();
    }

    /**
     * Returns the prime meridian name.
     */
    @Override
    public String delegate() {
        return getCode();
    }

    /**
     * Returns the prime meridian name.
     */
    @Override
    public String getCode() {
        return "Greenwich";
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
        return Units.DEGREE;
    }
}
