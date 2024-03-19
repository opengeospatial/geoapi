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
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A prime meridian defines the origin from which longitude values are determined.
 * Most geodetic datums use Greenwich as their prime meridian.
 *
 * <p>Constraints:</p>
 * <ul>
 *   <li>If the prime meridian {@linkplain #getName() name} is “Greenwich” then the value of
 *       {@linkplain #getGreenwichLongitude() Greenwich longitude} shall be 0 degrees.</li>
 *   <li>Conversely if the Greenwich longitude value is zero, then the prime meridian name
 *       shall be “Greenwich”.</li>
 * </ul>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.0.1
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createPrimeMeridian(String)
 * @see DatumFactory#createPrimeMeridian(Map, double, Unit)
 */
@UML(identifier="CD_PrimeMeridian", specification=ISO_19111, version=2007)
public interface PrimeMeridian extends IdentifiedObject {
    /**
     * Longitude of the prime meridian measured from the Greenwich meridian, positive eastward.
     * The {@code greenwichLongitude} default value is zero, and that value shall be used
     * when the {@linkplain #getName() meridian name} value is "Greenwich".
     *
     * @return the prime meridian Greenwich longitude, in {@linkplain #getAngularUnit angular unit}.
     * @unitof Angle
     */
    @UML(identifier="greenwichLongitude", obligation=CONDITIONAL, specification=ISO_19111)
    double getGreenwichLongitude();

    /**
     * Returns the angular unit of the {@linkplain #getGreenwichLongitude() Greenwich longitude}.
     *
     * @departure historic
     *   This attribute is inherited from an older OGC specification.
     *   In ISO 19111, {@code greenwichLongitude} is a property of type {@code Angle}
     *   rather than {@code double}, and the unit of measure is part of the {@code Angle} value.
     *
     * @return the angular unit of Greenwich longitude.
     */
    @UML(identifier="getAngularUnit", specification=OGC_01009)
    Unit<Angle> getAngularUnit();
}
