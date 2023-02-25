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
import javax.measure.Unit;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.metadata.Identifier;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the media on which the resource can be stored or distributed.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="MD_Medium", specification=ISO_19115)
public interface Medium {
    /**
     * Name of the medium on which the resource can be stored or distributed.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code MediumName} is replaced by {@link org.opengis.metadata.citation.Citation}.
     * This change may be applied in GeoAPI 4.0.
     * See <a href="https://github.com/opengeospatial/geoapi/issues/14">issue #14</a>.</div>
     *
     * @return name of the medium, or {@code null}.
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    default MediumName getName() {
        return null;
    }

    /**
     * Density at which the data is recorded.
     * The numbers shall be greater than zero.
     *
     * @return density at which the data is recorded, or {@code null}.
     *
     * @since 3.1
     */
    @UML(identifier="density", obligation=OPTIONAL, specification=ISO_19115)
    default Double getDensity() {
        return null;
    }

    /**
     * @deprecated As of ISO 19115:2014, replaced by {@link #getDensity()}.
     *
     * @return density at which the data is recorded.
     */
    @Deprecated
    default Collection<Double> getDensities() {
        Double density = getDensity();
        return (density != null) ? Collections.singleton(density) : Collections.emptySet();
    }

    /**
     * Units of measure for the recording density.
     *
     * @return units of measure for the recording density, or {@code null}.
     *
     * @condition The {@linkplain #getDensity() density} must be provided.
     */
    @UML(identifier="densityUnits", obligation=CONDITIONAL, specification=ISO_19115)
    default Unit<?> getDensityUnits() {
        return null;
    }

    /**
     * Number of items in the media identified.
     * Returns {@code null} if unknown.
     *
     * @return number of items in the media identified, or {@code null}.
     */
    @UML(identifier="volumes", obligation=OPTIONAL, specification=ISO_19115)
    default Integer getVolumes() {
        return null;
    }

    /**
     * Method used to write to the medium.
     *
     * @return method used to write to the medium, or {@code null}.
     */
    @UML(identifier="mediumFormat", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<MediumFormat> getMediumFormats() {
        return Collections.emptySet();          // Use Set instead of List for hash-safe final classes.
    }

    /**
     * Description of other limitations or requirements for using the medium.
     *
     * @return description of other limitations for using the medium, or {@code null}.
     */
    @UML(identifier="mediumNote", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getMediumNote() {
        return null;
    }

    /**
     * Unique identifier for an instance of the medium.
     *
     * @return unique identifier, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="identifier", obligation=OPTIONAL, specification=ISO_19115)
    default Identifier getIdentifier() {
        return null;
    }
}
