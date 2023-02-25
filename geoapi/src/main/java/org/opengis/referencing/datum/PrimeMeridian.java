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
package org.opengis.referencing.datum;

import javax.measure.Unit;
import javax.measure.quantity.Angle;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A prime meridian defines the origin from which longitude values are determined.
 * The default {@linkplain #getName name} is "Greenwich", and that value shall be
 * used when the {@linkplain #getGreenwichLongitude greenwich longitude} value is
 * zero.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0.1
 * @since   1.0
 *
 * @navassoc 1 - - Unit
 */
@UML(identifier="CD_PrimeMeridian", specification=ISO_19111)
public interface PrimeMeridian extends IdentifiedObject {
    /**
     * Longitude of the prime meridian measured from the Greenwich meridian, positive eastward.
     * The {@code greenwichLongitude} initial value is zero, and that value shall be used
     * when the {@linkplain #getName meridian name} value is "Greenwich".
     *
     * @return The prime meridian Greenwich longitude, in {@linkplain #getAngularUnit angular unit}.
     */
    @UML(identifier="greenwichLongitude", obligation=CONDITIONAL, specification=ISO_19111)
    double getGreenwichLongitude();

    /**
     * Returns the angular unit of the {@linkplain #getGreenwichLongitude Greenwich longitude}.
     *
     * @return The angular unit of greenwich longitude.
     */
    @UML(identifier="getAngularUnit", specification=OGC_01009)
    Unit<Angle> getAngularUnit();
}
