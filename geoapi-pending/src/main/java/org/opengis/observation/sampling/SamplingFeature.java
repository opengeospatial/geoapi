/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.observation.sampling;

import java.util.List;

import org.opengis.observation.AnyFeature;
import org.opengis.observation.Observation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A SamplingFeature is distinguished from typical domain feature types in that it has a set
 * of navigable associations with Observations.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="samplingFeature", specification=OGC_07022)
public interface SamplingFeature extends AnyFeature{

    /**
     * Sampling features are frequently related to each other, as parts of complexes, networks, through sub-sampling, etc.
     *	This is supported by the relatedSamplingFeature association with a SamplingFeatureRelation association class, which carries a source, target and role.
     */
    @UML(identifier="relatedSamplingFeature", obligation=MANDATORY, specification=OGC_07022)
    List<SamplingFeatureRelation> getRelatedSamplingFeature();

    /**
     * A common requirement for sampling features is an indication of the SurveyProcedure
     * that provides the surveyDetails related to determination of its location and shape.
     */
    @UML(identifier="surveyDetails", obligation=OPTIONAL, specification=OGC_07022)
    SurveyProcedure getSurveyDetail();

    /**
     * A SamplingFeature must be associated with one or more other features through an association role sampledFeature.
     * This association records the intention of the sample design.
     * The target of this association will usually be a domain feature.
     */
    @UML(identifier="sampleFeature", obligation=MANDATORY, specification=OGC_07022)
    List<AnyFeature> getSampledFeature();

    /**
     * A SamplingFeature is distinguished from typical domain feature types in that it has a set of [0..*] navigable associations with Observations, given the rolename relatedObservation.
     * This complements the association role "featureOfInterest" which is constrained to point back from the Observation to the Sampling-Feature.
     * The usual requirement of an Observation feature-of-interest is that its type has a property matching the observed-property on the Observation.
     * In the case of Sampling-features, the topology of the model and navigability of the relatedObservation role means that this requirement is satisfied automatically:
     * a property of the sampling-feature is implied by the observedProperty of a related observation.
     * This effectively provides an unlimited set of "soft-typed" properties on a Sampling Feature.
     */
    @UML(identifier="srelatedObservation", obligation=MANDATORY, specification=OGC_07022)
    List<Observation> getRelatedObservation();
    
}
