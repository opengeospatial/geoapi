/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.util.FactoryException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Creates {@linkplain CoordinateReferenceSystem coordinate reference systems} using authority codes.
 * External authorities are used to manage definitions of objects used in this interface.
 * The definitions of these objects are referenced using code strings.
 * A commonly used authority is <a href="http://www.epsg.org">EPSG</a>.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.cs.CSAuthorityFactory
 * @see org.opengis.referencing.datum.DatumAuthorityFactory
 */
@UML(identifier="CS_CoordinateSystemAuthorityFactory", specification=OGC_01009)
public interface CRSAuthorityFactory extends AuthorityFactory {
    /**
     * Returns an arbitrary coordinate reference system from a code.
     *
     * <p>If the coordinate reference system type is known at compile time, then it is recommended
     * to invoke the most precise method instead of this one. For example, it is usually better to
     * invoke <code>{@linkplain #createGeographicCRS createGeographicCRS}(code)</code> instead of
     * {@code createCoordinateReferenceSystem(code)} if the requested object is known to be a
     * {@code GeographicCRS} instance.</p>
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see #createGeographicCRS(String)
     * @see #createProjectedCRS(String)
     * @see #createVerticalCRS(String)
     * @see #createTemporalCRS(String)
     * @see #createCompoundCRS(String)
     */
    @UML(identifier="createHorizontalCoordinateSystem", specification=OGC_01009)
    CoordinateReferenceSystem createCoordinateReferenceSystem(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a 3D coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="createCompoundCoordinateSystem", specification=OGC_01009)
    CompoundCRS createCompoundCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a derived coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    DerivedCRS createDerivedCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a engineering coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    EngineeringCRS createEngineeringCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a geographic coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum(String)
     */
    @UML(identifier="createGeographicCoordinateSystem", specification=OGC_01009)
    GeographicCRS createGeographicCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a geocentric coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum(String)
     */
    GeocentricCRS createGeocentricCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a image coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    ImageCRS createImageCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a projected coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createGeodeticDatum(String)
     */
    @UML(identifier="createProjectedCoordinateSystem", specification=OGC_01009)
    ProjectedCRS createProjectedCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a temporal coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createTemporalDatum(String)
     */
    TemporalCRS createTemporalCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a vertical coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createVerticalDatum(String)
     */
    @UML(identifier="createVerticalCoordinateSystem", specification=OGC_01009)
    VerticalCRS createVerticalCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns a parametric coordinate reference system from a code.
     *
     * @param  code  value allocated by authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createParametricDatum(String)
     */
    ParametricCRS createParametricCRS(String code)
            throws NoSuchAuthorityCodeException, FactoryException;
}
