/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.datum;

import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Creates {@linkplain Datum datum} objects using authority codes. External authorities are used to
 * manage definitions of objects used in this interface. The definitions of these objects are
 * referenced using code strings. A commonly used authority is <a href="http://www.epsg.org">EPSG</a>.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSAuthorityFactory
 * @see org.opengis.referencing.crs.CRSAuthorityFactory
 */
@UML(identifier="CS_CoordinateSystemAuthorityFactory", specification=OGC_01009)
public interface DatumAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an arbitrary datum from a code.
     *
     * <p>If the datum type is known at compile time, then it is recommended to invoke the
     * most precise method instead of this one. For example it is usually better to invoke
     * <code>{@linkplain #createGeodeticDatum createGeodeticDatum}(code)</code> instead of
     * {@code createDatum(code)} if the requested object is known to be a
     * {@code GeodeticDatum} instance.</p>
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeodeticDatum(String)
     * @see #createVerticalDatum(String)
     * @see #createTemporalDatum(String)
     */
    Datum createDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a engineering datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createEngineeringCRS(String)
     */
    EngineeringDatum createEngineeringDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a image datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createImageCRS(String)
     */
    ImageDatum createImageDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a vertical datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createVerticalCRS(String)
     */
    @UML(identifier="createVerticalDatum", specification=OGC_01009)
    VerticalDatum createVerticalDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a temporal datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createTemporalCRS(String)
     */
    TemporalDatum createTemporalDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a parametric datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createParametricCRS(String)
     */
    ParametricDatum createParametricDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a geodetic datum from a code.
     *
     * @param  code  value allocated by authority.
     * @return the datum for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createEllipsoid(String)
     * @see #createPrimeMeridian(String)
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createGeographicCRS(String)
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS(String)
     */
    @UML(identifier="createHorizontalDatum", specification=OGC_01009)
    GeodeticDatum createGeodeticDatum(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns an ellipsoid from a code.
     *
     * @param  code  value allocated by authority.
     * @return the ellipsoid for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeodeticDatum(String)
     */
    @UML(identifier="createEllipsoid", specification=OGC_01009)
    Ellipsoid createEllipsoid(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a prime meridian from a code.
     *
     * @param  code  value allocated by authority.
     * @return the prime meridian for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeodeticDatum(String)
     */
    @UML(identifier="createPrimeMeridian", specification=OGC_01009)
    PrimeMeridian createPrimeMeridian(String code)
            throws NoSuchAuthorityCodeException, FactoryException;
}
