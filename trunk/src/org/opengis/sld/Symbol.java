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

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.Extension;


/**
 * Abstract superclass of the various symbol classes.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
public interface Symbol {
    /**
     * Returns the name of the geometry feature attribute to use for drawing.
     * May return null if this symbol is to use the default geometry attribute,
     * whatever it may be.
     */
    String getGeometryAttribute();

    /**
     * Sets the name of the geometry feature attribute that will be used for
     * drawing this symbol.
     * See {@link #getGeometryAttribute} for details.
     */
    void setGeometryAttribute(String name);

    /**
     * Returns a name for this symbol.
     * This can be any string that uniquely identifies this symbol within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    String getName();

    /**
     * Sets the name for this symbol.
     * See {@link #getName} for details.
     */
    void setName(String name);

    /**
     * Returns the human readable title of this symbol.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    InternationalString getTitle();

    /**
     * Sets the human readable title of this symbol.
     * See {@link #getTitle} for details.
     */
    void setTitle(InternationalString name);

    /**
     * Returns a human readable, prose description of this symbol.
     * This can be any string and can consist of any amount of text.
     */
    InternationalString getAbstract();

    /**
     * Sets the human readable, prose description of this symbol.
     * See {@link #getAbstract} for details.
     */
    void setAbstract(InternationalString abs);

    /**
     * Accepts a visitor.  Implementations of all subinterfaces must have with a
     * method whose content is the following:
     * <pre>return visitor.{@linkplain SymbolVisitor#visit visit}(this, extraData);</pre>
     */
    @Extension
    Object accept(StyleVisitor visitor, Object extraData);
}
