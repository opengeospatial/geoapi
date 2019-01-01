/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.observation;

import org.opengis.metadata.Metadata;
import org.opengis.metadata.quality.Element;
import org.opengis.temporal.TemporalObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;
import org.opengis.metadata.Identifier;

/**
 * Generic Observation event.
 * carries a generic  "result" properties of type "anyType".
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="Observation", specification=OGC_07022)
public interface Observation {

    /**
     * The name of the observation as an urn.
     */
    Identifier getName();

    /**
     * A description of the observation.
     */
    String getDefinition();

    /**
     * An Observation parameter is a general event-specific parameter.
     * This will typically be used to record environmental parameters,
     * or event-specific sampling parameters that are not tightly bound to either the feature-of-interest or the procedure.
     * NOTE: Parameters that are tightly bound to the procedure should be recorded as part of the procedure description.
     * For example, the SensorML model associates parameters with specific process elements or stages.
     * NOTE: The semantics of the parameter must be provided as part of its value.

     * In some applications it is convenient to use a generic or standard procedure,
     * or feature-of-interest, rather than define an event-specific process or feature.
     * In this context, event-specific parameters are bound to the Observation act.
     */
    @UML(identifier="parameter", obligation=OPTIONAL, specification=OGC_07022)
    Object getProcedureParameter();

    @UML(identifier="resultTime", obligation=OPTIONAL, specification=OGC_07022)
    TemporalObject getProcedureTime();

    /**
     * Instance-specific quality assessment or measure.
     * Allow multiple quality measures if required.
     */
    @UML(identifier="resultQuality", obligation=OPTIONAL, specification=OGC_07022)
    Element getQuality();

    @UML(identifier="metadata", obligation=OPTIONAL, specification=OGC_07022)
    Metadata getObservationMetadata();

    /**
    * The samplingTime is the time that the result applies to the feature-of-interest.
    * This is the time usually required for geospatial analysis of the result.
    */
    @UML(identifier="samplingTime", obligation=MANDATORY, specification=OGC_07022)
    TemporalObject getSamplingTime();

     /**
     * The featureOfInterest is a feature of any type (ISO 19109, ISO 19101),
     * which is a representation of the observation target,
     * being the real-world object regarding which the observation is made.
     * such as a specimen, station, tract, mountain, pixel, etc.
     * The spatial properties (location) of this feature of interest are typically of most interest for spatial analysis of the observation result.
     */
    @UML(identifier="featureOfInterest", obligation=MANDATORY, specification=OGC_07022)
    AnyFeature getFeatureOfInterest();

    /**
     * Property-type or phenomenon for which the observation result provides an estimate of its value.
     * for example "wavelength", "grass-species", "power", "intensity in the waveband x-y", etc.
     * It must be a property associated with the type of the feature of interest.
     * This feature-property that provides the (semantic) type of the observation.
     * The description of the phenomenon may be quite specific and constrained.
     * The description of the property-type may be presented using various alternative encodings.
     * If shown inline, the swe:Phenomenon schema is required.
     * If provided using another encoding (e.g. OWL or SWEET) then the description must be in a remote repository and xlink reference used.
     */
    @UML(identifier="observedProperty", obligation=MANDATORY, specification=OGC_07022)
    Phenomenon getObservedProperty();

    /**
     * The result contains the value generated by the procedure.
     * The type of the observation result must be consistent with the observed property, and the scale or scope for the value must be consistent with the quantity or category type.
     * Application profiles may choose to constrain the type of the result.
     *
     * an xsi:type attribute may appear in the instance to indicate the type of the result
     */
    @UML(identifier="result", obligation=MANDATORY, specification=OGC_07022)
    Object getResult();

    /**
     * The procedure is the description of a process used to generate the result.
     * It must be suitable for the observed property.
     * NOTE: At this level we do not distinguish between sensor-observations,
     * estimations made by an observer, or algorithms, simulations, computations and complex processing chains.
     */
    @UML(identifier="procedure", obligation=MANDATORY, specification=OGC_07022)
    Process getProcedure();
    
}
