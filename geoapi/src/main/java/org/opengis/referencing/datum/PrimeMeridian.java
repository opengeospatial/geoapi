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
 * Definition of the origin from which longitude values are determined.
 * Most geodetic reference frames use Greenwich as their prime meridian.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createPrimeMeridian(String)
 * @see DatumFactory#createPrimeMeridian(Map, double, Unit)
 */
@UML(identifier="PrimeMeridian", specification=ISO_19111)
public interface PrimeMeridian extends IdentifiedObject {
    /**
     * Longitude of the prime meridian measured from the internationally-recognized reference meridian,
     * positive eastward. If the {@linkplain #getName() meridian name} is "Greenwich", then this value
     * shall be zero.
     *
     * @return the prime meridian Greenwich longitude, in {@linkplain #getAngularUnit angular unit}.
     * @unitof Angle
     */
    @UML(identifier="greenwichLongitude", obligation=MANDATORY, specification=ISO_19111)
    double getGreenwichLongitude();

    /**
     * Returns the angular unit of the Greenwich longitude.
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
