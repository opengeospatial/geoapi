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
package org.opengis.metadata.lineage;

import java.util.Collection;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.identification.RepresentativeFraction;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the source data used in creating the data specified by the scope.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc & - - RepresentativeFraction
 * @navassoc 1 - - ReferenceSystem
 * @navassoc 1 - - Citation
 * @navassoc - - - Extent
 * @navassoc - - - ProcessStep
 * @navassoc 1 - - Identifier
 * @navassoc 1 - - NominalResolution
 */
@UML(identifier="LI_Source", specification=ISO_19115)
public interface Source {
    /**
     * Detailed description of the level of the source data.
     *
     * @return Description of the level of the source data, or {@code null}.
     *
     * @condition {@linkplain #getSourceExtents() Source extent} not provided.
     */
    @UML(identifier="description", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Denominator of the representative fraction on a source map.
     *
     * @return Representative fraction on a source map, or {@code null}.
     */
    @UML(identifier="scaleDenominator", obligation=OPTIONAL, specification=ISO_19115)
    RepresentativeFraction getScaleDenominator();

    /**
     * Spatial reference system used by the source data.
     *
     * @return Spatial reference system used by the source data, or {@code null}.
     */
    @UML(identifier="sourceReferenceSystem", obligation=OPTIONAL, specification=ISO_19115)
    ReferenceSystem getSourceReferenceSystem();

    /**
     * Recommended reference to be used for the source data.
     *
     * @return Recommended reference to be used for the source data, or {@code null}.
     */
    @UML(identifier="sourceCitation", obligation=OPTIONAL, specification=ISO_19115)
    Citation getSourceCitation();

    /**
     * Information about the spatial, vertical and temporal extent of the source data.
     *
     * @return Information about the extent of the source data.
     *
     * @condition {@linkplain #getDescription() Description} not provided.
     */
    @UML(identifier="sourceExtent", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Extent> getSourceExtents();

    /**
     * Information about an event in the creation process for the source data.
     *
     * @return Information about an event in the creation process.
     */
    @UML(identifier="sourceStep", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ProcessStep> getSourceSteps();

    /**
     * Processing level of the source data.
     *
     * @return Processing level of the source data.
     *
     * @since 2.3
     */
    @UML(identifier="processedLevel", obligation=OPTIONAL, specification=ISO_19115_2)
    Identifier getProcessedLevel();

    /**
     * Distance between consistent parts (centre, left side, right side) of two adjacent
     * pixels.
     *
     * @return Distance between consistent parts of two adjacent pixels.
     *
     * @since 2.3
     */
    @UML(identifier="resolution", obligation=OPTIONAL, specification=ISO_19115_2)
    NominalResolution getResolution();
}
