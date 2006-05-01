/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
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
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@Extension
public interface SymbolVisitor {
    Object visit(LineSymbol    symbol, Object userData);
    Object visit(PointSymbol   symbol, Object userData);
    Object visit(PolygonSymbol symbol, Object userData);
    Object visit(TextSymbol    symbol, Object userData);
}
