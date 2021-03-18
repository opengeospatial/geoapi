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
package org.opengis.referencing;

import java.util.Set;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * Base interface for all authority factories. An <cite>authority</cite> is an organization
 * that maintains definitions of authority codes. An <cite>authority code</cite> is a compact
 * string defined by an authority to reference a particular spatial reference object.
 *
 * <p>For example the <a href="http://www.epsg.org">European Petroleum Survey Group (EPSG)</a> maintains
 * a database of coordinate systems, and other spatial referencing objects, where each object has a code
 * number ID. For example, the EPSG code for a WGS84 Lat/Lon coordinate system is “4326”.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="CS_CoordinateSystemAuthorityFactory", specification=OGC_01009)
public interface AuthorityFactory extends Factory {
    /**
     * Returns the organization or party responsible for definition and maintenance of the database.
     *
     * @return the organization responsible for definition of the database.
     */
    @UML(identifier="getAuthority", specification=OGC_01009)
    Citation getAuthority();

    /**
     * Returns the set of authority codes for objects of the given type.
     * The {@code type} argument specifies the base type of identified objects.
     *
     * <p><b>Example:</b> if this factory is an instance of {@link org.opengis.referencing.crs.CRSAuthorityFactory},
     * then:</p>
     *
     * <ul>
     *   <li><p><code>{@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem}.class</code><br>
     *       asks for all authority codes accepted by one of
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createGeographicCRS createGeographicCRS},
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS createProjectedCRS},
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createVerticalCRS createVerticalCRS},
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createTemporalCRS createTemporalCRS}
     *       and any other method returning a sub-type of {@code CoordinateReferenceSystem}.</p></li>
     *   <li><p><code>{@linkplain org.opengis.referencing.crs.ProjectedCRS}.class</code><br>
     *       asks only for authority codes accepted by
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS createProjectedCRS}.</p></li>
     * </ul>
     *
     * @param  type  the spatial reference objects type.
     * @return the set of authority codes for spatial reference objects of the given type.
     *         If this factory does not contain any object of the given type, then this method
     *         returns an {@linkplain java.util.Collections#EMPTY_SET empty set}.
     * @throws FactoryException if access to the underlying database failed.
     *
     * @departure extension
     *   This method is not part of the OGC specification but has been added as a way to publish
     *   the capabilities of a factory.
     */
    Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException;

    /**
     * Gets a description of the object corresponding to a code.
     *
     * @param  code  value allocated by authority.
     * @return a description of the object, or {@code null} if the object
     *         corresponding to the specified {@code code} has no description.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the query failed for some other reason.
     */
    @UML(identifier="descriptionText", specification=OGC_01009)
    InternationalString getDescriptionText(String code) throws NoSuchAuthorityCodeException, FactoryException;

    /**
     * Returns an arbitrary object from a code. The returned object will typically be an
     * instance of {@link org.opengis.referencing.datum.Datum}, {@link org.opengis.referencing.cs.CoordinateSystem},
     * {@link org.opengis.referencing.ReferenceSystem} or {@link org.opengis.referencing.operation.CoordinateOperation}.
     *
     * <p>If the object type is known at compile time, then it is recommended to invoke the
     * most precise method instead of this one. For example it is usually better to invoke
     * <code>{@linkplain org.opengis.referencing.crs.CRSAuthorityFactory#createCoordinateReferenceSystem
     * createCoordinateReferenceSystem}(code)</code> instead of {@code createObject(code)}
     * if the requested object is known to be a {@code CoordinateReferenceSystem} instance.</p>
     *
     * @param  code  value allocated by authority.
     * @return the object for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @departure generalization
     *   This method is not part of the OGC specification. It has been added to leverage the
     *   capability of factories that can automatically determine the type of the requested
     *   object at runtime.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createDatum(String)
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createCoordinateReferenceSystem(String)
     */
    IdentifiedObject createObject(String code) throws NoSuchAuthorityCodeException, FactoryException;
}
