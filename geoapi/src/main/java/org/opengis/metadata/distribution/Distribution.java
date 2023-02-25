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
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information about the distributor of and options for obtaining the resource.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - Format
 * @navassoc - - - Distributor
 * @navassoc - - - DigitalTransferOptions
 */
@UML(identifier="MD_Distribution", specification=ISO_19115)
public interface Distribution {
    /**
     * Provides a description of the format of the data to be distributed.
     *
     * @return Description of the format of the data to be distributed.
     *
     * @condition {@link Distributor#getDistributorFormats()} not documented.
     */
    @Profile(level=CORE)
    @UML(identifier="distributionFormat", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Format> getDistributionFormats();

    /**
     * Provides information about the distributor.
     *
     * @return Information about the distributor.
     */
    @UML(identifier="distributor", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Distributor> getDistributors();

    /**
     * Provides information about technical means and media by which a resource is obtained
     * from the distributor.
     *
     * @return Technical means and media by which a resource is obtained from the distributor.
     */
    @Profile(level=CORE)
    @UML(identifier="transferOptions", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends DigitalTransferOptions> getTransferOptions();
}
