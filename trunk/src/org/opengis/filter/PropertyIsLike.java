package org.opengis.filter;

import org.opengis.filter.expression.Expression;

/**
 * Filter operator that performs the equivalent of the SQL "like" operator
 * on properties of a feature.
 */
public interface PropertyIsLike extends Filter {
    /**
     * Returns the expression whose value will be compared against the wildcard-
     * containing string provided by the getLiteral() method.
     */
    public Expression getExpression();

    /**
     * Returns the wildcard-containing string that will be used to check the
     * feature's properties.
     */
    public String getLiteral();

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to match any sequence of characters.  The default value for this
     * property is the one character string "%".
     */
    public String getWildCard();

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to match exactly one character.  The default value for this
     * property is the one character string "_".
     */
    public String getSingleChar();

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to prefix one of the wild card characters to indicate that it
     * should be matched literally in the content of the feature's property.
     * The default value for this property is the single character "'".
     */
    public String getEscape();
}
