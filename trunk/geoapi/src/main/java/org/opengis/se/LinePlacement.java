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
package org.opengis.se;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;


/**
 * The "LinePlacement" specifies where and how a text label should be rendered
 * relative to a line.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Ian Turton, CCG
 * @since GeoAPI 2.2
 */
@XmlElement("LinePlacement")
public interface LinePlacement extends LabelPlacement {
    
    /**
     * The PerpendicularOffset element of a LinePlacement gives the perpendicular distance
     * away from a line to draw a label.
     * 
     * The distance is in uoms and is positive to the left-hand side of the line string. Negative
     * numbers mean right. The default offset is 0.
     * 
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
    
    /**
     * InitialGap specifies how far away the first graphic will be drawn relative to the start of
     * the rendering line
     * 
     * @return Expression
     */
    @XmlElement("InitialGap")
    Expression getInitialGap();
    
    /**
     * Set the initialGap.
     * See {@link getInitialGap} for details.
     * 
     * @param exp 
     */
    @XmlElement("initialGap")
    void setInitialGap(Expression exp);
        
    /**
     * Gap gives the distance between two graphics.
     * 
     * @return Expression
     */
    @XmlElement("Gap")
    Expression getGap();
    
    /**
     * Set the Graphic gap.
     * See {@link getGap} for details.
     * 
     * @param exp
     */
    @XmlElement("Gap")
    void setGap(Expression exp);
        
    /**
     * If IsRepeated is "true", the label will be repeatedly drawn
     * along the line with InitialGap and Gap defining the spaces at the
     * beginning and between labels.
     * 
     * @return boolean
     */
    @XmlElement("IsRepeated")
    boolean isRepeated();
    
    /**
     * Set repeated.
     * See {@link #isRepeated} for details.
     * 
     * @param repeated
     */
    @XmlElement("IsRepeated")
    void setRepeated(boolean repeated);
    
    /**
     * Labels can either be aligned to the line geometry if IsAligned is "true" (the default) or are
     * drawn horizontally.
     * 
     * @return boolean
     */
    @XmlElement("IsAligned")
    boolean IsAligned();
    
    /**
     * Set aligned.
     * See {@link #isAligned} for details.
     * 
     * @param aligned
     */
    @XmlElement("IsAligned")
    void setAligned(boolean aligned);
    
    /**
     * GeneralizeLine allows the actual geometry, be it a
     * linestring or polygon to be generalized for label placement. This is e.g. useful for
     * labelling polygons inside their interior when there is need for the label to resemble the
     * shape of the polygon.
     * 
     * @return boolean
     */
    @XmlElement("GeneralizeLine")
    boolean isGeneralizeLine();
    
    /**
     * Set generalize.
     * See {@link #isGeneralizeLine} for details.
     * 
     * @param generalize 
     */
    @XmlElement("GeneralizeLine")
    void setGeneralizeLine(boolean generalize);
        
}
