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
import org.opengis.filter.expression.Expression;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Helds by a {@link TextSymbol} to indicate that text should be drawn at some distance
 * from a line.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
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
