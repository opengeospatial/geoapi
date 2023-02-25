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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Geographic position of the resource. This is only an approximate reference
 * so specifying the coordinate reference system is unnecessary.
 * A precision up to 2 decimal places is usually sufficient.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="EX_GeographicBoundingBox", specification=ISO_19115)
public interface GeographicBoundingBox extends GeographicExtent {
    /**
     * The western-most coordinate of the limit of the resource extent.
     * The value is expressed in longitude in decimal degrees (positive east).
     *
     * @return the western-most longitude between -180 and +180°.
     * @unitof Angle
     */
    @UML(identifier="westBoundLongitude", obligation=MANDATORY, specification=ISO_19115)
    double getWestBoundLongitude();

    /**
     * The eastern-most coordinate of the limit of the resource extent.
     * The value is expressed in longitude in decimal degrees (positive east).
     *
     * @return the eastern-most longitude between -180 and +180°.
     * @unitof Angle
     */
    @UML(identifier="eastBoundLongitude", obligation=MANDATORY, specification=ISO_19115)
    double getEastBoundLongitude();

    /**
     * The southern-most coordinate of the limit of the resource extent.
     * The value is expressed in latitude in decimal degrees (positive north).
     *
     * @return the southern-most latitude between -90 and +90°.
     * @unitof Angle
     */
    @UML(identifier="southBoundLatitude", obligation=MANDATORY, specification=ISO_19115)
    double getSouthBoundLatitude();

    /**
     * The northern-most coordinate of the limit of the resource extent.
     * The value is expressed in latitude in decimal degrees (positive north).
     *
     * @return the northern-most latitude between -90 and +90°.
     * @unitof Angle
     */
    @UML(identifier="northBoundLatitude", obligation=MANDATORY, specification=ISO_19115)
    double getNorthBoundLatitude();
}
