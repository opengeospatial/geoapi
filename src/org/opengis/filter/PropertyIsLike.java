package org.opengis.filter;

/**
 * Filter operator that performs the equivalent of the SQL "like" operator
 * on properties of a feature.
 */
public interface PropertyIsLike extends Filter {
    /**
     * Returns the property name that will be compared against the wildcard-
     * containing string provided by the getLiteral() method.
     */
    public String getPropertyName();

    /**
     * Sets the property name that will be compared against the wildcard-
     * containing string provided by the getLiteral() method.
     */
    public void setPropertyName(String propertyName);

    /**
     * Returns the wildcard-containing string that will be used to check the
     * feature's properties.
     */
    public String getLiteral();

    /**
     * Sets the wildcard-containing string that will be used to check the
     * feature's properties.
     */
    public void setLiteral(String wildcardString);

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to match any sequence of characters.  The default value for this
     * property is the one character string "%".
     */
    public String getWildCard();
    /**
     * Sets the string that can be used in the "literal" property of this object
     * to match any sequence of characters.  The default value for this property
     * is the one character string "%".
     */
    public void setWildCard(String wildcard);

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to match exactly one character.  The default value for this
     * property is the one character string "_".
     */
    public String getSingleChar();

    /**
     * Sets the string that can be used in the "literal" property of this object
     * to match exactly one character.  The default value for this property is
     * the one character string "_".
     */
    public void setSingleChar(String singleCharWildCard);

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to prefix one of the wild card characters to indicate that it
     * should be matched literally in the content of the feature's property.
     * The default value for this property is the single character "'".
     */
    public String getEscape();

    /**
     * Sets the string that can be used in the "literal" property of this
     * object to prefix one of the wild card characters to indicate that it
     * should be matched literally in the content of the feature's property.
     * The default value for this property is the single character "'".
     */
    public void setEscape(String escape);
}
