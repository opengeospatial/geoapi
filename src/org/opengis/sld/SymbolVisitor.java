package org.opengis.sld;

public interface SymbolVisitor {
    public Object visit(LineSymbol    symbol, Object userData);
    public Object visit(PointSymbol   symbol, Object userData);
    public Object visit(PolygonSymbol symbol, Object userData);
    public Object visit(TextSymbol    symbol, Object userData);
}
