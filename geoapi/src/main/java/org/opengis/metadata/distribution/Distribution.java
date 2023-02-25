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

import java.util.Collection;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the distributor of and options for obtaining the resource.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Distribution", specification=ISO_19115)
public interface Distribution {
    /**
     * Brief description of a set of distribution options.
     *
     * @return brief description of a set of distribution options.
     *
     * @since 3.1
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getDescription() {
        return null;
    }

    /**
     * Provides a description of the format of the data to be distributed.
     *
     * @return description of the format of the data to be distributed.
     *
     * @condition Mandatory if {@link Distributor#getDistributorFormats()} is empty.
     *
     * @see org.opengis.metadata.identification.Identification#getResourceFormats()
     */
    @UML(identifier="distributionFormat", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Format> getDistributionFormats();

    /**
     * Provides information about the distributor.
     *
     * @return information about the distributor.
     */
    @UML(identifier="distributor", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Distributor> getDistributors() {
        return Collections.emptyList();
    }

    /**
     * Provides information about technical means and media by which a resource is obtained
     * from the distributor.
     *
     * @return technical means and media by which a resource is obtained from the distributor.
     */
    @UML(identifier="transferOptions", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends DigitalTransferOptions> getTransferOptions() {
        return Collections.emptyList();
    }
}
