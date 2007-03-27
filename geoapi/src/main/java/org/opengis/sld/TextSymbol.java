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

import java.util.List;
import org.opengis.filter.expression.Expression;
import org.opengis.annotation.XmlElement;


/**
 * Indicates how text will be drawn.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("TextSymbolizer")
public interface TextSymbol extends Symbol {
    /**
     * Returns the expression that will be evaluated to determine what text is
     * displayed.
     */
    @XmlElement("Label")
    Expression getLabel();

    /**
     * Sets the expression that will be evaluated to determine what text is displayed.
     * See {@link #getLabel} for details.
     */
    @XmlElement("Label")
    void setLabel(Expression label);

    /**
     * Returns a list of Fonts to choose from when rendering this symbol.  The
     * renderer must choose the first one in the list that it is capable of
     * rendering.  The returned list is "live" and can be modified by the
     * caller.  (This is why there is no {@code setFonts} method.)
     */
    @XmlElement("Font")
    List<Font> getFonts();

    /**
     * Returns the object that indicates how the text should be placed with
     * respect to the feature geometry.  This object will either be an instance
     * of {@link LinePlacement} or {@link PointPlacement}.
     */
    @XmlElement("LabelPlacement")
    TextPlacement getPlacement();

    /**
     * Sets the object that indicates how the text should be placed with
     * respect to the feature geometry.
     * See {@link #getPlacement} for details.
     */
    @XmlElement("LabelPlacement")
    void setPlacement(TextPlacement placement);

    /**
     * Returns the object that indicates if a Halo will be drawn around the
     * text.  If null, a halo will not be drawn.
     */
    @XmlElement("Halo")
    Halo getHalo();

    /**
     * Sets the object that indicates if a Halo will be drawn around the
     * text.  If null, a halo will not be drawn.
     * See {@link #getHalo} for details.
     */
    @XmlElement("Halo")
    void setHalo(Halo halo);

    /**
     * Returns the object that indicates how the text will be filled.
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * Sets the object that indicates how the text will be filled.
     * See {@link #getFill} for details.
     */
    @XmlElement("Fill")
    void setFill(Fill fill);
}
