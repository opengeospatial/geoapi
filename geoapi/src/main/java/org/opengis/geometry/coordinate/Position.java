/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Point;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A type consisting of either a direct position or of a point from which a direct position shall be obtained.
 * The use of this data type allows the identification of a position either directly as a coordinate tuple
 * (variant direct) or indirectly as a {@linkplain Point point} (variant indirect).
 *
 * @departure constraint
 *   ISO 19107 defines {@code Position} as a {@code union} of {@code DirectPosition} and {@code Point}
 *   but unions are not allowed in Java. GeoAPI defines {@code Position} as the base interface of both
 *   types, and the Java {@code instanceof} operation should be used for determining the type.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @deprecated The {@code GM_Position} union has been removed in ISO 19107:2019.
 */
@Deprecated(since = "3.1")
@Classifier(Stereotype.UNION)
@UML(identifier="GM_Position", specification=ISO_19107, version=2003)
public interface Position {
    /**
     * Returns the direct position. This method shall never return {@code null}, but may return
     * {@code this} if invoked on an object which is already a {@code DirectPosition} instance.
     *
     * @return the direct position (may be {@code this}).
     */
    @UML(identifier="direct", obligation=CONDITIONAL, specification=ISO_19107)
    DirectPosition getDirectPosition();
}
