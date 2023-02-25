/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import java.util.Date;
import javax.measure.Unit;
import javax.measure.quantity.Time;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A temporal coordinate system to simplify  the computation of temporal distances
 * between points and the functional description of temporal operations.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_CoordinateSystem", specification=ISO_19108)
public interface TemporalCoordinateSystem extends TemporalReferenceSystem {

    /**
     * Returns position of the origin of the scale on which the temporal coordinate system is based
     * expressed as a date in the Gregorian calendar and time of day in UTC.
     *
     * @return position of the origin of the scale on which the temporal coordinate system is based
     * expressed as a date in the Gregorian calendar and time of day in UTC.
     */
    @UML(identifier="origin", obligation=MANDATORY, specification=ISO_19108)
    Date getOrigin();

    /**
     * Returns standard unit of time used to measure {@link Duration} on the axis of this {@link TemporalCoordinateSystem}.
     * It exprimate as a unit of measure specified by ISO 31-1,
     * or a multiple of one of those units, as specified by ISO 1000.
     *
     * @return standard unit of time used to measure {@link Duration} on the axis of this {@link TemporalCoordinateSystem}.
     */
    @UML(identifier="interval", obligation=MANDATORY, specification=ISO_19108)
    Unit<Time> getInterval();


    /**
     * Returns transformation of a value from a {@linkplain TemporalCoordinate coordinate} within this
     * temporal coordinate system into the equivalent {@linkplain DateAndTime date
     * and time} in the Gregorian Calendar and UTC.
     *
     * @param coordinates current {@linkplain TemporalCoordinate coordinate} coordinates which will be transform into UTC.
     * @return transformation of a value from a {@linkplain TemporalCoordinate coordinate} within this
     * temporal coordinate system into the equivalent {@linkplain DateAndTime date
     * and time} in the Gregorian Calendar and UTC.
     */
    @UML(identifier="transformCoord", obligation=MANDATORY, specification=ISO_19108)
    Date transformCoord(TemporalCoordinate coordinates);

    /**
     * Returns transformation of a {@linkplain DateAndTime date and time} in the Gregorian Calendar and UTC
     * into an equivalent {@linkplain TemporalCoordinate coordinate} within this temporal
     * coordinate system.
     *
     * @param datetime current UTC or {@link Date} coordinates which will be convert into this {@link TemporalCoordinateSystem}.
     * @return a transformation of a {@linkplain DateAndTime date and time} in the Gregorian Calendar and UTC
     * to an equivalent {@linkplain TemporalCoordinate coordinate} within this temporal
     * coordinate system.
     */
    @UML(identifier="transformDateTime", obligation=MANDATORY, specification=ISO_19108)
    TemporalCoordinate transformDateTime(Date datetime);
}
