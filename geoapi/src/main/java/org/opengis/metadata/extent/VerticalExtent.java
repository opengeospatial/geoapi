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
package org.opengis.metadata.extent;

import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Vertical domain of dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - VerticalCRS
 */
@UML(identifier="EX_VerticalExtent", specification=ISO_19115)
public interface VerticalExtent {
    /**
     * Returns the lowest vertical extent contained in the dataset.
     *
     * @return Double mandatory for valid content, may be null for an invalid document.
     */
    @UML(identifier="minimumValue", obligation=MANDATORY, specification=ISO_19115)
    Double getMinimumValue();

    /**
     * Returns the highest vertical extent contained in the dataset.
     *
     * @return Double mandatory for valid content, may be null for an invalid document.
     */
    @UML(identifier="maximumValue", obligation=MANDATORY, specification=ISO_19115)
    Double getMaximumValue();

    /**
     * Provides information about the vertical coordinate reference system to
     * which the maximum and minimum elevation values are measured. The CRS
     * identification includes unit of measure.
     *
     * @return The vertical CRS.
     *
     * @departure integration
     *   ISO 19115 specifies a generic <code>CoordinateReferenceSystem</code> instead than the more
     *   restrictive <code>VerticalCRS</code>. GeoAPI uses the more specific type for type-safety and
     *   consistency with <code>VerticalExtent</code> usage. However this restriction prevents usage
     *   of <cite>Height above the ellipsoid</cite> when only the constants defined in the
     *   <code>VerticalDatumType</code> code list are used. If such height is wanted, implementors
     *   need to extend the above code list with their own <code>ELLIPSOIDAL</code> constant.
     *
     * @issue http://jira.codehaus.org/browse/GEO-134
     *
     * @since 2.1
     */
    @UML(identifier="verticalCRS", obligation=MANDATORY, specification=ISO_19115)
    VerticalCRS getVerticalCRS();
}
