package org.opengis.sld;

import java.util.List;

import org.opengis.filter.expression.Expression;

/**
 * Not to be confused with GO-1's Graphic, this represents a little picture,
 * such as a GIF or JPG, that can be used in rendering.  Multiple little
 * pictures can be overlayed at the same spot.
 */
public interface Graphic {
    /**
     * Returns the list of external image files or marks that comprise this
     * graphic.  The returned list is "live" and can be modified by the user.
     * All elements of the list must be instances of either <code>Mark</code>
     * or <code>ExternalGraphic</code>.
     */
    public List getExternalGraphicOrMark();

    /**
     * Returns the expression that will be evaluated to determine the opacity
     * of the graphic when it is rendered.  This may return null if the graphic
     * is to be rendered completely opaque.  The opacity value must range from
     * 0.0 to 1.0, with 0.0 being completely transparent and 1.0 being
     * completely opaque.
     */
    public Expression getOpacity();

    /**
     * Sets the expression that will be evaluated to determine the opacity of
     * the graphic when it is rendered.  This may be set to null if the graphic
     * is to be rendered completely opaque.  The opacity value must range from
     * 0.0 to 1.0, with 0.0 being completely transparent and 1.0 being
     * completely opaque.
     */
    public void setOpacity(Expression opacityExpression);

    /**
     * Returns the expression that will be evaluated to determine the pixel
     * height of the image when it is drawn on the screen.  This can return
     * null if the inherent size of the image is to be used.  If a size
     * expression is specified, then the height is scaled to the result and the
     * width is scaled according to the aspect ratio of the original image.
     * Some image types, such as SVG or CGM, may not have an inherent pixel
     * size.  For such images, the default size is 16 pixels.
     */
    public Expression getSize();

    /**
     * Sets the expression that will be evaluated to determine the pixel height
     * of the image when it is drawn on the screen.  This can be set to null if
     * the inherent size of the image is to be used.  If a size expression is
     * specified, then the height is scaled to the result and the width is
     * scaled according to the aspect ratio of the original image.  Some image
     * types, such as SVG or CGM, may not have an inherent pixel size.  For such
     * images, the default size is 16 pixels.
     */
    public void setSize(Expression sizeExpression);

    /**
     * Returns the expression that will be used to calculate the rotation of the
     * graphic when it is drawn.  The value is interpreted as a rotation in
     * decimal degrees clockwise about the center point of the image.
     */
    public Expression getRotation();

    /**
     * Sets the expression that will be used to calculate the rotation of the
     * graphic when it is drawn.  The value is interpreted as a rotation in
     * decimal degrees clockwise about the center point of the image.
     */
    public void setRotation(Expression rotationExpression);
}
