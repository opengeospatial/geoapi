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
 * you can implement the <code>SymbolVisitor</code> interface and the object
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
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
public interface StyleVisitor {
    public Object visit(LineSymbol    symbol, Object userData);
    public Object visit(PointSymbol   symbol, Object userData);
    public Object visit(PolygonSymbol symbol, Object userData);
    public Object visit(TextSymbol    symbol, Object userData);

    public Object visit(ExternalGraphic externalGraphic, Object userData);
    public Object visit(Mark mark, Object userData);

    public Object visit(LinePlacement  placement, Object userData);
    public Object visit(PointPlacement placement, Object userData);
}
