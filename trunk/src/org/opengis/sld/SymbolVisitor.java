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


/**
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
public interface SymbolVisitor {
    public Object visit(LineSymbol    symbol, Object userData);
    public Object visit(PointSymbol   symbol, Object userData);
    public Object visit(PolygonSymbol symbol, Object userData);
    public Object visit(TextSymbol    symbol, Object userData);
}
