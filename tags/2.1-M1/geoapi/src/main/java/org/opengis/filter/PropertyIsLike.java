/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// OpenGIS direct dependencies
import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;


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
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("PropertyIsLike")
public interface PropertyIsLike extends Filter {
    /**
     * Returns the expression whose value will be compared against the wildcard-
     * containing string provided by the getLiteral() method.
     */
    @XmlElement("PropertyName")
    Expression getExpression();

    /**
     * Expression whose value will be compared against getLiteral() using wildcards.
     * 
     * @param expression first of two expressions compared 
     */
    void setExpression( Expression expression);

    /**
     * Returns the wildcard-containing string that will be used to check the
     * feature's properties.
     */
    @XmlElement("Literal")
    String getLiteral();
    
    /**
     * The wildcard-containing string that will be used to check the expression.
     * 
     * @param literal wildcard-containing string used to check expression
     */
    void setLiteral( String literal );

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to match any sequence of characters.
     * <p>
     * The default value for this property is the one character string "%".
     */
    @XmlElement("wildCard")
    String getWildCard();
    
    /**
     * String that can be used in the "literal" property to match any sequence of characters.
     * @param wildCard Used to match any sequence of characters
     */
    void setWildCard( String wildCard );

    /**
     * Returns the string that can be used in the "literal" property of this
     * object to match exactly one character.
     * <p>
     * The default value for this property is the one character string "_".
     */
    @XmlElement("singleChar")
    String getSingleChar();

    /**
     * Can be used to match exactly one character.
     * 
     * @param singleChar Used to match exactly one character
     */
    void setSingleChar( String singleChar );
    
    /**
     * Returns the string that can be used in the "literal" property of this
     * object to prefix one of the wild card characters to indicate that it
     * should be matched literally in the content of the feature's property.
     * The default value for this property is the single character "'".
     */
    @XmlElement("escape")
    String getEscape();
    
    /**
     * Used to prefix a wild card character to indicate it should be used literally.
     * 
     * @param escape used to allow wildcard character to be matched literally
     */
    void setEscape( String escape );
}
