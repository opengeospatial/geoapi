/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;

// Annotations
import org.opengis.annotation.XmlSchema;


/**
 * Filter operator that performs the equivalent of the SQL "{@code like}" operator
 * on properties of a feature. The {@code PropertyIsLike} element is intended to encode
 * a character string comparison operator with pattern matching. The pattern is defined
 * by a combination of regular characters, the {@link #getWildCard wildCard} character,
 * the {@link #getSingleChar singleChar} character, and the {@link #getEscape escape}
 * character. The {@code wildCard} character matches zero or more characters. The
 * {@code singleChar} character matches exactly one character. The {@code escape}
 * character is used to escape the meaning of the {@code wildCard}, {@code singleChar}
 * and {@code escape} itself.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Filter encoding implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema(URL="filter.xsd", element="PropertyIsLike")
public interface PropertyIsLike extends Filter {
    /**
     * Returns the expression whose value will be compared against the wildcard-
     * containing string provided by the getLiteral() method.
     */
    Expression getExpression();

    /**
     * Returns the wildcard-containing string that will be used to check the
     * feature's properties.
     */
    String getLiteral();

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to match any sequence of characters.  The default value for this
     * property is the one character string "%".
     */
    String getWildCard();

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to match exactly one character.  The default value for this
     * property is the one character string "_".
     */
    String getSingleChar();

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to prefix one of the wild card characters to indicate that it
     * should be matched literally in the content of the feature's property.
     * The default value for this property is the single character "'".
     */
    String getEscape();
}
