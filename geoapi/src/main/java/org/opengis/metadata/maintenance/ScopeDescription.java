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
package org.opengis.metadata.maintenance;

import java.util.Set;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureType;

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
     * <q>Administrative area A, B &amp; C</q> description.
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
     * <q>Administrative area A — Road network</q> description.
     * </div>
     *
     * <div class="warning"><b>Upcoming API change</b><br>
     * As of ISO 19115:2014, the type become {@link Set<? extends CharSequence>}.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return feature types to which the information applies.
     *
     * @condition {@code attributes}, {@code featureInstances}, {@code attributeInstances},
     *            {@code dataset} and {@code other} not provided.
     *
     * @see ScopeCode#FEATURE_TYPE
     */
    @UML(identifier="features", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    default Set<? extends FeatureType> getFeatures() {
        return Set.of();
    }

    /**
     * Attribute types to which the information applies.
     *
     * <div class="note"><b>Example:</b>
     * if an administrative area detects an anomaly in all overhead clearance of the road survey,
     * the correction can be recorded at {@link ScopeCode#ATTRIBUTE_TYPE} level with a
     * <q>Administrative area A — Overhead clearance</q> description.
     * </div>
     *
     * <div class="warning"><b>Upcoming API change</b><br>
     * As of ISO 19115:2014, the type become {@link Set<? extends CharSequence>}.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return attribute types to which the information applies.
     *
     * @condition {@code features}, {@code featureInstances}, {@code attributeInstances},
     *            {@code dataset} and {@code other} not provided.
     *
     * @see ScopeCode#ATTRIBUTE_TYPE
     */
    @UML(identifier="attributes", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    default Set<? extends AttributeType> getAttributes() {
        return Set.of();
    }

    /**
     * Feature instances to which the information applies.
     *
     * <div class="note"><b>Example:</b>
     * if a new bridge is constructed in a road network,
     * the change can be recorded at {@link ScopeCode#FEATURE} level with a
     * <q>Administrative area A — New bridge</q> description.
     * </div>
     *
     * <div class="warning"><b>Upcoming API change</b><br>
     * As of ISO 19115:2014, the type become {@link Set<? extends CharSequence>}.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return feature instances to which the information applies.
     *
     * @condition {@code features}, {@code attributes}, {@code attributeInstances},
     *            {@code dataset} and {@code other} not provided.
     *
     * @see ScopeCode#FEATURE
     */
    @UML(identifier="featureInstances", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    default Set<? extends FeatureType> getFeatureInstances() {
        return Set.of();
    }

    /**
     * Attribute instances to which the information applies.
     *
     * <div class="note"><b>Example:</b>
     * if the overhead clearance of a new bridge was wrongly recorded,
     * the correction can be recorded at {@link ScopeCode#ATTRIBUTE} level with a
     * <q>Administrative area A — New bridge — Overhead clearance</q> description.
     * </div>
     *
     * <div class="warning"><b>Upcoming API change</b><br>
     * As of ISO 19115:2014, the type become {@link Set<? extends CharSequence>}.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return attribute instances to which the information applies.
     *
     * @condition {@code features}, {@code attributes}, {@code featureInstances},
     *            {@code dataset} and {@code other} not provided.
     *
     * @see ScopeCode#ATTRIBUTE
     */
    @UML(identifier="attributeInstances", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    default Set<? extends AttributeType> getAttributeInstances() {
        return Set.of();
    }

    /**
     * Class of information that does not fall into the other categories to which the information applies.
     *
     * <div class="warning"><b>Upcoming API change — internationalization</b><br>
     * The return type will be changed from {@code String} to {@code InternationalString} in GeoAPI 4.0.
     * </div>
     *
     * @return class of information that does not fall into the other categories, or {@code null}.
     *
     * @condition {@code features}, {@code attributes}, {@code featureInstances},
     *            {@code attributeInstances} and {@code dataset} not provided.
     */
    @UML(identifier="other", obligation=CONDITIONAL, specification=ISO_19115)
    default String getOther() {
        return null;
    }
}
