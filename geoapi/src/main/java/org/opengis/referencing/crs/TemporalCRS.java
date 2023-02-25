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
package org.opengis.referencing.crs;

import java.util.Map;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 1-dimensional coordinate reference system used for the recording of time.
 *
 * <p>This type of CRS can be used with coordinate systems of type
 * {@link org.opengis.referencing.cs.TimeCS}.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createTemporalCRS(String)
 * @see CRSFactory#createTemporalCRS(Map, TemporalDatum, TimeCS)
 */
@UML(identifier="SC_TemporalCRS", specification=ISO_19111)
public interface TemporalCRS extends SingleCRS {
    /**
     * Returns the coordinate system, which shall be temporal.
     *
     * @return the temporal coordinate system.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    TimeCS getCoordinateSystem();

    /**
     * Returns the datum, which shall be temporal.
     */
    @Override
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    TemporalDatum getDatum();
}
