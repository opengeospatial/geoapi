/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.style;

import org.opengis.filter.expression.Expression;
import org.opengis.annotation.XmlElement;


/**
 * Indicates how text will be drawn.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("TextSymbolizer")
public interface TextSymbolizer extends Symbolizer {
    
    /**
     * Returns the expression that will be evaluated to determine what text is
     * displayed.
     * If a Label element is not provided in a TextSymbolizer, then no text shall be rendered.
     * 
     * @return Expression 
     */
    @XmlElement("Label")
    Expression getLabel();

    /**
     * Sets the expression that will be evaluated to determine what text is displayed.
     * See {@link #getLabel} for details.
     * @param label 
     */
    @XmlElement("Label")
    void setLabel(Expression label);

    /**
     * Returns a list of Fonts to choose from when rendering this symbol.  The
     * renderer must choose the first one in the list that it is capable of
     * rendering.  The returned list is "live" and can be modified by the
     * caller.  (This is why there is no {@code setFonts} method.)
     * @return Font
     */
    @XmlElement("Font")
    Font getFont();

    /**
     * Returns a list of Fonts to choose from when rendering this symbol.  The
     * renderer must choose the first one in the list that it is capable of
     * rendering.  The returned list is "live" and can be modified by the
     * caller.  (This is why there is no {@code setFonts} method.)
     * @param font : new font
     */
    @XmlElement("Font")
    void setFont(Font font);
    
    /**
     * Returns the object that indicates how the text should be placed with
     * respect to the feature geometry.  This object will either be an instance
     * of {@link LinePlacement} or {@link PointPlacement}.
     * @return {@link LinePlacement} or {@link PointPlacement}.
     */
    @XmlElement("LabelPlacement")
    LabelPlacement getLabelPlacement();

    /**
     * Sets the object that indicates how the text should be placed with
     * respect to the feature geometry.
     * See {@link #getPlacement} for details.
     * @param placement : {@link LinePlacement} or {@link PointPlacement}.
     */
    @XmlElement("LabelPlacement")
    void setLabelPlacement(LabelPlacement placement);

    /**
     * Returns the object that indicates if a Halo will be drawn around the
     * text.  If null, a halo will not be drawn.
     * @return Halo
     */
    @XmlElement("Halo")
    Halo getHalo();

    /**
     * Sets the object that indicates if a Halo will be drawn around the
     * text.  If null, a halo will not be drawn.
     * See {@link #getHalo} for details.
     * @param halo 
     */
    @XmlElement("Halo")
    void setHalo(Halo halo);

    /**
     * Returns the object that indicates how the text will be filled.
     * @return Fill
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * Sets the object that indicates how the text will be filled.
     * See {@link #getFill} for details.
     * @param fill 
     */
    @XmlElement("Fill")
    void setFill(Fill fill);
    
}
