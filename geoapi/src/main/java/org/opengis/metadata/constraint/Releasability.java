/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.constraint;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Responsibility;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about resource release constraints.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_Releasability", specification=ISO_19115)
public interface Releasability {
    /**
     * Parties to which the release statement applies.
     * Returns an empty collection if none.
     *
     * @return parties to which the release statement applies.
     */
    @UML(identifier="addressee", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Responsibility> getAddressees();

    /**
     * Release statement.
     * May be {@code null} if unspecified.
     *
     * @return release statement, or {@code null} if none.
     */
    @UML(identifier="statement", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getStatement();

    /**
     * Components in determining releasability.
     * Returns an empty collection if none.
     *
     * @return components in determining releasability.
     */
    @UML(identifier="disseminationConstraints", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Restriction> getDisseminationConstraints();
}
