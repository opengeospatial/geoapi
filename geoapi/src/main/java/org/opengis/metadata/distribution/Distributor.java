/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.metadata.distribution;

import java.util.Collection;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the distributor.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - ResponsibleParty
 * @navassoc - - - StandardOrderProcess
 * @navassoc - - - Format
 * @navassoc - - - DigitalTransferOptions
 */
@UML(identifier="MD_Distributor", specification=ISO_19115)
public interface Distributor {
    /**
     * Party from whom the resource may be obtained. This list need not be exhaustive.
     *
     * @return Party from whom the resource may be obtained.
     */
    @UML(identifier="distributorContact", obligation=MANDATORY, specification=ISO_19115)
    ResponsibleParty getDistributorContact();

    /**
     * Provides information about how the resource may be obtained, and related
     * instructions and fee information.
     *
     * @return Information about how the resource may be obtained.
     */
    @UML(identifier="distributionOrderProcess", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends StandardOrderProcess> getDistributionOrderProcesses();

    /**
     * Provides information about the format used by the distributor.
     *
     * @return Information about the format used by the distributor.
     *
     * @condition {@link Distribution#getDistributionFormats()} not documented.
     */
    @UML(identifier="distributorFormat", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Format> getDistributorFormats();

    /**
     * Provides information about the technical means and media used by the distributor.
     *
     * @return Information about the technical means and media used by the distributor.
     */
    @UML(identifier="distributorTransferOptions", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends DigitalTransferOptions> getDistributorTransferOptions();
}
