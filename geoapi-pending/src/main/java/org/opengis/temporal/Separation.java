/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import java.time.temporal.TemporalAmount;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * Provides operations for calculating temporal length and distance.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 */
@UML(identifier="TM_Separation", specification=ISO_19108)
public interface Separation {
    /**
     * Returns the distance from this {@linkplain TemporalGeometricPrimitive temporal geometric
     * primitive} to another {@linkplain TemporalGeometricPrimitive temporal geometric primitive}.
     * This is the absolute value of the difference b/n their temporal positions.
     *
     * @return the distance from this {@linkplain TemporalGeometricPrimitive temporal geometric
     * primitive} to another {@linkplain TemporalGeometricPrimitive temporal geometric primitive}.
     */
    @UML(identifier="distance", obligation=MANDATORY, specification=ISO_19108)
    TemporalAmount distance(TemporalGeometricPrimitive other);

    /**
     * Returns the duration of this {@linkplain TemporalGeometricPrimitive temporal geometric
     * primitive}.
     *
     * @return duration of this {@linkplain TemporalGeometricPrimitive temporal geometric
     * primitive}.
     */
    @UML(identifier="length", obligation=MANDATORY, specification=ISO_19108)
    TemporalAmount length();
}
