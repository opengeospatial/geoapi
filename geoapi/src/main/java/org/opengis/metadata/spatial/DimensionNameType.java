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
package org.opengis.metadata.spatial;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Name of the dimension.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_DimensionNameTypeCode", specification=ISO_19115)
public final class DimensionNameType extends CodeList<DimensionNameType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8534729639298737965L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<DimensionNameType> VALUES = new ArrayList<DimensionNameType>(8);

    /**
     * Ordinate (y) axis.
     */
    @UML(identifier="row", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DimensionNameType ROW = new DimensionNameType("ROW");

    /**
     * Abscissa (x) axis.
     */
    @UML(identifier="column", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DimensionNameType COLUMN = new DimensionNameType("COLUMN");

    /**
     * Vertical (z) axis.
     */
    @UML(identifier="vertical", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DimensionNameType VERTICAL = new DimensionNameType("VERTICAL");

    /**
     * Along the direction of motion of the scan point
     */
    @UML(identifier="track", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DimensionNameType TRACK = new DimensionNameType("TRACK");

    /**
     * Perpendicular to the direction of motion of the scan point.
     */
    @UML(identifier="crossTrack", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DimensionNameType CROSS_TRACK = new DimensionNameType("CROSS_TRACK");

    /**
     * Scan line of a sensor.
     */
    @UML(identifier="line", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DimensionNameType LINE = new DimensionNameType("LINE");

    /**
     * Element along a scan line.
     */
    @UML(identifier="sample", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DimensionNameType SAMPLE = new DimensionNameType("SAMPLE");

    /**
     * Duration.
     */
    @UML(identifier="time", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DimensionNameType TIME = new DimensionNameType("TIME");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private DimensionNameType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code DimensionNameType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static DimensionNameType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new DimensionNameType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public DimensionNameType[] family() {
        return values();
    }

    /**
     * Returns the dimension name type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static DimensionNameType valueOf(String code) {
        return valueOf(DimensionNameType.class, code);
    }
}
