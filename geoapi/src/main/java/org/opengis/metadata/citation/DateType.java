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
package org.opengis.metadata.citation;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of when a given event occurred
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="CI_DateTypeCode", specification=ISO_19115)
public final class DateType extends CodeList<DateType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 9031571038833329544L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<DateType> VALUES = new ArrayList<DateType>(3);

    /**
     * Date identifies when the resource was brought into existence.
     */
    @UML(identifier="creation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType CREATION = new DateType("CREATION");

    /**
     * Date identifies when the resource was issued.
     */
    @UML(identifier="publication", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType PUBLICATION = new DateType("PUBLICATION");

    /**
     * Date identifies when the resource was examined or re-examined and improved or amended.
     */
    @UML(identifier="revision", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DateType REVISION = new DateType("REVISION");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by another enum of this type.
     */
    private DateType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code DateType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static DateType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new DateType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind as this enum.
     */
    public DateType[] family() {
        return values();
    }

    /**
     * Returns the date type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static DateType valueOf(String code) {
        return valueOf(DateType.class, code);
    }
}
