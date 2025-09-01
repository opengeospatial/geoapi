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

import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the computer language construct that specifies the representation
 * of data objects in a record, file, message, storage device or transmission channel.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Format", specification=ISO_19115)
public interface Format {
    /**
     * Citation / URL of the specification format.
     *
     * @return citation / URL of the specification format.
     *
     * @since 3.1
     */
    @UML(identifier="formatSpecificationCitation", obligation=MANDATORY, specification=ISO_19115)
    Citation getFormatSpecificationCitation();

    /**
     * Name of a subset, profile, or product specification of the format.
     *
     * @return name of a subset, profile, or product specification of the format, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by
     * <code>{@linkplain #getFormatSpecificationCitation()}.{@linkplain Citation#getTitle() getTitle()}</code>.
     */
    @Deprecated(since="3.1")
    @UML(identifier="specification", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default InternationalString getSpecification() {
        Citation spec = getFormatSpecificationCitation();
        return (spec != null) ? spec.getTitle() : null;
    }

    /**
     * Name of the data transfer format(s).
     *
     * @return name of the data transfer format(s).
     *
     * @deprecated As of ISO 19115:2014, replaced by
     * <code>{@linkplain #getFormatSpecificationCitation()}.{@linkplain Citation#getAlternateTitles() getAlternateTitles()}</code>.
     * Note that citation alternate titles are often used for abbreviations.
     */
    @Deprecated(since="3.1")
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115, version=2003)
    default InternationalString getName() {
        Citation spec = getFormatSpecificationCitation();
        if (spec != null) {
            Iterator<? extends InternationalString> it = spec.getAlternateTitles().iterator();
            if (it.hasNext()) it.next();
        }
        return null;
    }

    /**
     * Version of the format (date, number, <i>etc</i>).
     *
     * @return version of the format.
     *
     * @deprecated As of ISO 19115:2014, replaced by
     * <code>{@linkplain #getFormatSpecificationCitation()}.{@linkplain Citation#getEdition() getEdition()}</code>.
     */
    @Deprecated(since="3.1")
    @UML(identifier="version", obligation=MANDATORY, specification=ISO_19115, version=2003)
    default InternationalString getVersion() {
        Citation spec = getFormatSpecificationCitation();
        return (spec != null) ? spec.getEdition() : null;
    }

    /**
     * Amendment number of the format version.
     *
     * @return amendment number of the format version, or {@code null}.
     */
    @UML(identifier="amendmentNumber", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getAmendmentNumber() {
        return null;
    }

    /**
     * Recommendations of algorithms or processes that can be applied to read or
     * expand resources to which compression techniques have been applied.
     *
     * @return processes that can be applied to read resources to which compression techniques have been applied,
     *         or {@code null}.
     */
    @UML(identifier="fileDecompressionTechnique", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getFileDecompressionTechnique() {
        return null;
    }

    /**
     * Media used by the format.
     *
     * @return media used by the format.
     *
     * @since 3.1
     */
    @UML(identifier="medium", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Medium> getMedia() {
        return List.of();
    }

    /**
     * Provides information about the distributor's format.
     *
     * @return information about the distributor's format.
     */
    @UML(identifier="formatDistributor", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Distributor> getFormatDistributors() {
        return List.of();
    }
}
