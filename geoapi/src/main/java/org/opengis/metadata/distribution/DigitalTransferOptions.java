/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.util.InternationalString;
import org.opengis.temporal.PeriodDuration;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Technical means and media by which a resource is obtained from the distributor.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_DigitalTransferOptions", specification=ISO_19115)
public interface DigitalTransferOptions {
    /**
     * Tiles, layers, geographic areas, <i>etc.</i>, in which data is available.
     * Units of distribution apply to both onLine and offLine distributions.
     *
     * @return tiles, layers, geographic areas, <i>etc.</i> in which data is available, or {@code null}.
     */
    @UML(identifier="unitsOfDistribution", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getUnitsOfDistribution() {
        return null;
    }

    /**
     * Estimated size of a unit in the specified transfer format, expressed in megabytes.
     * The transfer size shall be greater than zero.
     * Returns {@code null} if the transfer size is unknown.
     *
     * @return estimated size of a unit in the specified transfer format in megabytes, or {@code null}.
     */
    @UML(identifier="transferSize", obligation=OPTIONAL, specification=ISO_19115)
    default Double getTransferSize() {
        return null;
    }

    /**
     * Information about online sources from which the resource can be obtained.
     *
     * @return online sources from which the resource can be obtained.
     */
    @UML(identifier="onLine", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends OnlineResource> getOnLines() {
        return Collections.emptyList();
    }

    /**
     * Information about offline media on which the resource can be obtained.
     *
     * @return offline media on which the resource can be obtained.
     *
     * @since 3.1
     */
    @UML(identifier="offLine", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Medium> getOffLines() {
        return Collections.emptyList();
    }

    /**
     * Information about offline media on which the resource can be obtained.
     *
     * @return offline media on which the resource can be obtained, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getOffLines()}.
     */
    @Deprecated(since="3.1")
    Medium getOffLine();

    /**
     * Rate of occurrence of distribution.
     *
     * @return rate of occurrence of distribution, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="transferFrequency", obligation=OPTIONAL, specification=ISO_19115)
    default PeriodDuration getTransferFrequency() {
        return null;
    }

    /**
     * Formats of distribution.
     *
     * @return formats of distribution.
     *
     * @since 3.1
     */
    @UML(identifier="distributionFormat", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Format> getDistributionFormats() {
        return Collections.emptyList();
    }
}
