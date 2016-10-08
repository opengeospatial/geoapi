/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2016 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.test;

import javax.measure.Unit;
import javax.measure.quantity.Time;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Dimensionless;
import javax.measure.spi.ServiceProvider;
import javax.measure.spi.SystemOfUnits;
import org.opengis.util.Factory;
import org.opengis.metadata.citation.Citation;


/**
 * Pre-defined constants for the units of measurement used by the conformance tests.
 * This pseudo-factory provides separated methods for all units needed by {@code geoapi-conformance}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0.1
 * @since   3.0.1
 */
public class Units implements Factory {
    /**
     * The default instance, created when first needed.
     *
     * @see #getDefault()
     */
    private static Units DEFAULT;

    /**
     * Linear units used in the test.
     */
    private final Unit<Length> metre, kilometre;

    /**
     * Angular units used in the test.
     */
    private final Unit<Angle> radian, degree;

    /**
     * Temporal units used in the test.
     */
    private final Unit<Time> second, day;

    /**
     * Dimensionless units used in the tests.
     */
    private final Unit<Dimensionless> one, ppm;

    /**
     * Creates a new factory which will use the given system of units.
     *
     * @param  system  the system of units to use for creating base units.
     */
    protected Units(final SystemOfUnits system) {
        metre     = system.getUnit(Length.class);
        radian    = system.getUnit(Angle.class);
        second    = system.getUnit(Time.class);
        one       = getDimensionless(system);
        kilometre = metre .multiply(1000);
        degree    = radian.multiply(Math.PI/180);
        day       = second.multiply(24*60*60);
        ppm       = one   .divide(1000000);
    }

    /**
     * Returns the default units factory. This factory uses the unit service provider which is
     * {@linkplain ServiceProvider#current() current} at the time of the first invocation of this method.
     *
     * @return the default units factory.
     */
    public static synchronized Units getDefault() {
        if (DEFAULT == null) {
            DEFAULT = new Units(ServiceProvider.current().getSystemOfUnitsService().getSystemOfUnits());
        }
        return DEFAULT;
    }

    /**
     * Returns the dimensionless unit. This is a workaround for what seems to be a bug
     * in the reference implementation 1.0.1 of unit API.
     */
    private static Unit<Dimensionless> getDimensionless(final SystemOfUnits system) {
        Unit<Dimensionless> unit = system.getUnit(Dimensionless.class);
        if (unit == null) try {
            unit = ((Unit<?>) Class.forName("tec.units.ri.AbstractUnit").getField("ONE").get(null)).asType(Dimensionless.class);
        } catch (ReflectiveOperationException | ClassCastException e) {
            throw new IllegalArgumentException("Can not create a dimensionless unit from the given provider.");
        }
        return unit;
    }

    /**
     * Not yet implemented.
     *
     * @return  {@code null} in current implementation.
     */
    @Override
    public Citation getVendor() {
        return null;
    }

    /** Base unit of measurement for lengths.            */  public Unit<Length> metre()      {return metre;}
    /** Unit of measurement defined as 1000 metres.      */  public Unit<Length> kilometre()  {return kilometre;}
    /** The base unit of measurement for angle.          */  public Unit<Angle>  radian()     {return radian;}
    /** Unit of measurement defined as π/180 radians.    */  public Unit<Angle>  degree()     {return degree;}
    /** Base unit of measurement for durations.          */  public Unit<Time>   second()     {return second;}
    /** Unit of measurement defined as 24×60×60 seconds. */  public Unit<Time>   day()        {return day;}
    /** Dimensionless unit for scale measurements.       */  public Unit<Dimensionless> one() {return one;}
    /** The "parts per million" unit.                    */  public Unit<Dimensionless> ppm() {return ppm;}
}
