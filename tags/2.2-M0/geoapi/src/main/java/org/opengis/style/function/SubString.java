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
package org.opengis.style.function;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Function;

/**
 * SE supports a repertoire of string manipulation functions. The following is a collection of
 * the most important functions for the purpose of symbolization of text.
 * 
 * The function shall react friendly on invalid Position and Length contents. Positions and
 * Lengths less or equal 0 shall yield the empty string. If the actual string length is less the
 * defined substring the existing part of the substring shall be returned.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("SubString")
public interface SubString extends Function{
        
    /**
     * Get value to substring.
     * 
     * The first argument StringValue is converted to a string value before applying the
     * substring operation.
     * 
     * @return Expression
     */
    @XmlElement("StringValue")
    Expression getStringValue();
    
    /**
     * See {@link #getStringValue} for details.
     * 
     * @param exp
     */
    @XmlElement("StringValue")
    void setStringValue(Expression exp);
    
    /**
     * Get the position.
     * If Position is not specified it is assumed as 1.
     *  
     * @return Expression
     */
    @XmlElement("Position")
    Expression getPosition();
    
    /**
     * Set the position.
     * See {@link #getPosition} for details.
     * 
     * @param exp 
     */
    @XmlElement("Position")
    void setPosition(Expression exp);
    
    /**
     * Get the length.
     * The default value for Length is the remaining length starting at Position.
     *  
     * @return Expression
     */
    @XmlElement("Length")
    Expression getLength();
    
    /**
     * Set the length.
     * See {@link #getLength} for details.
     * 
     * @param exp 
     */
    @XmlElement("Length")
    void setLength(Expression exp);
                
}