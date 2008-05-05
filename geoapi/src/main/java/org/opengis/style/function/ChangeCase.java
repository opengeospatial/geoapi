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
 * The function changes the case of the StringValue as indicated by the attribute direction.
 * Possible values of the latter are "toUpper" and "toLower", where the former is the default
 * value.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("ChangeCase")
public interface ChangeCase extends Function<String>{
        
    /**
     * possible directions
     */
    public static enum DIRECTION{
        TO_UPPER,
        TO_LOWER
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
     * Get the function direction.
     * 
     * @return TO_UPPER or TO_LOWER
     */
    @XmlElement("Direction")
    DIRECTION getDirection();
    
    /**
     * See {@link #getDirection} for details.
     * 
     * @param dir 
     */
    @XmlElement("Direction")
    void setDirection(DIRECTION dir);
    
}