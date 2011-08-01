/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.operation.MathTransformFactory;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.util.FactoryException;
import org.opengis.test.util.PseudoFactory;


/**
 * Creates Coordinate Reference System objects for a limited set of hard-coded EPSG codes
 * using only {@link ObjectFactory} and {@link MathTransformFactory}. This pseudo-factory
 * can be used with implementation that do not support (or don't want to test) a "real"
 * {@link CRSAuthorityFactory} for the EPSG database.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class PseudoEpsgFactory extends PseudoFactory {
    /**
     * Number of US survey feet in 1 metre.
     */
    static final double FEET = 3.2808333333333333333;

    /**
     * The factory to use for creating math transforms.
     */
    protected final MathTransformFactory mtFactory;

    /**
     * Creates a new pseudo-factory which will use the given factories.
     *
     * @param mtFactory The factory to use for creating math transforms.
     */
    public PseudoEpsgFactory(final MathTransformFactory mtFactory) {
        this.mtFactory = mtFactory;
    }

    /**
     * Returns the projection parameters to use for creating a CRS of the given code.
     * The supported codes are determined from the set of examples published in the
     * EPSG guidance document or other sources. They are:
     * <p>
     * <table border="1" cellspacing="0" cellpadding="2">
     *   <tr><th>Code</th>      <th>CRS Name</th>                            <th>Operation method</th></tr>
     *   <tr><td>3002</td>      <td>Makassar / NEIEZ</td>                    <td>Mercator (variant A)</td></tr>
     *   <tr><td>3388</td>      <td>Pulkovo 1942 / Caspian Sea Mercator</td> <td>Mercator (variant B)</td></tr>
     *   <tr><td>3857</td>      <td>WGS 84 / Pseudo-Mercator</td>            <td>Popular Visualisation Pseudo Mercator</td></tr>
     *   <tr><td>24200</td>     <td>JAD69 / Jamaica National Grid</td>       <td>Lambert Conic Conformal (1SP)</td></tr>
     *   <tr><td>32040</td>     <td>NAD27 / Texas South Central</td>         <td>Lambert Conic Conformal (2SP)</td></tr>
     *   <tr><td>31300</td>     <td>Belge 1972 / Belge Lambert 72</td>       <td>Lambert Conic Conformal (2SP Belgium)</td></tr>
     *   <tr><td>310642901</td> <td>IGNF:MILLER</td>                         <td>Miller_Cylindrical</td></tr>
     * </table>
     *
     * @param  code The EPSG code of the Coordinate Reference System to create.
     * @return The projection parameters.
     * @throws FactoryException If the given EPSG code is unknown to this factory.
     */
    protected ParameterValueGroup createParameters(final int code) throws FactoryException {
        return createParameters(mtFactory, code);
    }

    /**
     * Implementation of the above {@link #createParameters(int)} method,
     * as a static method for direct access by {@link MathTransformTest}.
     */
    static ParameterValueGroup createParameters(final MathTransformFactory factory, final int code)
            throws FactoryException
    {
        final ParameterValueGroup parameters;
        switch (code) {
            case 3002: { // "Makassar / NEIEZ" using operation method 9804
                parameters = factory.getDefaultParameters("Mercator (variant A)"); // Alias "Mercator (1SP)"
                parameters.parameter("semi-major axis").setValue(6377397.155); // Bessel 1841
                parameters.parameter("semi-minor axis").setValue(6377397.155 * (1 - 1/299.1528128));
                parameters.parameter("Latitude of natural origin")    .setValue(  0.0);
                parameters.parameter("Longitude of natural origin")   .setValue(110.0);
                parameters.parameter("Scale factor at natural origin").setValue(0.997);
                parameters.parameter("False easting").setValue(3900000.0);
                parameters.parameter("False northing").setValue(900000.0);
                break;
            }
            case 3388: { // "Pulkovo 1942 / Caspian Sea Mercator" using operation method 9805
                parameters = factory.getDefaultParameters("Mercator (variant B) "); // Alias "Mercator (2SP)"
                parameters.parameter("semi-major axis").setValue(6378245.0); // Krassowski 1940
                parameters.parameter("semi-minor axis").setValue(6378245.0 * (1 - 1/298.3));
                parameters.parameter("Latitude of 1st standard parallel").setValue(42.0);
                parameters.parameter("Longitude of natural origin")      .setValue(51.0);
                break;
            }
            case 3857: { // "WGS 84 / Pseudo-Mercator" using operation method 1024
                parameters = factory.getDefaultParameters("Popular Visualisation Pseudo Mercator");
                parameters.parameter("semi-major axis").setValue(6378137.0); // WGS 84
                parameters.parameter("semi-minor axis").setValue(6378137.0 * (1 - 1/298.2572236));
                break;
            }
            case 310642901: { // "IGNF:MILLER" (not an official EPSG code)
                parameters = factory.getDefaultParameters("Miller_Cylindrical");
                parameters.parameter("semi-major axis").setValue(6378137);
                parameters.parameter("semi-minor axis").setValue(6378137);
                break;
            }
            case 24200: { // "JAD69 / Jamaica National Grid" using operation method 9801
                parameters = factory.getDefaultParameters("Lambert Conic Conformal (1SP)");
                parameters.parameter("semi-major axis").setValue(6378206.4); // Clarke 1866
                parameters.parameter("semi-minor axis").setValue(6356583.8);
                parameters.parameter("Latitude of natural origin")    .setValue( 18.0);
                parameters.parameter("Longitude of natural origin")   .setValue(-77.0);
                parameters.parameter("Scale factor at natural origin").setValue(  1.0);
                parameters.parameter("False easting") .setValue(250000.00);
                parameters.parameter("False northing").setValue(150000.00);
                break;
            }
            case 32040: { // "NAD27 / Texas South Central" using operation method 9802
                parameters = factory.getDefaultParameters("Lambert Conic Conformal (2SP)");
                parameters.parameter("semi-major axis").setValue(6378206.4); // Clarke 1866
                parameters.parameter("semi-minor axis").setValue(6356583.8);
                parameters.parameter("Latitude of 1st standard parallel").setValue(28 + 23.0/60); // 28°23'00"N
                parameters.parameter("Latitude of 2nd standard parallel").setValue(30 + 17.0/60); // 30°17'00"N
                parameters.parameter("Latitude of false origin")         .setValue(27 + 50.0/60); // 27°50'00"N
                parameters.parameter("Longitude of false origin")        .setValue(-99.0);        // 99°00'00"W
                parameters.parameter("Easting at false origin") .setValue(2000000 / FEET);
                parameters.parameter("Northing at false origin").setValue(      0 / FEET);
                break;
            }
            case 31300: { // "Belge 1972 / Belge Lambert 72" using operation method 9803
                parameters = factory.getDefaultParameters("Lambert Conic Conformal (2SP Belgium)");
                parameters.parameter("semi-major axis").setValue(6378388); // International 1924
                parameters.parameter("semi-minor axis").setValue(6378388 * (1 - 1/297.0));
                parameters.parameter("Latitude of 1st standard parallel").setValue(49 + 50.0/60); // 49°50'00"N
                parameters.parameter("Latitude of 2nd standard parallel").setValue(51 + 10.0/60); // 51°10'00"N
                parameters.parameter("Latitude of false origin")         .setValue(90.0);         // 90°00'00"N
                parameters.parameter("Longitude of false origin")        .setValue(4 + (21 + 24.983/60)/60); // 4°21'24.983"E
                parameters.parameter("Easting at false origin") .setValue( 150000.01);
                parameters.parameter("Northing at false origin").setValue(5400088.44);
                break;
            }
            default: {
                throw new FactoryException("No case implemented for EPSG:" + code);
            }
        }
        return parameters;
    }
}
