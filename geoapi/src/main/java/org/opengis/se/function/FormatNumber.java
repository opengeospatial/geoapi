/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.se.function;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

/**
 * One of the most needed is a function for formatting numbers to make them human
 * readable. You need such a function whenever a TextSymbolizer is employed to output a
 * numeric property value.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("FormatNumber")
public interface FormatNumber extends Function<String>{
        
    /**
     * Get value to format.
     * 
     * @return Expression
     */
    @XmlElement("NumericValue")
    Expression getNumericValue();
    
    /**
     * See {@link #getNumericValue} for details.
     * 
     * @param exp
     */
    @XmlElement("NumericValue")
    void setNumericValue(Expression exp);
    
    /**
     * Set the pattern format.
     * The Pattern is build-up of characters as follows :
     * 
     * <table>
     *  <tr><td>#</td><td>digit, which is omitted if irrelevant (leading or trailing zero)</td></tr>
     *  <tr><td>0</td><td>digit</td></tr>
     *  <tr><td>./td><td>decimal point</td></tr>
     *  <tr><td>,</td><td>grouping seperator</td></tr>
     *  <tr><td>E</td><td>exponent indicator</td></tr>
     *  <tr><td>-</td><td>minus sign</td></tr>
     *  <tr><td>'</td><td>apostrophe</td></tr> 
     * </table>
     * 
     * @return String
     */
    @XmlElement("Pattern")
    String getPattern();
    
    /**
     * Set the positive pattern.
     * See {@link #getPattern} for details.
     * 
     * @param pattern 
     */
    @XmlElement("Pattern")
    void setPattern(String pattern);
    
    /**
     * Get the negative pattern.
     * See {@link #getPattern} for details.
     * 
     * @return String
     */
    @XmlElement("NegativePattern")
    String getNegativePattern();
    
    /**
     * Set the negative pattern.
     * See {@link #getPattern} for details.
     * 
     * @param pattern 
     */
    @XmlElement("NegativePattern")
    void setNegativePattern(String pattern);
    
    /**
     * String value used for decimal point.
     * 
     * @return String : if null default value is "."
     */
    @XmlElement("decimalPoint")
    String getDecimalPoint();
    
    /**
     * Set the decimal point value.
     * See {@link #getDecimalPoint} for details.
     * 
     * @param point 
     */
    @XmlElement("decimalPoint")
    void setDecimalPoint(String point);
    
    /**
     * String value used for grouping separator.
     * 
     * @return String : if null default value is ","
     */
    @XmlElement("groupingSeparator")
    String getGroupingSeparator();
    
    /**
     * Set the grouping separator.
     * See {@link #getGroupingSeparator} for details.
     * 
     * @param separator 
     */
    @XmlElement("groupingSeparator")
    void setGroupingSeparator(String separator);
        
}