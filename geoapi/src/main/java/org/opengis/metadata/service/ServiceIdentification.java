/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2014 Open Geospatial Consortium, Inc.
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
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.identification.DataIdentification;
import org.opengis.metadata.distribution.StandardOrderProcess;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of capabilities which a service provider makes available to a service user
 * through a set of interfaces that define a behaviour.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="SV_ServiceIdentification", specification=ISO_19115)
public interface ServiceIdentification extends Identification {
    /**
     * A service type name.
     * <blockquote><font size="-1"><b>Example:</b>
     * "discovery", "view", "download", "transformation", or "invoke".
     * </font></blockquote>
     *
     * @return A service type name.
     */
    @UML(identifier="serviceType", obligation=MANDATORY, specification=ISO_19115)
    GenericName getServiceType();

    /**
     * The version of the service, supports searching based on the version of serviceType.
     * For example, we might only be interested in OGC Catalogue V1.1 services.
     * If version is maintained as a separate attribute, users can easily search
     * for all services of a type regardless of the version.
     *
     * @return the version of the service, supports searching based on the version of serviceType.
     */
    @UML(identifier="serviceTypeVersion", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends InternationalString> getServiceTypeVersion();

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
     * @return Information about the availability of the service, or {@code null} if none.
     */
    @UML(identifier="accessProperties", obligation=OPTIONAL, specification=ISO_19115)
    StandardOrderProcess getAccessProperties();

    /**
     * Type of coupling between service and associated data (if exist).
     *
     * @return Type of coupling between service and associated data, or {@code null} if none.
     *
     * @condition mandatory if {@linkplain #getCoupledResource()} is not provided.
     */
    @UML(identifier="couplingType", obligation=CONDITIONAL, specification=ISO_19115)
    CouplingType getCouplingType();

    /**
     * Further description of the data coupling in the case of tightly coupled services.
     * Returns an empty set if none.
     *
     * @return Further description of the data coupling in the case of tightly coupled services.
     *
     * @condition mandatory if {@linkplain #getCouplingType()} is not provided.
     */
    @UML(identifier="coupledResource", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends CoupledResource> getCoupledResource();

    /**
     * Provides a reference to the resource on which the service operates.
     * Returns an empty set if none.
     *
     * @return reference(s) to the resource on which the service operates.
     */
    @UML(identifier="operationDataset", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getOperatedDataset();

    /**
     * Profile to which the service adheres.
     * Returns an empty set if none.
     *
     * @return Profile to which the service adheres.
     */
    @UML(identifier="profile", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getProfile();

    /**
     * Standard to which the service adheres.
     * Returns an empty set if none.
     *
     * @return Standard to which the service adheres.
     */
    @UML(identifier="serviceStandard", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getServiceStandard();

    /**
     * Provides information about the operations that comprise the service.
     * Returns an empty set if none.
     *
     * @return Information about the operations that comprise the service.
     */
    @UML(identifier="containsOperations", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends OperationMetadata> getContainsOperations();

    /**
     * Provides information on the resources that the service operates on.
     * Returns an empty set if none.
     *
     * @return Information on the resources that the service operates on.
     */
    @UML(identifier="operatesOn", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends DataIdentification> getOperatesOn();

    /**
     * Provides information about the chain applied by the service.
     * Returns an empty set if none.
     *
     * @return Information about the chain applied by the service.
     */
    @UML(identifier="containsChain", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends OperationChainMetadata> getContainsChain();
}
