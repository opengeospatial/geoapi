/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;


/**
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
public interface Font {
    /**
     * Indicates the name of the font or font family to use.  Any number of
     * comma-separated values may be provided and they are assumed to be in
     * preferred order.  The list of available font families is system
     * dependent.  If null, a system dependent default will be used.
     */
    public Expression getFamily();

    /**
     * Indicates the name of the font or font family to use.  Any number of
     * comma-separated values may be provided and they are assumed to be in
     * preferred order.  The list of available font families is system
     * dependent.  If null, a system dependent default will be used.
     */
    public void setFamily(Expression expression);

    /**
     * Expression that indicates the style of the font.  Allowed values are
     * "normal", "italic", and "oblique".  If null, the default is "normal".
     */
    public Expression getStyle();

    /**
     * Expression that indicates the style of the font.  Allowed values are
     * "normal", "italic", and "oblique".  If null, the default is "normal".
     */
    public void setStyle(Expression expression);

    /**
     * Expression that indicates the weight of the font.  Allowed values are
     * "normal" and "bold".  If null, the default is "normal".
     */
    public Expression getWeight();

    /**
     * Expression that indicates the weight of the font.  Allowed values are
     * "normal" and "bold".  If null, the default is "normal".
     */
    public void setWeight(Expression expression);

    /**
     * Expression that indicates the pixel size of the font.  If null, the
     * default value is 10.
     */
    public Expression getSize();

    /**
     * Expression that indicates the pixel size of the font.  If null, the
     * default value is 10.
     */
    public void setSize(Expression expression);
}
