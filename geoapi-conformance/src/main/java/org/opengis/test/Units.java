/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2016-2021 Open Geospatial Consortium, Inc.
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
import javax.measure.quantity.Pressure;
import javax.measure.quantity.Dimensionless;
import javax.measure.spi.ServiceProvider;
import javax.measure.spi.SystemOfUnits;
import org.opengis.test.util.PseudoFactory;


/**
 * Pre-defined constants for the units of measurement used by the conformance tests.
 * This pseudo-factory provides separated methods for all units needed by {@code geoapi-conformance}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.0.1
 */
public class Units extends PseudoFactory {
    /**
     * The default instance, created when first needed.
     *
     * @see #getDefault()
     */
    private static Units DEFAULT;

    /**
     * Linear units used in the tests.
     */
    private final Unit<Length> metre, kilometre, foot, footSurveyUS;

    /**
     * Angular units used in the tests.
     */
    private final Unit<Angle> radian, microradian, degree, grad, arcSecond;

    /**
     * Temporal units used in the tests.
     */
    private final Unit<Time> second, day;

    /**
     * Pressure units used in the tests.
     */
    private final Unit<Pressure> pascal, hectopascal;

    /**
     * Scale units used in the tests.
     */
    private final Unit<Dimensionless> one, ppm;

    /**
     * Creates a new factory which will use the given system of units.
     *
     * @param  system  the system of units to use for creating base units.
     */
    public Units(final SystemOfUnits system) {
        metre        = system.getUnit(Length.class);
        radian       = system.getUnit(Angle.class);
        second       = system.getUnit(Time.class);
        pascal       = system.getUnit(Pressure.class);
        one          = getDimensionless(system);
        kilometre    = metre .multiply(1000);
        foot         = metre .multiply(0.3048);
        footSurveyUS = metre .multiply(12 / 39.37);
        degree       = radian.multiply(Math.PI / 180);
        grad         = radian.multiply(Math.PI / 200);
        arcSecond    = radian.multiply(Math.PI / (180*60*60));
        microradian  = radian.divide(1E6);
        day          = second.multiply(24*60*60);
        hectopascal  = pascal.multiply(100);
        ppm          = one   .divide(1000000);
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
            throw new IllegalArgumentException("Cannot create a dimensionless unit from the given provider.");
        }
        return unit;
    }

    /** Base unit of measurement for lengths.            */  public Unit<Length>   metre()        {return metre;}
    /** Unit of measurement defined as 1000 metres.      */  public Unit<Length>   kilometre()    {return kilometre;}
    /** Unit of measurement defined as 0.3048 metres.    */  public Unit<Length>   foot()         {return foot;}
    /** Unit of measurement defined as 12/39.37 metres.  */  public Unit<Length>   footSurveyUS() {return footSurveyUS;}
    /** Base unit of measurement for angle.              */  public Unit<Angle>    radian()       {return radian;}
    /** Unit of measurement defined as 1E-6 radians.     */  public Unit<Angle>    microradian()  {return microradian;}
    /** Unit of measurement defined as π/180 radians.    */  public Unit<Angle>    degree()       {return degree;}
    /** Unit of measurement defined as π/200 radians.    */  public Unit<Angle>    grad()         {return grad;}
    /** Unit of measurement defined as 1/(60×60) degree. */  public Unit<Angle>    arcSecond()    {return arcSecond;}
    /** Base unit of measurement for durations.          */  public Unit<Time>     second()       {return second;}
    /** Unit of measurement defined as 24×60×60 seconds. */  public Unit<Time>     day()          {return day;}
    /** Base unit of measurement for pressure.           */  public Unit<Pressure> pascal()       {return pascal;}
    /** Unit of measurement defined as 100 pascals.      */  public Unit<Pressure> hectopascal()  {return hectopascal;}
    /** Dimensionless unit for scale measurements.       */  public Unit<Dimensionless> one()     {return one;}
    /** The "parts per million" unit.                    */  public Unit<Dimensionless> ppm()     {return ppm;}
}
