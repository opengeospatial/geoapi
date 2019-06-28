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
package org.opengis.metadata.citation;

import java.util.Collection;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.metadata.identification.BrowseGraphic;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about the party if the party is an organisation.
 *
 * <p><b>Conditional properties:</b></p>
 * Following properties have default methods but shall nevertheless be implemented if the corresponding condition is met:
 * <ul>
 *   <li>At least one of {@linkplain #getIndividual() individual} and {@linkplain #getLogo() logo} properties
 *       shall be documented if the {@linkplain #getName() name} (in parent interface) is not documented.</li>
 * </ul>
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="CI_Organisation", specification=ISO_19115)
public interface Organisation extends Party {
    /**
     * Graphics identifying organisation.
     * Returns an empty collection if there is none.
     *
     * @return graphic identifying organisation.
     *
     * @condition Mandatory if {@linkplain #getName() name} or {@linkplain Individual#getPositionName() position name}
     *            is not documented.
     */
    @UML(identifier="logo", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<? extends BrowseGraphic> getLogo() {
        return Collections.emptyList();
    }

    /**
     * Individuals in the named organisation.
     * Returns an empty collection if there is none.
     *
     * @return individuals in the named organisation.
     */
    @UML(identifier="individual", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Individual> getIndividual() {
        return Collections.emptyList();
    }
}
