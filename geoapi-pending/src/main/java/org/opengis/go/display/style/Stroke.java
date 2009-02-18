/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

// J2SE direct dependencies
import java.awt.Color;


/**
 * Encapsulates the stroke attributes that can be applied to any line Graphic.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface Stroke {
    /**
     *  arrow direction attribute name.
     */
    String ARROW_DIRECTION = "STROKE_ARROW_DIRECTION";

    /**
     *  arrow style attribute name.
     */
    String ARROW_STYLE = "STROKE_ARROW_STYLE";

    /**
     *  color attribute name.
     */
    String COLOR = "STROKE_COLOR";

    /**
     *  dash array attribute name.
     */
    String DASH_ARRAY = "STROKE_DASH_ARRAY";

    /**
     *  dash offset attribute name.
     */
    String DASH_OFFSET = "STROKE_DASH_OFFSET";

    /**
     *  fill attribute name.
     */
    String FILL = "STROKE_FILL";

    /**
     *  line cap attribute name.
     */
    String LINE_CAP = "STROKE_LINE_CAP";

    /**
     *  line gap attribute name.
     */
    String LINE_GAP = "STROKE_LINE_GAP";

    /**
     *  line join attribute name.
     */
    String LINE_JOIN = "STROKE_LINE_JOIN";

    /**
     *  line style attribute name.
     */
    String LINE_STYLE = "STROKE_LINE_STYLE";

    /**
     *  opacity attribute name.
     */
    String OPACITY = "STROKE_OPACITY";

    /**
     *  width attribute name.
     */
    String WIDTH = "STROKE_WIDTH";

    /**
     * Returns the stroke begin arrow style value.
     * @return the stroke begin arrow style value.
     */
    ArrowStyle getBeginArrowStyle();

    /**
     * Returns whether the stroke begin arrow style value has been set.
     * @return true if the stroke begin arrow style value has been set, false otherwise.
     */
    boolean isBeginArrowStyleSet();

    /**
     * Sets the stroke begin arrow style value.
     * @param strokeArrowStyle the stroke begin arrow style value.
     */
    void setBeginArrowStyle(ArrowStyle strokeArrowStyle);

    /**
     * Sets the fact that the stroke begin arrow style value has been set.
     * @param flag true if the stroke begin arrow style value has been set, false otherwise.
     */
    void setBeginArrowStyleSet(boolean flag);

    /**
     * Returns the stroke end arrow style value.
     * @return the stroke end arrow style value.
     */
    ArrowStyle getEndArrowStyle();

    /**
     * Returns whether the stroke end arrow style value has been set.
     * @return true if the stroke end arrow style value has been set, false otherwise.
     */
    boolean isEndArrowStyleSet();

    /**
     * Sets the stroke end arrow style value.
     * @param strokeArrowStyle the stroke end arrow style value.
     */
    void setEndArrowStyle(ArrowStyle strokeArrowStyle);

    /**
     * Sets the fact that the stroke end arrow style value has been set.
     * @param flag true if the stroke end arrow style value has been set, false otherwise.
     */
    void setEndArrowStyleSet(boolean flag);

    /**
     * Returns the stroke color value.
     * @return the stroke color value.
     */
    Color getColor();

    /**
     * Returns whether the stroke color value has been set.
     * @return true if the stroke color value has been set, false otherwise.
     */
    boolean isColorSet();

    /**
     * Sets the stroke color value.
     * @param strokeColor the stroke color value.
     */
    void setColor(Color strokeColor);

    /**
     * Sets the fact that the stroke color value has been set.
     * @param flag true if the stroke color value has been set, false otherwise.
     */
    void setColorSet(boolean flag);

    /**
     * Returns the stroke dash array value.
     * @return the stroke dash array value.
     */
    DashArray getDashArray();

    /**
     * Returns whether the stroke dash array value has been set.
     * @return true if the stroke dash array value has been set, false otherwise.
     */
    boolean isDashArraySet();

    /**
     * Sets the stroke dash array value.
     * @param strokeDashArray the stroke dash array value.
     */
    void setDashArray(DashArray strokeDashArray);

    /**
     * Sets the fact that the stroke dash array value has been set.
     * @param flag true if the stroke dash array value has been set, false otherwise.
     */
    void setDashArraySet(boolean flag);

    /**
     * Returns the stroke dash offset value.
     * @return the stroke dash offset value.
     */
    float getDashOffset();

    /**
     * Returns whether the stroke dash offset value has been set.
     * @return true if the stroke dash offset value has been set, false otherwise.
     */
    boolean isDashOffsetSet();

    /**
     * Sets the stroke dash offset value.
     * @param strokeDashOffset the stroke dash offset value.
     */
    void setDashOffset(float strokeDashOffset);

    /**
     * Sets the fact that the stroke dash offset value has been set.
     * @param flag true if the stroke dash offset value has been set, false otherwise.
     */
    void setDashOffsetSet(boolean flag);

    /**
     * Returns the stroke fill value.
     * @return the stroke fill value.
     */
    Fill getFill();

    /**
     * Returns whether the stroke fill value has been set.
     * @return true if the stroke fill value has been set, false otherwise.
     */
    boolean isFillSet();

    /**
     * Sets the stroke fill value.
     * @param strokeFill the stroke fill value.
     */
    void setFill(Fill strokeFill);

    /**
     * Sets the fact that the stroke fill value has been set.
     * @param flag true if the stroke fill value has been set, false otherwise.
     */
    void setFillSet(boolean flag);

    /**
     * Returns the stroke line cap value.
     * @return the stroke line cap value.
     */
    LineCap getLineCap();

    /**
     * Returns whether the stroke line cap value has been set.
     * @return true if the stroke line cap value has been set, false otherwise.
     */
    boolean isLineCapSet();

    /**
     * Sets the stroke line cap value.
     * @param strokeLineCap the stroke line cap value.
     */
    void setLineCap(LineCap strokeLineCap);

    /**
     * Sets the fact that the stroke line cap value has been set.
     * @param flag true if the stroke line cap value has been set, false otherwise.
     */
    void setLineCapSet(boolean flag);

    /**
     * Returns the stroke line gap value.
     * @return the stroke line gap value.
     */
    float getLineGap();

    /**
     * Returns whether the stroke line gap value has been set.
     * @return true if the stroke line gap value has been set, false otherwise.
     */
    boolean isLineGapSet();

    /**
     * Sets the stroke line gap value.
     * @param strokeLineGap the stroke line gap value.
     */
    void setLineGap(float strokeLineGap);

    /**
     * Sets the fact that the stroke line gap value has been set.
     * @param flag true if the stroke line gap value has been set, false otherwise.
     */
    void setLineGapSet(boolean flag);

    /**
     * Returns the stroke line join value.
     * @return the stroke line join value.
     */
    LineJoin getLineJoin();

    /**
     * Returns whether the stroke line join value has been set.
     * @return true if the stroke line join value has been set, false otherwise.
     */
    boolean isLineJoinSet();

    /**
     * Sets the stroke line join value.
     * @param strokeLineJoin the stroke line join value.
     */
    void setLineJoin(LineJoin strokeLineJoin);

    /**
     * Sets the fact that the stroke line join value has been set.
     * @param flag true if the stroke line join value has been set, false otherwise.
     */
    void setLineJoinSet(boolean flag);

    /**
     * Returns the stroke line style value.
     * @return the stroke line style value.
     */
    LineStyle getLineStyle();

    /**
     * Returns whether the stroke line style value has been set.
     * @return true if the stroke line style value has been set, false otherwise.
     */
    boolean isLineStyleSet();

    /**
     * Sets the stroke line style value.
     * @param strokeLineStyle the stroke line style value.
     */
    void setLineStyle(LineStyle strokeLineStyle);

    /**
     * Sets the fact that the stroke line style value has been set.
     * @param flag true if the stroke line style value has been set, false otherwise.
     */
    void setLineStyleSet(boolean flag);

    /**
     * Returns the opacity value.
     * @return the opacity value.
     */
    float getOpacity();

    /**
     * Returns whether the opacity value has been set.
     * @return true if the opacity value has been set, false otherwise.
     */
    boolean isOpacitySet();

    /**
     * Sets the opacity value.
     * @param opacity the opacity value.
     */
    void setOpacity(float opacity);

    /**
     * Sets the fact that the opacity value has been set.
     * @param flag true if the opacity value has been set, false otherwise.
     */
    void setOpacitySet(boolean flag);

    /**
     * Returns the width value.
     * @return the width value.
     */
    float getWidth();

    /**
     * Returns whether the width value has been set.
     * @return true if the width value has been set, false otherwise.
     */
    boolean isWidthSet();

    /**
     * Sets the width value.
     * @param width the width value.
     */
    void setWidth(float width);

    /**
     * Sets the fact that the width value has been set.
     * @param flag true if the width value has been set, false otherwise.
     */
    void setWidthSet(boolean flag);
}
