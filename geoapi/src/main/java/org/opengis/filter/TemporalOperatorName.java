/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Nature of the temporal operation between two geometries.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="TemporalOperatorName", specification=ISO_19143)
public final class TemporalOperatorName extends CodeList<TemporalOperatorName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6958011101187882290L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<TemporalOperatorName> VALUES = new ArrayList<>(14);

    /**
     * Operator evaluates to {@code true} if the first expression is after the second.
     */
    @UML(identifier="After", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName AFTER = new TemporalOperatorName("AFTER");

    /**
     * Operator evaluates to {@code true} if the first expression is before the second.
     */
    @UML(identifier="Before", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName BEFORE = new TemporalOperatorName("BEFORE");

    /**
     * Operator evaluates to {@code true} if the first expression begins at the second.
     */
    @UML(identifier="Begins", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName BEGINS = new TemporalOperatorName("BEGINS");

    /**
     * Operator evaluates to {@code true} if the first expression begun by the second.
     */
    @UML(identifier="BegunBy", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName BEGUN_BY = new TemporalOperatorName("BEGUN_BY");

    /**
     * Operator evaluates to {@code true} if the first expression is contained by the second.
     */
    @UML(identifier="TContains", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName CONTAINS = new TemporalOperatorName("CONTAINS");

    /**
     * Operator evaluates to {@code true} if the first expression is during the second.
     */
    @UML(identifier="During", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName DURING = new TemporalOperatorName("DURING");

    /**
     * Operator evaluates to {@code true} if the first expression is equal to the second.
     */
    @UML(identifier="TEquals", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName EQUALS = new TemporalOperatorName("EQUALS");

    /**
     * Operator evaluates to {@code true} if the first expression overlaps the second.
     */
    @UML(identifier="TOverlaps", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName OVERLAPS = new TemporalOperatorName("OVERLAPS");

    /**
     * Operator evaluates to {@code true} if the first expression meets the second.
     */
    @UML(identifier="Meets", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName MEETS = new TemporalOperatorName("MEETS");

    /**
     * Operator evaluates to {@code true} if the first expression ends at the second.
     */
    /*
     * This value is missing from UML and table 4 of ISO 19143:2010 but is present
     * in the XML schema definition. We presume that it has been forgotten.
     */
    @UML(identifier="Ends", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName ENDS = new TemporalOperatorName("ENDS");

    /**
     * Operator evaluates to {@code true} if the first expression is overlapped by the second.
     */
    @UML(identifier="OverlappedBy", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName OVERLAPPED_BY = new TemporalOperatorName("OVERLAPPED_BY");

    /**
     * Operator evaluates to {@code true} if the first expression is met by the second.
     */
    @UML(identifier="MetBy", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName MET_BY = new TemporalOperatorName("MET_BY");

    /**
     * Operator evaluates to {@code true} if the first expression is ended by the second.
     */
    @UML(identifier="EndedBy", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName ENDED_BY = new TemporalOperatorName("ENDED_BY");

    /**
     * Shortcut operator semantically equivalent to NOT (Before OR Meets OR MetBy OR After).
     * This is applicable to periods only.
     */
    @UML(identifier="AnyInteracts", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName ANY_INTERACTS = new TemporalOperatorName("ANY_INTERACTS");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private TemporalOperatorName(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code TemporalOperatorName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static TemporalOperatorName[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(TemporalOperatorName[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public TemporalOperatorName[] family() {
        return values();
    }

    /**
     * Returns the temporal operator that matches the given string, or returns a new one if none match it.
     * More specifically, this methods returns the first instance for which
     * <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code> returns {@code true}.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static TemporalOperatorName valueOf(String code) {
        return valueOf(TemporalOperatorName.class, code);
    }
}
