/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
 * the {@linkplain #getSingleChar() single character}, and the escape character.
 * The wildcard character matches zero or more characters.
 * The single character matches exactly one character.
 * The escape character is used to escape the meaning of the wildcard, single character and escape character itself.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <T>  the type of inputs to filter.
 */
@UML(identifier="LikeOperator", specification=ISO_19143)
public interface LikeOperator<T> extends ComparisonOperator<T> {
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
     * Returns the expression to be compared by this operator, together with the pattern.
     * The list content is as below:
     *
     * <ol>
     *   <li>The first expression is the expression to compare and should evaluate to a character string.</li>
     *   <li>The second expression is the pattern. It is often specified in a {@link Literal}.</li>
     * </ol>
     *
     * @return a list of size 2 containing the expression to be compared and the pattern, in that order.
     */
    @Override
    @UML(identifier="expression", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<? super T, ?>> getExpressions();

   /**
     * Returns the character that can be used in the pattern to match any sequence of characters.
     * For the SQL "{@code LIKE}" operator, this property is the {@code %} character.
     *
     * @return the character for matching zero or more characters.
     */
    @UML(identifier="wildCard", obligation=MANDATORY, specification=ISO_19143)
    char getWildCard();

    /**
     * Returns the character that can be used in the pattern to match exactly one character.
     * For the SQL "{@code LIKE}" operator, this property is the {@code _} character.
     *
     * @return the character for matching exactly one character.
     */
    @UML(identifier="singleChar", obligation=MANDATORY, specification=ISO_19143)
    char getSingleChar();

    /**
     * Returns the character that can be used in the pattern to prefix one of the wild card characters
     * to indicate that it should be matched literally in the content of the feature's property.
     * For the SQL "{@code LIKE}" operator, this property is the {@code '} character.
     *
     * @return the character for escaping a wildcard character.
     */
    @UML(identifier="escapeChar", obligation=MANDATORY, specification=ISO_19143)
    char getEscapeChar();

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
