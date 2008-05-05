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
 * Interpolation: Transformation of continuous values by a function defined on a
 * number of nodes. This is used to adjust the value distribution of an attribute to the
 * desired distribution of a continuous symbolization control variable (like size,
 * width, color, etc).
 * 
 * In case the Categorize (or Interpolate) function is used inside a RasterSymbolizer as a
 * ColorMap, the LookupValue is set to the fixed value “Rasterdata”.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("Interpolate")
public interface Interpolate extends Function{
        
    public static enum METHOD{
        NUMERIC,
        COLOR
    };
    
    public static enum MODE{
        LINEAR,
        COSINE,
        CUBIC
    };
            
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
     * See {@link InterpolationPoint} for details.
     * @return Live list, this means you can modify it.
     */
    List<InterpolationPoint> getInterpolationPoints();
            
    /**
     * Get the interpolation mode.
     * 
     * @return LINEAR, COSINE or CUBIC.
     */
    @XmlElement("Mode")
    MODE getMode();
    
    /**
     * See {@link #getMode} for details.
     * 
     * @param mode 
     */
    @XmlElement("Mode")
    void setMode(MODE mode);
                    
    /**
     * Get the interpolation method.
     * 
     * @return NUMERIC or COLOR
     */
    @XmlElement("Method")
    METHOD getMethod();
    
    /**
     * See {@link #getMethod} for details.
     * 
     * @param method 
     */
    @XmlElement("Method")
    void setMethod(METHOD method);
    
}