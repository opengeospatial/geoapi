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
package org.opengis.referencing.datum;

import java.util.Map;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Defines the location and precise orientation in 3-dimensional space of a defined ellipsoid
 * (or sphere) that approximates the shape of the earth. Used also for Cartesian coordinate
 * system centered in this ellipsoid (or sphere).
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see Ellipsoid
 * @see PrimeMeridian
 *
 * @see DatumAuthorityFactory#createGeodeticDatum(String)
 * @see DatumFactory#createGeodeticDatum(Map, Ellipsoid, PrimeMeridian)
 */
@UML(identifier="CD_GeodeticDatum", specification=ISO_19111)
public interface GeodeticDatum extends Datum {
    /**
     * Returns the ellipsoid.
     *
     * @return the ellipsoid.
     */
    @UML(identifier="ellipsoid", obligation=MANDATORY, specification=ISO_19111)
    Ellipsoid getEllipsoid();

    /**
     * Returns the prime meridian.
     *
     * @return the prime meridian.
     */
    @UML(identifier="primeMeridian", obligation=MANDATORY, specification=ISO_19111)
    PrimeMeridian getPrimeMeridian();
}
