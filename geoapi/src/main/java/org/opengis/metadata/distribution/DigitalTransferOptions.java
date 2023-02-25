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
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Technical means and media by which a resource is obtained from the distributor.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - OnlineResource
 * @navassoc 1 - - Medium
 */
@UML(identifier="MD_DigitalTransferOptions", specification=ISO_19115)
public interface DigitalTransferOptions {
    /**
     * Tiles, layers, geographic areas, <cite>etc.</cite>, in which data is available.
     *
     * @return  Tiles, layers, geographic areas, <cite>etc.</cite> in which data is available, or {@code null}.
     */
    @UML(identifier="unitsOfDistribution", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getUnitsOfDistribution();

    /**
     * Estimated size of a unit in the specified transfer format, expressed in megabytes.
     * The transfer size is &gt; 0.0.
     * Returns {@code null} if the transfer size is unknown.
     *
     * @return Estimated size of a unit in the specified transfer format in megabytes, or {@code null}.
     */
    @UML(identifier="transferSize", obligation=OPTIONAL, specification=ISO_19115)
    Double getTransferSize();

    /**
     * Information about online sources from which the resource can be obtained.
     *
     * @return Online sources from which the resource can be obtained.
     */
    @Profile(level=CORE)
    @UML(identifier="onLine", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends OnlineResource> getOnLines();

    /**
     * Information about offline media on which the resource can be obtained.
     *
     * @return  offline media on which the resource can be obtained, or {@code null}.
     */
    @UML(identifier="offLine", obligation=OPTIONAL, specification=ISO_19115)
    Medium getOffLine();
}
