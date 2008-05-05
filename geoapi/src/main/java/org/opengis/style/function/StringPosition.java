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
 * This function returns the position of the first occurrence (counting from 1) of the
 * LookupString in StringValue. Zero is returned in case of search failure. The direction
 * of search is determined by the attribute searchdirection, which can take values
 * "frontToBack" and "backToFront", where the former is the default.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("StringPosition")
public interface StringPosition extends Function<Integer>{
        
    /**
     * possible search directions.
     */
    public static enum SEARCH_DIRECTION{
        BACK_TO_FRONT,
        FRONT_TO_BACK
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
     * Get the function search direction.
     * 
     * @return BACK_TO_FRONT or FRONT_TO_BACK
     */
    @XmlElement("SearchDirection")
    SEARCH_DIRECTION getSearchDirection();
    
    /**
     * See {@link #getStripOffPosition} for details.
     * 
     * @param position
     */
    @XmlElement("SearchDirection")
    void setSearchDirection(SEARCH_DIRECTION position);
    
    /**
     * Get the string to look for.
     * 
     * @return String
     */
    @XmlElement("LookupString")
    String getLookupString();
    
    /**
     * See {@link #getLookupString} for details.
     * 
     * @param look
     */
    @XmlElement("LookupString")
    void setLookupString(String look);
    
    
}