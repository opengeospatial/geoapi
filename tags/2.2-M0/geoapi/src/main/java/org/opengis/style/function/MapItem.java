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

/**
 * This function recodes values from a property or expression into corresponding values of
 * arbitrary type. The comparisons are performed checking for identical values.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("MapItem")
public interface MapItem extends Expression{

    /**
     * Get value.
     * 
     * The Values can be of any type, dependent on which symbolization context the function is
     * employed. Color values (like #00ffff) or numeric values are typical.
     * 
     * @return Expression
     */
    @XmlElement("Value")
    Expression getValue();
    
    /**
     * See {@link #getValue} for details.
     * 
     * @param exp 
     */
    @XmlElement("Value")
    void setValue(Expression exp);
    
    /**
     * Get data.
     * 
     * @return double
     */
    @XmlElement("Data")
    double getData();
    
    /**
     * See {@link #getData} for details.
     * 
     * @param d
     */
    @XmlElement("Data")
    void setData(double d);
        
}
