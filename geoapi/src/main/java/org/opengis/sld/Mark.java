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

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Indicate that one of a few predefined shapes will be drawn at the points of the geometry.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Mark")
public interface Mark extends ExternalGraphicOrMark {
    /**
     * Returns the expression whose value will indicate the symbol to draw.
     * At least the following values must be accepted: "square", "circle",
     * "triangle", "star", "cross", or "x".  If null, the default is "square".
     */
    @XmlElement("WellKnownName")
    Expression getWellKnownName();

    /**
     * Sets the expression whose value will indicate the symbol to draw.
     * See {@link #getWellKnownName} for details.
     */
    @XmlElement("WellKnownName")
    void setWellKnownName(Expression name);

    /**
     * Returns the object that indicates how the mark should be filled.
     * Null means no fill.
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * Sets the object that indicates how the mark should be filled.
     * See {@link #getFill} for details.
     */
    @XmlElement("Fill")
    void setFill(Fill f);

    /**
     * Returns the object that indicates how the edges of the mark will be
     * drawn.  Null means that the edges will not be drawn at all.
     */
    @XmlElement("Stroke")
    Stroke getStroke();

    /**
     * Sets the object that indicates how the edges of the mark will be drawn.
     * See {@link #getStroke} for details.
     */
    @XmlElement("Stroke")
    void setStroke(Stroke s);
}
