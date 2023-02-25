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
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Level of detail expressed as a scale factor or a ground distance.
 * Exactly one of the {@linkplain #getEquivalentScale() equivalent scale}, {@linkplain #getDistance() distance},
 * {@linkplain #getVertical() vertical}, {@linkplain #getAngularDistance() angular distance} and
 * {@linkplain #getLevelOfDetail() level of detail} properties shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 *
 * @see Identification#getSpatialResolutions()
 */
@Classifier(Stereotype.UNION)
@UML(identifier="MD_Resolution", specification=ISO_19115)
public interface Resolution {
    /**
     * Level of detail expressed as the scale of a comparable hardcopy map or chart.
     *
     * @return level of detail expressed as the scale of a comparable hardcopy, or {@code null}.
     *
     * @condition {@code distance}, {@code vertical}, {@code angularDistance} and {@code levelOfDetail} not provided.
     */
    @UML(identifier="equivalentScale", obligation=CONDITIONAL, specification=ISO_19115)
    default RepresentativeFraction getEquivalentScale() {
        return null;
    }

    /**
     * Horizontal ground sample distance.
     *
     * <div class="warning"><b>Upcoming API change — units of measurement</b><br>
     * The return type of this method may change in GeoAPI 4.0. It may be replaced by the
     * {@link javax.measure.quantity.Length} type in order to provide unit of measurement
     * together with the value.
     * </div>
     *
     * @return the ground sample distance, or {@code null}.
     * @unitof Distance
     *
     * @condition {@code equivalentScale}, {@code vertical}, {@code angularDistance} and {@code levelOfDetail} not provided.
     */
    @UML(identifier="distance", obligation=CONDITIONAL, specification=ISO_19115)
    default Double getDistance() {
        return null;
    }

    /**
     * Vertical sampling distance.
     *
     * <div class="warning"><b>Upcoming API change — units of measurement</b><br>
     * The return type of this method may change in GeoAPI 4.0. It may be replaced by the
     * {@link javax.measure.quantity.Length} type in order to provide unit of measurement
     * together with the value.
     * </div>
     *
     * @return the vertical sampling distance, or {@code null}.
     * @unitof Distance
     *
     * @condition {@code equivalentScale}, {@code distance}, {@code angularDistance} and {@code levelOfDetail} not provided.
     *
     * @since 3.1
     */
    @UML(identifier="vertical", obligation=CONDITIONAL, specification=ISO_19115)
    default Double getVertical() {
        return null;
    }

    /**
     * Angular sampling measure.
     *
     * <div class="warning"><b>Upcoming API change — units of measurement</b><br>
     * The return type of this method may change in GeoAPI 4.0. It may be replaced by the
     * {@link javax.measure.quantity.Angle} type in order to provide unit of measurement
     * together with the value.
     * </div>
     *
     * @return the angular sampling measure, or {@code null}.
     * @unitof Angle
     *
     * @condition {@code equivalentScale}, {@code distance}, {@code vertical} and {@code levelOfDetail} not provided.
     *
     * @since 3.1
     */
    @UML(identifier="angularDistance", obligation=CONDITIONAL, specification=ISO_19115)
    default Double getAngularDistance() {
        return null;
    }

    /**
     * Brief textual description of the spatial resolution of the resource.
     *
     * @return textual description of the spatial resolution of the resource, or {@code null}.
     *
     * @condition {@code equivalentScale}, {@code distance}, {@code vertical} and {@code angularDistance} not provided.
     *
     * @since 3.1
     */
    @UML(identifier="levelOfDetail", obligation=CONDITIONAL, specification=ISO_19115)
    default InternationalString getLevelOfDetail() {
        return null;
    }
}
