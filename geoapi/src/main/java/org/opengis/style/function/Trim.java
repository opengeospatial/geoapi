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
 * The function strips off "leading", "trailing", or "both" chars from a string value.
 * Attributes control the mode of stripping and the character stripped.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("Trim")
public interface Trim extends Function{
        
    /**
     * possible stripOffPosition
     */
    public static enum STRIP_OFF_POSITION{
        LEADING,
        TRAILING,
        BOTH
    };
    
    /**
     * Get value to change case.
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
     * Get the function StripOffPosition.
     * Default is LEADING.
     * 
     * @return LEADING, TRAILING or BOTH
     */
    @XmlElement("StripOffPosition")
    STRIP_OFF_POSITION getStripOffPosition();
    
    /**
     * See {@link #getStripOffPosition} for details.
     * 
     * @param position
     */
    @XmlElement("StripOffPosition")
    void setStripOffPosition(STRIP_OFF_POSITION position);
    
    /**
     * Get the function stripOffChar.
     * Default is blank.
     * 
     * @return String
     */
    @XmlElement("StripOffChar")
    String getStripOffChar();
    
    /**
     * See {@link #getStripOffChar} for details.
     * 
     * @param ch 
     */
    @XmlElement("StripOffChar")
    void setStripOffChar(String ch);
    
    
}