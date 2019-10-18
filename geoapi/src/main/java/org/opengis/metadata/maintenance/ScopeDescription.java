/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.maintenance;

import java.util.Set;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the class of information covered by the information.
 * Exactly one of the {@code attributes}, {@code features}, {@code featureInstances},
 * {@code attributeInstances}, {@code dataset} and {@code other} properties shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.UNION)
@UML(identifier="MD_ScopeDescription", specification=ISO_19115)
public interface ScopeDescription {
    /**
     * Dataset to which the information applies.
     *
     * <div class="note"><b>Example:</b>
     * if a geographic data provider is generating vector mapping for the administrative areas
     * and if the data were processed in the same way, then the provider could record the bulk
     * of initial data at {@link ScopeCode#DATASET} level with a
     * “<cite>Administrative area A, B &amp; C</cite>” description.
     * </div>
     *
     * @return dataset to which the information applies, or {@code null}.
     *
     * @condition {@code features}, {@code attributes}, {@code featureInstances},
     *            {@code attributeInstances} and {@code other} not provided.
     *
     * @see ScopeCode#DATASET
     */
    @UML(identifier="dataset", obligation=CONDITIONAL, specification=ISO_19115)
    default String getDataset() {
        return null;
    }

    /**
     * Feature types to which the information applies.
     *
     * <div class="note"><b>Example:</b>
     * if an administrative area performs a complete re-survey of the road network,
     * the change can be recorded at {@link ScopeCode#FEATURE_TYPE} level with a
     * “<cite>Administrative area A — Road network</cite>” description.
     * </div>
     *
     * @return feature types to which the information applies.
     *
     * @condition {@code attributes}, {@code featureInstances}, {@code attributeInstances},
     *            {@code dataset} and {@code other} not provided.
     *
     * @see ScopeCode#FEATURE_TYPE
     */
    @UML(identifier="features", obligation=CONDITIONAL, specification=ISO_19115)
    default Set<? extends CharSequence> getFeatures() {
        return Collections.emptySet();
    }

    /**
     * Attribute types to which the information applies.
     *
     * <div class="note"><b>Example:</b>
     * if an administrative area detects an anomaly in all overhead clearance of the road survey,
     * the correction can be recorded at {@link ScopeCode#ATTRIBUTE_TYPE} level with a
     * “<cite>Administrative area A — Overhead clearance</cite>” description.
     * </div>
     *
     * @return attribute types to which the information applies.
     *
     * @condition {@code features}, {@code featureInstances}, {@code attributeInstances},
     *            {@code dataset} and {@code other} not provided.
     *
     * @see ScopeCode#ATTRIBUTE_TYPE
     */
    @UML(identifier="attributes", obligation=CONDITIONAL, specification=ISO_19115)
    default Set<? extends CharSequence> getAttributes() {
        return Collections.emptySet();
    }

    /**
     * Feature instances to which the information applies.
     *
     * <div class="note"><b>Example:</b>
     * if a new bridge is constructed in a road network,
     * the change can be recorded at {@link ScopeCode#FEATURE} level with a
     * “<cite>Administrative area A — New bridge</cite>” description.
     * </div>
     *
     * @return feature instances to which the information applies.
     *
     * @condition {@code features}, {@code attributes}, {@code attributeInstances},
     *            {@code dataset} and {@code other} not provided.
     *
     * @see ScopeCode#FEATURE
     */
    @UML(identifier="featureInstances", obligation=CONDITIONAL, specification=ISO_19115)
    default Set<? extends CharSequence> getFeatureInstances() {
        return Collections.emptySet();
    }

    /**
     * Attribute instances to which the information applies.
     *
     * <div class="note"><b>Example:</b>
     * if the overhead clearance of a new bridge was wrongly recorded,
     * the correction can be recorded at {@link ScopeCode#ATTRIBUTE} level with a
     * “<cite>Administrative area A — New bridge — Overhead clearance</cite>” description.
     * </div>
     *
     * @return attribute instances to which the information applies.
     *
     * @condition {@code features}, {@code attributes}, {@code featureInstances},
     *            {@code dataset} and {@code other} not provided.
     *
     * @see ScopeCode#ATTRIBUTE
     */
    @UML(identifier="attributeInstances", obligation=CONDITIONAL, specification=ISO_19115)
    default Set<? extends CharSequence> getAttributeInstances() {
        return Collections.emptySet();
    }

    /**
     * Class of information that does not fall into the other categories to which the information applies.
     *
     * @return class of information that does not fall into the other categories, or {@code null}.
     *
     * @condition {@code features}, {@code attributes}, {@code featureInstances},
     *            {@code attributeInstances} and {@code dataset} not provided.
     */
    @UML(identifier="other", obligation=CONDITIONAL, specification=ISO_19115)
    default InternationalString getOther() {
        return null;
    }
}
