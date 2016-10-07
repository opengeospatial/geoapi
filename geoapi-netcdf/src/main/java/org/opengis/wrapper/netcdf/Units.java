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
package org.opengis.wrapper.netcdf;

import javax.measure.Unit;
import javax.measure.quantity.Time;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.Dimensionless;
import javax.measure.spi.ServiceProvider;
import javax.measure.spi.SystemOfUnits;


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
    static final Unit<Time> SECOND, HOUR, DAY;

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
     * Do not allow instantiation of this class.
     */
    private Units() {
    }
}
