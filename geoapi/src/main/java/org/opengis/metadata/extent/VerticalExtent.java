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
package org.opengis.metadata.extent;

import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Vertical domain of resource.
 *
 * @departure integration
 *   ISO 19115 provides two ways to define a coordinate reference system,
 *   with the restriction that only one of those two ways can be used:
 *   <ol>
 *     <li>{@code verticalCRS}   of type {@code VerticalCRS} (from ISO 19111),</li>
 *     <li>{@code verticalCRSId} of type {@code MD_ReferenceSystem} (from ISO 19115).</li>
 *   </ol>
 *   GeoAPI provides only the first way, because the {@code MD_ReferenceSystem} type
 *   has been intentionally omitted in order to have a single CRS framework (the ISO 19111 one).
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   1.0
 */
@UML(identifier="EX_VerticalExtent", specification=ISO_19115)
public interface VerticalExtent {
    /**
     * The lowest vertical extent contained in the resource.
     *
     * @return the lowest vertical extent.
     */
    @UML(identifier="minimumValue", obligation=MANDATORY, specification=ISO_19115)
    Double getMinimumValue();

    /**
     * The highest vertical extent contained in the resource.
     *
     * @return the highest vertical extent.
     */
    @UML(identifier="maximumValue", obligation=MANDATORY, specification=ISO_19115)
    Double getMaximumValue();

    /**
     * Provides information about the vertical coordinate reference system
     * to which the maximum and minimum elevation values are measured.
     * The CRS identification includes unit of measure.
     *
     * <p>This property is conditional in ISO 19115-1:2016 but mandatory in GeoAPI
     * because the alternative ({@code verticalCRSId}) is intentionally omitted in
     * order to have a single CRS framework: the ISO 19111 one.</p>
     *
     * @return the vertical CRS.
     */
    @UML(identifier="verticalCRS", obligation=CONDITIONAL, specification=ISO_19115)
    VerticalCRS getVerticalCRS();
}
