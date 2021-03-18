/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2021 Open Geospatial Consortium, Inc.
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

import java.util.Collection;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.util.ScopedName;
import org.opengis.metadata.citation.Citation;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Links a given operation name (mandatory attribute of {@link OperationMetadata})
 * with a resource identified by an "identifier".
 *
 * <p><b>Constraint:</b></p>
 * <ul>
 *   <li>For one {@code CoupledResource} either {@linkplain #getResources() resources} or
 *       {@linkplain #getResourceReferences() resource references} should be used
 *       (not both for the same {@code CoupledResource}).</li>
 * </ul>
 *
 * @author  Rémi Maréchal (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SV_CoupledResource", specification=ISO_19115)
public interface CoupledResource {
    /**
     * Scoped identifier of the resource in the context of the given service instance.
     * This is the name of the resources (for example dataset) as it is used by a service instance
     *
     * <div class="note"><b>Examples:</b> layer name or feature type name.</div>
     *
     * @return scoped identifier of the resource in the context of the given service instance, or {@code null} if none.
     */
    @UML(identifier="scopedName", obligation=OPTIONAL, specification=ISO_19115)
    default ScopedName getScopedName() {
        return null;
    }

    /**
     * References to the resource on which the services operates.
     * Returns an empty collection if none.
     *
     * @return references to the resource on which the services operates.
     *
     * @condition Only one of {@linkplain #getResources() resources} and resource references should be non-empty.
     *
     * @see DataIdentification#getCitation()
     */
    @UML(identifier="resourceReference", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getResourceReferences() {
        return Collections.emptyList();
    }

    /**
     * The tightly coupled resources.
     * Returns an empty collection if none.
     *
     * @return tightly coupled resources.
     *
     * @condition Only one of resources and {@linkplain #getResourceReferences() resource references} should be non-empty.
     */
    @UML(identifier="resource", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends DataIdentification> getResources() {
        return Collections.emptyList();
    }

    /**
     * The service operation.
     *
     * @return the service operation, or {@code null} if none.
     */
    @UML(identifier="operation", obligation=OPTIONAL, specification=ISO_19115)
    default OperationMetadata getOperation() {
        return null;
    }
}
