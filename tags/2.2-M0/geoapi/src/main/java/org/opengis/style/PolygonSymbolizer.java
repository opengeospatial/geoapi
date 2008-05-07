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

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;


/**
 * Holds the information that indicates how to draw the lines and the interior of polygons.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("PolygonSymbolizer")
public interface PolygonSymbolizer extends Symbolizer {
    
    /**
     * Returns the object containing all the information necessary to draw
     * styled lines.  This is used for the edges of polygons.
     * 
     * @return Stroke
     */
    @XmlElement("Stroke")
    Stroke getStroke();

    /**
     * Sets the object containing all the information necessary to draw styled lines.
     * This is used for the edges of polygons.
     * See {@link #getStroke} for details.
     * 
     * @param stroke 
     */
    @XmlElement("Stroke")
    void setStroke(Stroke stroke);

    /**
     * Returns the object that holds the information about how the interior of
     * polygons should be filled.  This may be null if the polygons are not to
     * be filled at all.
     * 
     * @return Fill
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * Sets the object the holds the information about how the interior of
     * polygons should be filled.  This may be null if the polygons are not
     * to be filled at all.
     * See {@link #getFill} for details.
     * 
     * @param fill 
     */
    @XmlElement("Fill")
    void setFill(Fill fill);
    
    /**
     * The Displacement gives the X and Y displacements from the original geometry. This
     * element may be used to avoid over-plotting of multiple PolygonSymbolizers for one
     * geometry or supplying "shadows" of polygon gemeotries. The displacements are in units
     * of pixels above and to the right of the point. The default displacement is X=0, Y=0.
     *
     * @return Displacement
     */
    @XmlElement("Displacement")
    Displacement getDisplacement();
    
    /**
     * Set the new displacement
     * See {@link #getDisplacement} for details.
     * 
     * @param disp : new displacement
     */
    @XmlElement("Displacement")
    void setDisplacement(Displacement disp);    
        
    /**
     * PerpendicularOffset works as defined for LineSymbolizer, allowing to draw polygons
     * smaller or larger than their actual geometry. The distance is in uoms and is positive to the
     * outside of the polygon. Negative numbers mean drawing the polygon smaller. The default
     * offset is 0.

     * @return Expression
     */
    @XmlElement("PerpendicularOffset")
    Expression getPerpendicularOffset();
    
    /**
     * Set the new perpendicular offset
     * See {@link #getPerpendicularOffSet} for details.
     * 
     * @param exp : new perpendicular offset
     */
    @XmlElement("PerpendicularOffset")
    void setPerpendicularOffset(Expression exp);
    
}
