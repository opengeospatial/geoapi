/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2021-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Nature of the temporal operation between two geometries.
 * Values are based on the 13 temporal relationships identified by Allen (1993),
 * with the addition of {@link #ANY_INTERACTS}.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Vocabulary(capacity=14)
@UML(identifier="TemporalOperatorName", specification=ISO_19143)
public final class TemporalOperatorName extends CodeList<TemporalOperatorName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6958011101187882290L;

    /**
     * Operator evaluates to {@code true} if the first expression is after the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two instants: {@code self > other}</li>
     *   <li>When comparing a period with an instant: {@code self.begin > other}</li>
     *   <li>When comparing two periods: {@code self.begin > other.end}</li>
     * </ul>
     */
    @UML(identifier="After", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName AFTER = new TemporalOperatorName("AFTER");

    /**
     * Operator evaluates to {@code true} if the first expression is before the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two instants: {@code self < other}</li>
     *   <li>When comparing a period with an instant: {@code self.end < other}</li>
     *   <li>When comparing two periods: {@code self.end < other.begin}</li>
     * </ul>
     */
    @UML(identifier="Before", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName BEFORE = new TemporalOperatorName("BEFORE");

    /**
     * Operator evaluates to {@code true} if the first expression begins at the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two periods: {@code self.begin = other.begin} AND {@code self.end < other.end}</li>
     * </ul>
     */
    @UML(identifier="Begins", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName BEGINS = new TemporalOperatorName("BEGINS");

    /**
     * Operator evaluates to {@code true} if the first expression begun by the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing a period with an instant: {@code self.begin = other}</li>
     *   <li>When comparing two periods: {@code self.begin = other.begin} AND {@code self.end > other.end}</li>
     * </ul>
     */
    @UML(identifier="BegunBy", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName BEGUN_BY = new TemporalOperatorName("BEGUN_BY");

    /**
     * Operator evaluates to {@code true} if the first expression is contained by the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing a period with an instant: {@code self.begin < other} AND {@code self.end > other}</li>
     *   <li>When comparing two periods: {@code self.begin < other.begin} AND {@code self.end > other.end}</li>
     * </ul>
     */
    @UML(identifier="TContains", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName CONTAINS = new TemporalOperatorName("CONTAINS");

    /**
     * Operator evaluates to {@code true} if the first expression is during the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two periods: {@code self.begin > other.begin} AND {@code self.end < other.end}</li>
     * </ul>
     */
    @UML(identifier="During", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName DURING = new TemporalOperatorName("DURING");

    /**
     * Operator evaluates to {@code true} if the first expression is equal to the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two instants: {@code self = other}</li>
     *   <li>When comparing two periods: {@code self.begin = other.begin} AND {@code self.end = other.end}</li>
     * </ul>
     */
    @UML(identifier="TEquals", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName EQUALS = new TemporalOperatorName("EQUALS");

    /**
     * Operator evaluates to {@code true} if the first expression overlaps the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two periods: {@code self.begin < other.begin}
     *       AND {@code self.end > other.begin}
     *       AND {@code self.end < other.end}</li>
     * </ul>
     */
    @UML(identifier="TOverlaps", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName OVERLAPS = new TemporalOperatorName("OVERLAPS");

    /**
     * Operator evaluates to {@code true} if the first expression meets the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two periods: {@code self.end = other.begin}</li>
     * </ul>
     */
    @UML(identifier="Meets", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName MEETS = new TemporalOperatorName("MEETS");

    /**
     * Operator evaluates to {@code true} if the first expression ends at the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two periods: {@code self.begin > other.begin} AND {@code self.end = other.end}</li>
     * </ul>
     */
    /*
     * This value is missing from UML and table 4 of ISO 19143:2010 but is present
     * in the XML schema definition. We presume that it has been forgotten.
     */
    @UML(identifier="Ends", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName ENDS = new TemporalOperatorName("ENDS");

    /**
     * Operator evaluates to {@code true} if the first expression is overlapped by the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two periods: {@code self.begin > other.begin}
     *       AND {@code self.begin < other.end}
     *       AND {@code self.end > other.end}</li>
     * </ul>
     */
    @UML(identifier="OverlappedBy", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName OVERLAPPED_BY = new TemporalOperatorName("OVERLAPPED_BY");

    /**
     * Operator evaluates to {@code true} if the first expression is met by the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing two periods: {@code self.begin = other.end}</li>
     * </ul>
     */
    @UML(identifier="MetBy", obligation=CONDITIONAL, specification=ISO_19143)
    public static final TemporalOperatorName MET_BY = new TemporalOperatorName("MET_BY");

    /**
     * Operator evaluates to {@code true} if the first expression is ended by the second.
     * In pseudo-code:
     * <ul>
     *   <li>When comparing a period with an instant: {@code self.end = other}</li>
     *   <li>When comparing two periods: {@code self.begin < other.begin} AND {@code self.end = other.end}</li>
     * </ul>
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
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private TemporalOperatorName(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code TemporalOperatorName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static TemporalOperatorName[] values() {
        return values(TemporalOperatorName.class);
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
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static TemporalOperatorName valueOf(String code) {
        return valueOf(TemporalOperatorName.class, code, TemporalOperatorName::new).get();
    }
}
