/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * A character string comparison operator with pattern matching.
 * {@code LikeOperator} performs the equivalent of the SQL "{@code LIKE}" operator on properties of a feature.
 * The pattern contains a combination of regular characters, the {@linkplain #getWildCard() wildcard character},
 * the {@linkplain #getSingleChar() single character}, and the {@linkplain #getEscapeChar() escape character}.
 * The wildcard character matches zero or more characters.
 * The single character matches exactly one character.
 * The escape character is used to escape the meaning of the wildcard, single character and escape character itself.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="LikeOperator", specification=ISO_19143)
public interface LikeOperator<R> extends ComparisonOperator<R> {
    /**
     * Returns the nature of the comparison.
     * The default implementation returns {@code PROPERTY_IS_LIKE}.
     *
     * @return the nature of the comparison.
     */
    @Override
    default ComparisonOperatorName getOperatorType() {
        return ComparisonOperatorName.PROPERTY_IS_LIKE;
    }

    /**
     * Returns the expression whose values will be compared by this operator, together with the pattern.
     * The list content is as below:
     *
     * <ol>
     *   <li>The first expression provides the values to compare. It should evaluate to a character string.</li>
     *   <li>The second expression is the pattern. It is often specified in a {@link Literal} with a value which
     *       is a combination of regular characters, the {@linkplain #getWildCard() wildcard character}, the
     *       {@linkplain #getSingleChar() single character}, and the {@linkplain #getEscapeChar() escape character}.</li>
     * </ol>
     *
     * @return a list of size 2 containing the expression to be compared and the pattern, in that order.
     */
    @Override
    @UML(identifier="expression", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<? super R, ?>> getExpressions();

    /**
     * Returns the character that can be used in the pattern to match any sequence of characters.
     * The default value is the {@code "%"} character.
     *
     * @return the character for matching zero or more characters.
     */
    @UML(identifier="wildCard", obligation=MANDATORY, specification=ISO_19143)
    default char getWildCard() {
        return '%';
    }

    /**
     * Returns the character that can be used in the pattern to match exactly one character.
     * The default value is the {@code "_"} character.
     *
     * @return the character for matching exactly one character.
     */
    @UML(identifier="singleChar", obligation=MANDATORY, specification=ISO_19143)
    default char getSingleChar() {
        return '_';
    }

    /**
     * Returns the character that can be used in the pattern to prefix one of the wild card characters
     * to indicate that it should be matched literally in the content of the feature's property.
     * The default value is the {@code "\"} character.
     *
     * @return the character for escaping a wildcard character.
     */
    @UML(identifier="escapeChar", obligation=MANDATORY, specification=ISO_19143)
    default char getEscapeChar() {
        return '\\';
    }

    /**
     * Specifies how a filter expression processor should perform string comparisons.
     * A value of {@code true} means that string comparisons shall match case.
     * The value {@code false} means that string comparisons are performed caselessly.
     * The default value {@code true}.
     *
     * @return {@code true} if comparisons are case sensitive, otherwise {@code false}.
     */
    // Defined in OGC corrigendum, not yet in ISO 19143 (as of 2010 revision).
    default boolean isMatchingCase() {
        return true;
    }
}
