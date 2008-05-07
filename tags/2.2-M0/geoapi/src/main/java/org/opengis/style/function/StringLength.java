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
 * The function returns the number of characters in a string. The argument is converted to a
 * string before computing its length.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("StringLength")
public interface StringLength extends Function{
            
    /**
     * Get string value.
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
        
}