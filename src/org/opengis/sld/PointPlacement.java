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

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Used in the {@link TextSymbol} class to indicate that text should be drawn starting at
 * a particular point and extending in a straight line.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
@XmlElement("PointPlacement")
public interface PointPlacement extends TextPlacement {
    /**
     * Returns an expression that indicates the position within the bounding
     * box of the text that is anchored to the geometry point.
     * This expression must evaluate to a floating point number between zero
     * and one with zero indicating the far left of the bounding box and one
     * indicating the far right of the bounding box.  If the expression is
     * null, the default value is zero.
     */
    @XmlElement("AnchorPointX")
    Expression getAnchorX();

    /**
     * Sets the expression that indicates the position within the bounding
     * box of the text that is anchored to the geometry point.
     *
     * @see #getAnchorX
     */
    @XmlElement("AnchorPointX")
    void setAnchorX(Expression e);

    /**
     * Returns an expression that indicates the position within the bounding
     * box of the text that is anchored to the geometry point.
     * This expression must evaluate to a floating point number between zero
     * and one with zero indicating the bottom of the bounding box and one
     * indicating the top of the bounding box.  If the expression is null, the
     * default value is zero.
     */
    @XmlElement("AnchorPointY")
    Expression getAnchorY();

    /**
     * Returns an expression that indicates the position within the bounding
     * box of the text that is anchored to the geometry point.
     *
     * @see #getAnchorY
     */
    @XmlElement("AnchorPointY")
    void setAnchorY(Expression e);

    /**
     * Returns an expression that computes a pixel offset from the geometry
     * point.  This offset point is where the text's anchor point gets located.
     * If this expression is null, the default offset of zero is used.
     */
    @XmlElement("DisplacementX")
    Expression getDisplacementX();

    /**
     * Sets the expression that computes a pixel offset from the geometry
     * point.
     *
     * @see #getDisplacementX
     */
    @XmlElement("DisplacementX")
    void setDisplacementX(Expression e);

    /**
     * Returns an expression that computes a pixel offset from the geometry
     * point.  This offset point is where the text's anchor point gets located.
     * If this expression is null, the default offset of zero is used.
     */
    @XmlElement("DisplacementY")
    Expression getDisplacementY();

    /**
     * Sets the expression that computes a pixel offset from the geometry
     * point.
     *
     * @see #getDisplacementY
     */
    @XmlElement("DisplacementY")
    void setDisplacementY(Expression e);

    /**
     * Returns the expression that will be evaluated to determine the rotation
     * of the text about its anchor point.  The rotation must evaluate to a
     * number that is interpreted as rotation clockwise in decimal degrees from
     * the normal orientation of the text.  If the expression is null, then the
     * default value of zero is used.
     */
    @XmlElement("Rotation")
    Expression getRotation();

    /**
     * Sets the expression that will be evaluated to determine the rotation
     * of the text about its anchor point.
     *
     * @see #getRotation
     */
    @XmlElement("Rotation")
    void setRotation(Expression e);
}
