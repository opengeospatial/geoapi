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

import java.util.List;
import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Function;

/**
 * Recoding: Transformation of discrete values to any other values. This is needed
 * when integers have to be translated into text or, reversely, text contents into other
 * texts or numeric values or colors.
 * 
 * This function recodes values from a property or expression into corresponding values of
 * arbitrary type. The comparisons are performed checking for identical values.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("recode")
public interface Recode extends Function{
                    
    /**
     * Get lookup value.
     *  
     * @return Expression
     */
    @XmlElement("LookupValue")
    Expression getLookupValue();
    
    /**
     * See {@link #getLookupValue} for details.
     * 
     * @param exp 
     */
    @XmlElement("LookupValue")
    void setLookupValue(Expression exp);
    
    /**
     * See {@link MapItem} for details.
     * @return Live list, this means you can modify it.
     */
    List<MapItem> getMapItems();
                
}