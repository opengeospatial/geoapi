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

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;


/**
 * Indicates how text will be drawn.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
public interface TextSymbol extends Symbol {
    /**
     * Returns the expression that will be evaluated to determine what text is
     * displayed.
     */
    public Expression getLabel();

    /**
     * Sets the expression that will be evaluated to determine what text is
     * displayed.
     */
    public void setLabel(Expression label);

    /**
     * Returns a list of Fonts to choose from when rendering this symbol.  The
     * renderer must choose the first one in the list that it is capable of
     * rendering.  The returned list is "live" and can be modified by the
     * caller.  (This is why there is no "setFonts" method.)
     */
    public List getFonts();

    /**
     * Returns the object that indicates how the text should be placed with
     * respect to the feature geometry.  This object will either be an instance
     * of <code>LinePlacement</code> or <code>PointPlacement</code>.
     */
    public TextPlacement getPlacement();

    /**
     * Sets the object that indicates how the text should be placed with
     * respect to the feature geometry.  This object must either be an instance
     * of <code>LinePlacement</code> or <code>PointPlacement</code>.
     */
    public void setPlacement(TextPlacement placement);

    /**
     * Returns the object that indicates if a Halo will be drawn around the
     * text.  If null, a halo will not be drawn.
     */
    public Halo getHalo();

    /**
     * Sets the object that indicates if a Halo will be drawn around the
     * text.  If null, a halo will not be drawn.
     */
    public void setHalo(Halo halo);

    /**
     * Returns the object that indicates how the text will be filled.
     */
    public Fill getFill();

    /**
     * Sets the object that indicates how the text will be filled.
     */
    public void setFill(Fill fill);
}
