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
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Description of the computer language construct that specifies the representation
 * of data objects in a record, file, message, storage device or transmission channel.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - Distributor
 */
@UML(identifier="MD_Format", specification=ISO_19115)
public interface Format {
    /**
     * Name of the data transfer format(s).
     *
     * @return Name of the data transfer format(s).
     */
    @Profile(level=CORE)
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getName();

    /**
     * Version of the format (date, number, <i>etc</i>).
     *
     * @return Version of the format.
     */
    @Profile(level=CORE)
    @UML(identifier="version", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getVersion();

    /**
     * Amendment number of the format version.
     *
     * @return Amendment number of the format version, or {@code null}.
     */
    @UML(identifier="amendmentNumber", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getAmendmentNumber();

    /**
     * Name of a subset, profile, or product specification of the format.
     *
     * @return Name of a subset, profile, or product specification of the format, or {@code null}.
     */
    @UML(identifier="specification", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getSpecification();

    /**
     * Recommendations of algorithms or processes that can be applied to read or
     * expand resources to which compression techniques have been applied.
     *
     * @return Processes that can be applied to read resources to which compression techniques have
     *         been applied, or {@code null}.
     */
    @UML(identifier="fileDecompressionTechnique", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getFileDecompressionTechnique();

    /**
     * Provides information about the distributor's format.
     *
     * @return Information about the distributor's format.
     */
    @UML(identifier="formatDistributor", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Distributor> getFormatDistributors();
}
