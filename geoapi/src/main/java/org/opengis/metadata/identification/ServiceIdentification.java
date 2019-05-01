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
package org.opengis.metadata.identification;

import java.util.Collection;
import org.opengis.annotation.UML;
import org.opengis.util.GenericName;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.distribution.StandardOrderProcess;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of capabilities which a service provider makes available.
 * The services are provided to a user through a set of interfaces that define a behaviour.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="SV_ServiceIdentification", specification=ISO_19115)
public interface ServiceIdentification extends Identification {
    /**
     * A service type name.
     * <div class="note"><b>Examples:</b>
     * "discovery", "view", "download", "transformation", or "invoke".
     * </div>
     *
     * @return a service type name.
     *
     * @since 3.1
     */
    @UML(identifier="serviceType", obligation=MANDATORY, specification=ISO_19115)
    GenericName getServiceType();

    /**
     * The version(s) of the service.
     * Supports searching based on the version of {@linkplain #getServiceType() service type}.
     *
     * <div class="note"><b>Example:</b>
     * We might only be interested in OGC Catalogue V1.1 services.
     * If version is maintained as a separate attribute, users can easily search
     * for all services of a type regardless of the version.
     * </div>
     *
     * @return the version of the service, supports searching based on the version of serviceType.
     *
     * @since 3.1
     */
    @UML(identifier="serviceTypeVersion", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getServiceTypeVersions();

    /**
     * Information about the availability of the service.
     * This includes:
     * <ul>
     *   <li>fee</li>
     *   <li>planned available date and time</li>
     *   <li>ordering instructions</li>
     *   <li>turnaround</li>
     * </ul>
     *
     * @return information about the availability of the service, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="accessProperties", obligation=OPTIONAL, specification=ISO_19115)
    StandardOrderProcess getAccessProperties();

    /**
     * Type of coupling between service and associated data (if exist).
     *
     * @return type of coupling between service and associated data, or {@code null} if none.
     *
     * @condition mandatory if {@link #getCoupledResources() coupledResources} is not provided.
     *
     * @since 3.1
     */
    @UML(identifier="couplingType", obligation=CONDITIONAL, specification=ISO_19115)
    CouplingType getCouplingType();

    /**
     * Further description(s) of the data coupling in the case of tightly coupled services.
     * Returns an empty collection if none.
     *
     * @return further description of the data coupling in the case of tightly coupled services.
     *
     * @condition mandatory if {@link #getCouplingType() couplingType} is not provided.
     *
     * @since 3.1
     */
    @UML(identifier="coupledResource", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends CoupledResource> getCoupledResources();

    /**
     * Provides reference(s) to the resources on which the service operates.
     * Returns an empty collection if none.
     *
     * @return reference(s) to the resource on which the service operates.
     *
     * @condition For one resource either {@code operatedDataset} or {@link #getOperatesOn() operatesOn}
     *            may be used (not both for the same resource).
     *
     * @since 3.1
     */
    @UML(identifier="operatedDataset", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getOperatedDatasets();

    /**
     * Profile(s) to which the service adheres.
     * Returns an empty collection if none.
     *
     * @return profile(s) to which the service adheres.
     *
     * @since 3.1
     */
    @UML(identifier="profile", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getProfiles();

    /**
     * Standard(s) to which the service adheres.
     * Returns an empty collection if none.
     *
     * @return standard(s) to which the service adheres.
     *
     * @since 3.1
     */
    @UML(identifier="serviceStandard", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getServiceStandards();

    /**
     * Provides information about the operations that comprise the service.
     * Returns an empty collection if none.
     *
     * @return information about the operations that comprise the service.
     *
     * @since 3.1
     */
    @UML(identifier="containsOperations", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends OperationMetadata> getContainsOperations();

    /**
     * Provides information on the resources that the service operates on.
     * Returns an empty collection if none.
     *
     * @return information on the resources that the service operates on.
     *
     * @condition For one resource either {@link #getOperatedDatasets() operatedDataset}
     *            or {@code operatesOn} may be used (not both for the same resource).
     *
     * @since 3.1
     */
    @UML(identifier="operatesOn", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends DataIdentification> getOperatesOn();

    /**
     * Provides information about the chain applied by the service.
     * Returns an empty collection if none.
     *
     * @return information about the chain applied by the service.
     *
     * @since 3.1
     */
    @UML(identifier="containsChain", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends OperationChainMetadata> getContainsChain();
}
