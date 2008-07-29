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

import org.opengis.annotation.Extension;


/**
 * Interface that can be implemented by objects that want to perform some action
 * on style objects.  The visitor design pattern is used to avoid runtime type
 * checking of objects that descend from a common superclass.  So instead of
 * code that looks like this, with costly instanceof checks:
 * <pre>
 * Symbol s = ... ;
 * if (s instanceof LineSymbol) {
 *   // Do Line stuff
 * }
 * else if (s instanceof PointSymbol) {
 *   // Do Point stuff
 * }
 * ...
 * </pre>
 * you can implement the {@code SymbolVisitor} interface and the object
 * itself will invoke the appropriate method:
 * <pre>
 * class MyVisitor implements SymbolVisitor {
 *   public Object visit(LineSymbol symbol, Object userData) {
 *     // Do Line stuff
 *   }
 *   public Object visit(PointSymbol symbol, Object userData) {
 *     // Do Point stuff
 *   }
 *   ...
 * }
 * ...
 * Symbol s = ... ;
 * MyVisitor visitor = new MyVisitor(...);
 * Object o = s.accept(visitor, myData);
 * </pre>
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 * 
 * @deprecated use interfaces from style package : org.opengis.style.StyleVisitor
 */
@Extension
public interface StyleVisitor {
    Object visit(LineSymbol    symbol, Object userData);
    Object visit(PointSymbol   symbol, Object userData);
    Object visit(PolygonSymbol symbol, Object userData);
    Object visit(TextSymbol    symbol, Object userData);

    Object visit(ExternalGraphic externalGraphic, Object userData);
    Object visit(Mark mark, Object userData);

    Object visit(LinePlacement  placement, Object userData);
    Object visit(PointPlacement placement, Object userData);
}
