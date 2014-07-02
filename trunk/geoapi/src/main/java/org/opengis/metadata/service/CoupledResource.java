/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.service;

import java.util.Collection;
import org.opengis.annotation.UML;
import org.opengis.util.ScopedName;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.identification.DataIdentification;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Links a given operation name (mandatory attribute of {@link OperationMetadata})
 * with a resource identified by an "identifier".
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SV_CoupledResource", specification=ISO_19115)
public interface CoupledResource {
    /**
     * Scoped identifier of the resource in the context of the given service instance.
     *
     * <blockquote><font size="-1"><b>Example:</b> name of the resources (for example dataset)
     * as it is used by a service instance (for example layer name or feature type name).</font></blockquote>
     *
     * @return Scoped identifier of the resource in the context of the given service instance, or {@code null} if none.
     */
    @UML(identifier="scopedName", obligation=OPTIONAL, specification=ISO_19115)
    ScopedName getScopedName();

    /**
     * Reference to the resource on which the services operates.
     * Returns an empty collection if none.
     *
     * @return Reference to the resource on which the services operates.
     */
    @UML(identifier="resourceReference", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getResourceReference();

    /**
     * The tightly coupled resource.
     * Returns an empty collection if none.
     *
     * @return Tightly coupled resource.
     */
    @UML(identifier="resource", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends DataIdentification> getResource();

    /**
     * The service operation.
     *
     * @return The service operation, or {@code null} if none.
     */
    @UML(identifier="operation", obligation=OPTIONAL, specification=ISO_19115)
    OperationMetadata getOperation();
}
