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
 * Abstract superclass of the various symbol classes.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
public interface Symbol {
    /**
     * Returns the name of the geometry FeatureAttribute to use for drawing.
     * May return null if this symbol is to use the default geometry attribute,
     * whatever it may be.
     */
    public String getGeometryAttribute();

    /**
     * Sets the name of the geometry FeatureAttribute that will be used for
     * drawing this symbol.
     * May return null if this symbol is to use the default geometry attribute,
     * whatever it may be.
     */
    public void setGeometryAttribute(String name);

    /**
     * Returns a name for this symbol.
     * This can be any string that uniquely identifies this symbol within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    public String getName();

    /**
     * Sets the name for this symbol.
     * This can be any string that uniquely identifies this symbol within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    public void setName(String name);

    /**
     * Returns the human readable title of this symbol.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    public String getTitle();

    /**
     * Sets the human readable title of this symbol.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    public void setTitle(String name);

    /**
     * Returns a human readable, prose description of this symbol.
     * This can be any string and can consist of any amount of text.
     */
    public String getAbstract();

    /**
     * Sets the human readable, prose description of this symbol.
     * This can be any string and can consist of any amount of text.
     */
    public void setAbstract(String abs);

    /**
     * Helps implement the visitor design pattern.
     */
    public Object accept(StyleVisitor visitor, Object extraData);
}
