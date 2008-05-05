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
 * The InterpolationPoints have to be specified in ascending order of Data. They define a
 * graph of points. LookupValues less than the Data value of the first InterpolationPoint
 * are mapped to its corresponding Value. Accordingly, LookupValues greater than the
 * Data value of the last InterpolationPoint are mapped to the Value of this one.
 * LookupValues between two InterpolationPoints are interpolated between the
 * corresponding Values.
 * 
 * Only numeric quantities are allowed for LookupValue and Data. Values are usually
 * numeric as well. The interpolation of color-values requires the attribute mode="color" at
 * the Interpolate element.s
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("InterpolationPoint")
public interface InterpolationPoint {

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
