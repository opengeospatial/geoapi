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
 * The function concatenates strings. It is used to create concatenated strings as arguments
 * of functions.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("Concatenate")
public interface Concatenate extends Function<String>{
        
    /**
     * Get values to concatenate.
     *  
     * @return Expression[]
     */
    @XmlElement("StringValue")
    Expression[] getStringValue();
    
    /**
     * See {@link #getStringValue} for details.
     * 
     * @param exps : Strings to concatenate
     */
    @XmlElement("StringValue")
    void setStringValue(Expression ... exps);
    
}