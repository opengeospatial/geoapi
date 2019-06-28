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
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Associated resource information.
 *
 * <p><b>Conditional properties:</b></p>
 * Following property has default method but shall nevertheless be implemented if the corresponding condition is met:
 * <ul>
 *   <li>{@linkplain #getMetadataReference() Metadata reference} is mandatory if the resource
 *       {@linkplain #getName() name} is not provided.</li>
 * </ul>
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_AssociatedResource", specification=ISO_19115)
public interface AssociatedResource {
    /**
     * Citation information about the associated resource.
     *
     * @return citation information about the associated resource, or {@code null} if none.
     *
     * @condition Mandatory if the {@linkplain #getMetadataReference() metadata reference} is not documented.
     */
    @UML(identifier="name", obligation=CONDITIONAL, specification=ISO_19115)
    Citation getName();

    /**
     * Type of relation between the resources.
     *
     * @return the type of relation between the resources.
     */
    @UML(identifier="associationType", obligation=MANDATORY, specification=ISO_19115)
    AssociationType getAssociationType();

    /**
     * Type of initiative under which the associated resource was produced.
     *
     * @return the type of initiative under which the associated resource was produced, or {@code null} if none.
     */
    @UML(identifier="initiativeType", obligation=OPTIONAL, specification=ISO_19115)
    default InitiativeType getInitiativeType() {
        return null;
    }

    /**
     * Reference to the metadata of the associated resource.
     *
     * @return reference to the metadata of the associated resource, or {@code null} if none.
     *
     * @condition Mandatory if the {@linkplain #getName() name} is not documented.
     */
    @UML(identifier="metadataReference", obligation=CONDITIONAL, specification=ISO_19115)
    default Citation getMetadataReference() {
        return null;
    }
}
