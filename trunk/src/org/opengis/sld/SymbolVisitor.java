/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

// Annotations
import org.opengis.annotation.Extension;


/**
 * Visitor with {@code visit} methods to be called by {@link Symbol#accept Symbol.accept(...)}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
@Extension
public interface SymbolVisitor {
    Object visit(LineSymbol    symbol, Object userData);
    Object visit(PointSymbol   symbol, Object userData);
    Object visit(PolygonSymbol symbol, Object userData);
    Object visit(TextSymbol    symbol, Object userData);
}
