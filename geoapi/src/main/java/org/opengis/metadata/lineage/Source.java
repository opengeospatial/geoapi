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
package org.opengis.metadata.lineage;

import java.util.List;
import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.maintenance.Scope;
import org.opengis.metadata.identification.Resolution;
import org.opengis.metadata.identification.RepresentativeFraction;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the resource used in creating the resource specified by the scope.
 *
 * At least one of the {@linkplain #getDescription() description} and
 * {@linkplain #getScope() scope} shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="LI_Source", specification=ISO_19115)
public interface Source {
    /**
     * Detailed description of the level of the source data.
     *
     * @return description of the level of the source data, or {@code null} if none.
     *
     * @condition Mandatory if the {@linkplain #getScope() scope} is not provided.
     */
    @UML(identifier="description", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Spatial resolution expressed as a scale factor, an angle or a level of detail.
     * May be {@code null} if none.
     *
     * @return spatial resolution expressed as a scale factor, an angle or a level of detail, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="sourceSpatialResolution", obligation=OPTIONAL, specification=ISO_19115)
    default Resolution getSourceSpatialResolution() {
        return null;
    }

    /**
     * Denominator of the representative fraction on a source map.
     *
     * @return representative fraction on a source map, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, moved to {@link Resolution#getEquivalentScale()}.
     */
    @Deprecated(since="3.1")
    @UML(identifier="scaleDenominator", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default RepresentativeFraction getScaleDenominator() {
        Resolution res = getSourceSpatialResolution();
        return (res != null) ? res.getEquivalentScale() : null;
    }

    /**
     * Spatial reference system used by the source data.
     *
     * @return spatial reference system used by the source data, or {@code null}.
     */
    @UML(identifier="sourceReferenceSystem", obligation=OPTIONAL, specification=ISO_19115)
    default ReferenceSystem getSourceReferenceSystem() {
        return null;
    }

    /**
     * Recommended reference to be used for the source data.
     *
     * @return recommended reference to be used for the source data, or {@code null}.
     */
    @UML(identifier="sourceCitation", obligation=OPTIONAL, specification=ISO_19115)
    default Citation getSourceCitation() {
        return null;
    }

    /**
     * References to metadata for the source.
     * Returns an empty collection if none.
     *
     * @return references to metadata for the source.
     *
     * @since 3.1
     */
    @UML(identifier="sourceMetadata", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getSourceMetadata() {
        return List.of();
    }

    /**
     * Type and / or extent of the source.
     * May be {@code null} if none.
     *
     * @return type and / or extent of the source, or {@code null} if none.
     *
     * @condition Mandatory if the {@linkplain #getDescription() description} is not provided.
     *
     * @since 3.1
     */
    @UML(identifier="scope", obligation=CONDITIONAL, specification=ISO_19115)
    Scope getScope();

    /**
     * Information about the spatial, vertical and temporal extent of the source data.
     *
     * @return information about the extent of the source data.
     *
     * @condition Mandatory if the {@linkplain #getDescription() description} is not provided.
     *
     * @deprecated As of ISO 19115:2014, moved to {@link Scope#getExtents()}.
     */
    @Deprecated(since="3.1")
    @UML(identifier="sourceExtent", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    default Collection<? extends Extent> getSourceExtents() {
        return List.of();
    }

    /**
     * Information about process steps in which this source was used.
     *
     * @return information about process steps in which this source was used.
     */
    @UML(identifier="sourceStep", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends ProcessStep> getSourceSteps() {
        return List.of();
    }

    /**
     * Processing level of the source data.
     *
     * @return processing level of the source data, or {@code null}.
     */
    @UML(identifier="processedLevel", obligation=OPTIONAL, specification=ISO_19115_2)
    default Identifier getProcessedLevel() {
        return null;
    }

    /**
     * Distance between consistent parts (centre, left side, right side) of two adjacent pixels.
     *
     * @return distance between consistent parts of two adjacent pixels.
     */
    @UML(identifier="resolution", obligation=OPTIONAL, specification=ISO_19115_2)
    default NominalResolution getResolution() {
        return null;
    }
}
