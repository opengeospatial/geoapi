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
package org.opengis.referencing.datum;

import java.util.Map;
import java.util.Optional;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of a particular reference level surface used as a zero-height surface.
 * The description includes its position with respect to the planet for any of the height
 * types recognized by the ISO 19111 standard.
 *
 * <p>There are several types of vertical reference frames, and each may place constraints
 * on the {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis Coordinate Axis} with which
 * it is combined to create a {@linkplain org.opengis.referencing.crs.VerticalCRS Vertical CRS}.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 4.0
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createVerticalDatum(String)
 * @see DatumFactory#createVerticalDatum(Map, RealizationMethod)
 */
@UML(identifier="VerticalReferenceFrame", specification=ISO_19111)
public interface VerticalDatum extends Datum {
    /**
     * Returns the method through which this vertical reference frame is realized.
     *
     * @return method through which this vertical reference frame is realized.
     *
     * @since 3.1
     */
    @UML(identifier="realizationMethod", obligation=OPTIONAL, specification=ISO_19111)
    default Optional<RealizationMethod> getRealizationMethod() {
        return Optional.empty();
    }
}
