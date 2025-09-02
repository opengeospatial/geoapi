/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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

import java.util.List;
import java.util.Collection;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the distributor.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Distributor", specification=ISO_19115)
public interface Distributor {
    /**
     * Party from whom the resource may be obtained.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ResponsibleParty} is replaced by the {@link Responsibility} parent interface.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return party from whom the resource may be obtained.
     */
    @UML(identifier="distributorContact", obligation=MANDATORY, specification=ISO_19115, version=2003)
    ResponsibleParty getDistributorContact();

    /**
     * Provides information about how the resource may be obtained, and related
     * instructions and fee information.
     *
     * @return information about how the resource may be obtained.
     */
    @UML(identifier="distributionOrderProcess", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends StandardOrderProcess> getDistributionOrderProcesses() {
        return List.of();
    }

    /**
     * Provides information about the format used by the distributor.
     *
     * @return information about the format used by the distributor.
     *
     * @condition Mandatory if {@link Distribution#getDistributionFormats()} is empty.
     */
    @UML(identifier="distributorFormat", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Format> getDistributorFormats();

    /**
     * Provides information about the technical means and media used by the distributor.
     *
     * @return information about the technical means and media used by the distributor.
     */
    @UML(identifier="distributorTransferOptions", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends DigitalTransferOptions> getDistributorTransferOptions() {
        return List.of();
    }
}
