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
 * Nature of the comparison between expressions. A standard set of comparison operators is equal to,
 * less than, greater than, less than or equal to, greater than or equal to and not equal to.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="BinaryComparisonName", specification=ISO_19143)
public final class ComparisonOperatorName extends CodeList<ComparisonOperatorName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6384249667390069781L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ComparisonOperatorName> VALUES = new ArrayList<>(10);

    /**
     * Filter operator that compares that its two sub-expressions are equal to each other.
     */
    @UML(identifier="PropertyIsEqualTo", obligation=CONDITIONAL, specification=ISO_19143)
    public static final ComparisonOperatorName PROPERTY_IS_EQUAL_TO = new ComparisonOperatorName("PROPERTY_IS_EQUAL_TO");

    /**
     * Filter operator that compares that its two sub-expressions are not equal to each other.
     */
    @UML(identifier="PropertyIsNotEqualTo", obligation=CONDITIONAL, specification=ISO_19143)
    public static final ComparisonOperatorName PROPERTY_IS_NOT_EQUAL_TO = new ComparisonOperatorName("PROPERTY_IS_NOT_EQUAL_TO");

    /**
     * Filter operator that checks that its first sub-expression is less than its second sub-expression.
     */
    @UML(identifier="PropertyIsLessThan", obligation=CONDITIONAL, specification=ISO_19143)
    public static final ComparisonOperatorName PROPERTY_IS_LESS_THAN = new ComparisonOperatorName("PROPERTY_IS_LESS_THAN");

    /**
     * Filter operator that checks that its first sub-expression is greater than its second sub-expression.
     */
    @UML(identifier="PropertyIsGreaterThan", obligation=CONDITIONAL, specification=ISO_19143)
    public static final ComparisonOperatorName PROPERTY_IS_GREATER_THAN = new ComparisonOperatorName("PROPERTY_IS_GREATER_THAN");

    /**
     * Filter operator that checks that its first sub-expression is less than or equal to its second sub-expression.
     */
    @UML(identifier="PropertyIsLessThanOrEqualTo", obligation=CONDITIONAL, specification=ISO_19143)
    public static final ComparisonOperatorName PROPERTY_IS_LESS_THAN_OR_EQUAL_TO = new ComparisonOperatorName("PROPERTY_IS_LESS_THAN_OR_EQUAL_TO");

    /**
     * Filter operator that checks that its first sub-expression is greater or equal to its second sub-expression.
     */
    @UML(identifier="PropertyIsGreaterThanOrEqualTo", obligation=CONDITIONAL, specification=ISO_19143)
    public static final ComparisonOperatorName PROPERTY_IS_GREATER_THAN_OR_EQUAL_TO = new ComparisonOperatorName("PROPERTY_IS_GREATER_THAN_OR_EQUAL_TO");

    /**
     * Default value for {@link BetweenComparisonOperator#getOperatorType()}.
     */
    static final ComparisonOperatorName PROPERTY_IS_BETWEEN = new ComparisonOperatorName("PROPERTY_IS_BETWEEN");

    /**
     * Default value for {@link LikeOperator#getOperatorType()}.
     */
    static final ComparisonOperatorName PROPERTY_IS_LIKE = new ComparisonOperatorName("PROPERTY_IS_LIKE");

    /**
     * Default value for {@link NullOperator#getOperatorType()}.
     */
    static final ComparisonOperatorName PROPERTY_IS_NULL = new ComparisonOperatorName("PROPERTY_IS_NULL");

    /**
     * Default value for {@link NilOperator#getOperatorType()}.
     */
    static final ComparisonOperatorName PROPERTY_IS_NIL = new ComparisonOperatorName("PROPERTY_IS_NIL");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private ComparisonOperatorName(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ComparisonOperatorName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static ComparisonOperatorName[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(ComparisonOperatorName[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public ComparisonOperatorName[] family() {
        return values();
    }

    /**
     * Returns the comparison operators that matches the given string, or returns a new one if none match it.
     * More specifically, this methods returns the first instance for which
     * <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code> returns {@code true}.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static ComparisonOperatorName valueOf(String code) {
        return valueOf(ComparisonOperatorName.class, code);
    }
}
