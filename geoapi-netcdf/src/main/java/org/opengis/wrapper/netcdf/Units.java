/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2016-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.wrapper.netcdf;

import javax.measure.Unit;
import javax.measure.format.UnitFormat;
import javax.measure.format.ParserException;
import javax.measure.quantity.Time;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.Dimensionless;
import javax.measure.spi.ServiceProvider;
import javax.measure.spi.SystemOfUnits;
import javax.measure.spi.UnitFormatService;


/**
 * Pre-defined constants for the units of measurement used by the {@code geoapi-netcdf} module.
 * We use this class for avoiding direct dependency to the JSR-363 reference implementation in
 * other classes of the {@code geoapi-netcdf} module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class Units {
    /**
     * Linear units used in the {@code geoapi-netcdf} module.
     */
    static final Unit<Length> METRE, KILOMETRE, FOOT, FOOT_SURVEY_US;

    /**
     * Angular units used in the {@code geoapi-netcdf} module.
     */
    static final Unit<Angle> RADIAN, MICRORADIAN, DEGREE, GRAD, ARC_SECOND;

    /**
     * Temporal units used in the {@code geoapi-netcdf} module.
     */
    static final Unit<Time> SECOND, MINUTE, HOUR, DAY;

    /**
     * Pressure units used in the {@code geoapi-netcdf} module.
     */
    static final Unit<Pressure> PASCAL, HECTOPASCAL;

    /**
     * Scale units used in the {@code geoapi-netcdf} module.
     */
    static final Unit<Dimensionless> ONE, PPM;

    static {
        final SystemOfUnits system = ServiceProvider.current().getSystemOfUnitsService().getSystemOfUnits();
        METRE          = system.getUnit(Length.class);
        RADIAN         = system.getUnit(Angle.class);
        SECOND         = system.getUnit(Time.class);
        PASCAL         = system.getUnit(Pressure.class);
        ONE            = getDimensionless(system);
        KILOMETRE      = METRE .multiply(1000);
        FOOT           = METRE .multiply(0.3048);
        FOOT_SURVEY_US = METRE .multiply(12 / 39.37);
        DEGREE         = RADIAN.multiply(Math.PI/180);
        GRAD           = RADIAN.multiply(Math.PI/200);
        ARC_SECOND     = RADIAN.multiply(Math.PI / (180*60*60));
        MICRORADIAN    = RADIAN.divide(1E6);
        MINUTE         = SECOND.multiply(60);
        HOUR           = SECOND.multiply(60*60);
        DAY            = SECOND.multiply(60*60*24);
        HECTOPASCAL    = PASCAL.multiply(100);
        PPM            = ONE   .divide(1000000);
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
     * The object to use for getting a unit from its symbol.
     * This is created when first needed by {@link #parse(String)}.
     */
    private static UnitFormat unitFormat;

    /**
     * Do not allow instantiation of this class.
     */
    private Units() {
    }

    /**
     * Parses the given symbol using the {@link UnitFormat} instance provided by whatever JSR-363
     * implementation is found on the classpath. The same instance is reused for all units to parse.
     */
    static synchronized Unit<?> parse(final String symbol) throws ParserException {
        if (unitFormat == null) {
            ServiceProvider provider = ServiceProvider.current();
            if (provider != null) {
                UnitFormatService fs = provider.getUnitFormatService();
                if (fs != null) {
                    unitFormat = fs.getUnitFormat();
                }
            }
            if (unitFormat == null) {
                throw new ParserException("Can not parse unit symbol because no UnitFormat has been found on the classpath.", symbol, 0);
            }
        }
        try {
            return unitFormat.parse(symbol);
        } catch (ParserException e) {
            /*
             * Workaround for symbols found in some netCDF files
             * but not recognized by all {@link UnitFormat} parsers.
             */
            // Angular units
            if (symbol.equalsIgnoreCase(   "rad") ||
                equalsIgnorePlural(symbol, "radian"))         return RADIAN;
            if (equalsIgnorePlural(symbol, "degree") ||
                equalsIgnorePlural(symbol, "decimal_degree")) return DEGREE;
            if (equalsIgnorePlural(symbol, "grad"))           return GRAD;
            if (symbol.equalsIgnoreCase(   "arcsec"))         return ARC_SECOND;

            // Linear units
            if (equalsIgnorePlural(symbol, "meter") ||
                equalsIgnorePlural(symbol, "metre"))       return METRE;
            if (equalsIgnorePlural(symbol, "kilometer") ||
                equalsIgnorePlural(symbol, "kilometre"))   return KILOMETRE;
            if (symbol.equalsIgnoreCase("US survey foot")) return FOOT_SURVEY_US;
            if (symbol.equalsIgnoreCase("foot"))           return FOOT;
            if (symbol.equalsIgnoreCase("100 feet"))       return METRE.multiply(30.48);

            // Temporal units
            if (equalsIgnorePlural(symbol, "day"))    return DAY;
            if (equalsIgnorePlural(symbol, "hour"))   return HOUR;
            if (equalsIgnorePlural(symbol, "minute")) return MINUTE;
            if (equalsIgnorePlural(symbol, "second")) return SECOND;

            // Dimensionless units
            if (symbol.equalsIgnoreCase("ppm")) return PPM;
            if (symbol.isEmpty())               return ONE;
            throw e;
        }
    }

    /**
     * Returns {@code true} if the given {@code symbol} is equals to the given expected string,
     * ignoring case and trailing {@code 's'} character (if any).
     */
    private static boolean equalsIgnorePlural(final String symbol, final String expected) {
        final int length = expected.length();
        final int diff = symbol.length() - length;
        if (diff != 0 && (diff != 1 || Character.toLowerCase(symbol.charAt(length)) != 's')) {
            return false;
        }
        return symbol.regionMatches(true, 0, expected, 0, length);
    }
}
