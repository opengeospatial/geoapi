/*
 * Created on Aug 3, 2004
 */
package org.opengis.sld;

/**
 * Abstract super-interface of the various symbol classes.  The common
 * functionality is that every XxxSymbol class has an optional Geometry
 * attribute.
 * Not finished: needs setters and better documentation.
 */
public interface Symbol {
    /**
     * Returns the name of the geometry FeatureAttribute to use for drawing.
     * May return null if this symbol is to use the default geometry attribute,
     * whatever it may be.
     */
    public String getGeometryAttribute();
}
