/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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

import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of a spatial and temporal reference system used by a dataset.
 *
 * @departure historic
 *   This interface was initially derived from an ISO 19111 specification published in 2003. Later
 *   revisions (in 2005) rely on an interface defined in ISO 19115 instead. The annotations were
 *   updated accordingly, but this interface is still defined in the referencing package instead
 *   of the metadata package for this historical reason.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.referencing.crs.CoordinateReferenceSystem
 *
 * @navassoc 1 - - Extent
 */
@UML(identifier="RS_ReferenceSystem", specification=ISO_19115)
public interface ReferenceSystem extends IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} <code>createFoo(&hellip;)</code> methods.
     * This is used for setting the value to be returned by {@link #getDomainOfValidity()}.
     *
     * @see #getDomainOfValidity()
     *
     * @since 2.1
     */
    String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} <code>createFoo(&hellip;)</code> methods.
     * This is used for setting the value to be returned by {@link #getScope()}.
     *
     * @see #getScope()
     */
    String SCOPE_KEY = "scope";

    /**
     * Area or region or timeframe in which this (coordinate) reference system is valid.
     *
     * @return The reference system valid domain, or {@code null} if not available.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003.
     *   Later revisions changed the multiplicity, so the return type should now be a 
     *   collection. The singleton has been preserved in GeoAPI for historical reasons, 
     *   and also because the <code>Extent</code> attributes already allow collections.
     *
     * @since 2.1
     */
    @UML(identifier="domainOfValidity", obligation=OPTIONAL, specification=ISO_19111)
    Extent getDomainOfValidity();

    /**
     * Description of domain of usage, or limitations of usage, for which this
     * Reference System object is valid.
     *
     * @return The domain of usage, or {@code null} if none.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003. 
     *   A later revision moved this attribute to subclasses, but GeoAPI keeps this method 
     *   here for historical reasons. The obligation is still optional, as opposed to ISO 19111:2007
     *   which makes this attribute mandatory while mandating the text "<cite>not known</cite>" if
     *   the scope is unknown. In addition, the return value of this method is still a singleton as in
     *   the 2003 version, as opposed to the 2007 version which mandates a collection. The proposed
     *   change is still under review.
     */
    @UML(identifier="SC_CRS.scope", obligation=OPTIONAL, specification=ISO_19111)
    InternationalString getScope();
}
