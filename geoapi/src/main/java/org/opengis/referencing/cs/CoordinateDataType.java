/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.cs;

import java.time.temporal.ChronoUnit;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19111;


/**
 * Type (measure, integer or date) of coordinate values.
 * Most axes implicitly use the {@link #MEASURE} type.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see TimeCS#getCoordinateType()
 *
 * @since 3.1
 */
@Vocabulary(capacity=3)
@UML(identifier="CoordinateDataType", specification=ISO_19111)
public final class CoordinateDataType extends CodeList<CoordinateDataType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -7295884834031271882L;

    /**
     * Quantity expressed as a count used for a temporal or ordinal coordinate system axis.
     * The fractional values between integer values may have no meaning.
     */
    @UML(identifier="integer", obligation=CONDITIONAL, specification=ISO_19111)
    public static final CoordinateDataType INTEGER = new CoordinateDataType("INTEGER");

    /**
     * Quantity expressed as a measure used for a temporal coordinate system axis.
     * This is the usual type for coordinate system axes.
     */
    @UML(identifier="measure", obligation=CONDITIONAL, specification=ISO_19111)
    public static final CoordinateDataType MEASURE = new CoordinateDataType("MEASURE");

    /**
     * Numbers related to dates or times expressed in the proleptic Gregorian calendar.
     * This is used for a temporal coordinate system axis.
     * The time elapsed between two consecutive coordinate values may vary.
     *
     * <h4>Examples</h4>
     * <p>Coordinate values may be years expressed as a decimal year in the Gregorian calendar.
     * For example, 2017-03-25 in the Gregorian calendar is epoch 2017.23.
     * The number of days between two integer values may vary because of leap years.
     * For a coordinate system using this convention, {@link TimeCS#getTemporalUnit()}
     * should return {@link ChronoUnit#YEARS}.</p>
     *
     * <p>Coordinate values may be a count of months since a given year. For example, 1 = January 2000,
     * 2 = February 2000, 3 = March 2000, …, 13 = January 2001, 14 = February 2001, <i>etc</i>.
     * The number of days between two integer values may very because of different month lengths.
     * For a coordinate system using this convention, {@link TimeCS#getTemporalUnit()}
     * should return {@link ChronoUnit#MONTHS}.</p>
     */
    @UML(identifier="dateTime", obligation=CONDITIONAL, specification=ISO_19111)
    public static final CoordinateDataType DATE_TIME = new CoordinateDataType("DATE_TIME");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private CoordinateDataType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code CoordinateDataType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static CoordinateDataType[] values() {
        return values(CoordinateDataType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public CoordinateDataType[] family() {
        return values();
    }

    /**
     * Returns the coordinate data type that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static CoordinateDataType valueOf(String code) {
        return valueOf(CoordinateDataType.class, code, CoordinateDataType::new).get();
    }
}
