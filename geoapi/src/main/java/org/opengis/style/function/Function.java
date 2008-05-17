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
 * SE extends the concept of ogc:expressions inherited from Filter Encoding to adequately
 * support the needs of symbolization in transforming and editing data. It does so by
 * introducing a couple of functions (se:Functions) which are substitutable for
 * ogc:expressions.
 * There are two general groups of functions for usage in SE. The first group is used to
 * transform raw values into “symbolizable” quantities. This especially comprises the
 * processes of categorization, recoding, and interpolation. This group of functions is
 * especially useful in all places using SvgParameters, making them dynamically related to
 * data values. The second group defines means for formatting data items like numbers,
 * strings, and dates. These functions are especially helpful to set up the Label element of
 * TextSymbolizers.
 * 
 * A conformant SE implementation need not implement any of the described functions,
 * however, if it does implement any of the functions specified, their working shall be as
 * described in this specification. The functions have to exhibit the described behavior only
 * if used in the context of Symbolizers. It is not necessary (though allowed) for an
 * implementation to make the functions also available in the context of filter expressions
 * elsewhere.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("Function")
public interface Function<T> extends Expression{
        
    /**
     * The value of the fallbackValue attribute is used as a default value, if the SE
     * implementation does not support the function. If the implementation supports the
     * function, then the result value is determined by executing the function.
     * 
     * @return String default value for this function
     */
    @XmlElement("fallbackValue")
    String getFallbackValue();
    
    /**
     * Set Fallback value
     * See {@link #getFallbackValue} for details.
     * 
     * @param value
     */
    @XmlElement("fallbackValue")
    void setFallbackValue(String value);
    
    /**
     * Once the functions parameters are set you can use this method
     * to get the result. 
     * 
     * @return
     */
    T evaluate();
    
}