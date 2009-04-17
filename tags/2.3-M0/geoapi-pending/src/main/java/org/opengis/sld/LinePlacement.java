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

import org.opengis.filter.expression.Expression;
import org.opengis.annotation.XmlElement;


/**
 * Helds by a {@link TextSymbol} to indicate that text should be drawn at some distance
 * from a line.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 * 
 * @deprecated use interfaces from style package : org.opengis.style.LinePlacement
 */
@XmlElement("PointPlacement")
public interface LinePlacement extends TextPlacement {
    /**
     * Returns the expression that is used to compute how far from the lines
     * the text will be drawn.  The distance must evaluate to a non-negative
     * number.
     */
    @XmlElement("PerpendicularOffset")
    Expression getPerpendicularOffset();

    /**
     * Sets the expression that is used to compute how far from the lines
     * the text will be drawn.
     * See {@link #getPerpendicularOffset} for details.
     */
    @XmlElement("PerpendicularOffset")
    void setPerpendicularOffset(Expression e);
}
