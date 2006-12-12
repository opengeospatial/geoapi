/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/identification/ServiceIdentification.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.service;

// Annotations
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19119;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.metadata.distribution.StandardOrderProcess;
import org.opengis.metadata.identification.DataIdentification;
import org.opengis.metadata.identification.Identification;
import org.opengis.util.GenericName;

/**
 * Provides descriptive data for a Service instance sufficient to allow a client
 * to invoke the service
 * <p>
 * ServiceIdentification has an aggregation relationship with multiple instances
 * of {@link OperationMetadata}. While it is known that the service structure may be
 * more complicated than this aggregation, the additional detail of services
 * aggregating services is not needed in a service metadata record.
 * </p>
 * 
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 */
@UML(identifier = "SV_ServiceIdentification", specification = ISO_19119)
public interface ServiceIdentification extends Identification {
    /**
     * A service type name from a registry of services. For example, the values
     * of the nameSpace and name attributes of GeneralName may be "OGC " and
     * "catalogue.
     * 
     * @return GenericName
     */
    @UML(identifier = "serviceType", obligation = MANDATORY, specification = ISO_19119)
    GenericName getServiceType();

    /**
     * Provides for searching based on the version of serviceType. For example,
     * we may only be interested in OGC Catalogue V1.1 services. If version is
     * maintained as a separate attribute, users can easily search for all
     * services of a type regardless of the version.
     * 
     * @return Collection of String
     */
    @UML(identifier = "serviceTypeVersion", obligation = OPTIONAL, specification = ISO_19119)
    Collection<String> getServiceTypeVersion();

    /**
     * Information about the availability of the service, including,
     * <ul>
     * <li> Fees
     * <li> Planned available date and time
     * <li> Ordering instructions
     * <li> Turnaround
     * </ul>
     * 
     * @return StandardOrderProcess
     */
    @UML(identifier = "accessProperties", obligation = OPTIONAL, specification = ISO_19119)
    StandardOrderProcess getAccessProperties();

    /**
     * Provides information about the operations that comprise the service
     * 
     * @return Collection of OperationMetadata
     */
    @UML(identifier = "containsOperations", obligation = MANDATORY, specification = ISO_19119)
    Collection<OperationMetadata> getContainsOperations();

    /**
     * Provides information on the datasets that the service operates on
     * 
     * @return Collection of DataIdentification
     */
    @UML(identifier = "operatesOn", obligation = OPTIONAL, specification = ISO_19119)
    Collection<DataIdentification> getOperatesOn();

    /**
     * Provider of this service
     * 
     * @return Collection of ServiceProvider
     */
    @UML(identifier = "provider", obligation = OPTIONAL, specification = ISO_19119)
    Collection<ServiceProvider> getProvider();

}
