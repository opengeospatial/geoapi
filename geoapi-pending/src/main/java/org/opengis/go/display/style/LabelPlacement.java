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


/**
 * Encapsulates the label placement attributes that can be applied to any text Graphic.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface LabelPlacement {
    /**
     * Label attribute name.
     */
    String LABEL = "LABELPLACEMENT_LABEL";

    /**
     * Label rotation attribute name.
     */
    String ROTATION = "LABELPLACEMENT_ROTATION";

    /**
     * Label X anchor attribute name.
     */
    String XANCHOR = "LABELPLACEMENT_XANCHOR";

    /**
     * Label X displacementattribute name.
     */
    String XDISPLACEMENT = "LABELPLACEMENT_XDISPLACEMENT";

    /**
     * Label Y anchor attribute name.
     */
    String YANCHOR = "LABELPLACEMENT_YANCHOR";

    /**
     * Label Y displacement attribute name.
     */
    String YDISPLACEMENT = "LABELPLACEMENT_YDISPLACEMENT";

    /**
     * Show label attribute name.
     */
    String SHOW_LABEL = "LABELPLACEMENT_SHOW_LABEL";

    /**
     * Returns the label.
     * @return the label.
     */
    String getLabel();

    /**
     * Returns whether the label has been set.
     * @return true if the label has been set, false otherwise.
     */
    boolean isLabelSet();

    /**
     * Sets the label.
     * @param label the label.
     */
    void setLabel(String label);

    /**
     * Sets the fact that the label has been set.
     * @param flag true if the label has been set, false otherwise.
     */
    void setLabelSet(boolean flag);

    /**
     * Returns the label rotation.
     * @return the label rotation.
     */
    float getRotation();

    /**
     * Returns whether the label rotation has been set.
     * @return true if the label rotation has been set, false otherwise.
     */
    boolean isRotationSet();

    /**
     * Sets the label rotation.
     * @param labelRotation the label rotation.
     */
    void setRotation(float labelRotation);

    /**
     * Sets the fact that the label rotation has been set.
     * @param flag true if the label rotation has been set, false otherwise.
     */
    void setRotationSet(boolean flag);

    /**
     * Returns the label XAnchor.
     * @return the label XAnchor.
     */
    XAnchor getXAnchor();

    /**
     * Returns whether the label XAnchor has been set.
     * @return true if the label XAnchor has been set, false otherwise.
     */
    boolean isXAnchorSet();

    /**
     * Sets the label XAnchor.
     * @param labelXAnchor the label XAnchor.
     */
    void setXAnchor(XAnchor labelXAnchor);

    /**
     * Sets the fact that the label XAnchor has been set.
     * @param flag true if the label XAnchor has been set, false otherwise.
     */
    void setXAnchorSet(boolean flag);

    /**
     * Returns the label X displacement.
     * @return the label X displacement.
     */
    float getXDisplacement();

    /**
     * Returns whether the label X displacement has been set.
     * @return true if the label X displacement has been set, false otherwise.
     */
    boolean isXDisplacementSet();

    /**
     * Sets the label X displacement.
     * @param labelXDisplacement the label X displacement.
     */
    void setXDisplacement(float labelXDisplacement);

    /**
     * Sets the fact that the label X displacement has been set.
     * @param flag true if the label X displacement has been set, false otherwise.
     */
    void setXDisplacementSet(boolean flag);

    /**
     * Returns the label YAnchor.
     * @return the label YAnchor.
     */
    YAnchor getYAnchor();

    /**
     * Returns whether the label YAnchor has been set.
     * @return true if the label YAnchor has been set, false otherwise.
     */
    boolean isYAnchorSet();

    /**
     * Sets the label YAnchor.
     * @param labelYAnchor the label YAnchor.
     */
    void setYAnchor(YAnchor labelYAnchor);

    /**
     * Sets the fact that the label YAnchor has been set.
     * @param flag true if the label YAnchor has been set, false otherwise.
     */
    void setYAnchorSet(boolean flag);

    /**
     * Returns the label Y displacement.
     * @return the label Y displacement.
     */
    float getYDisplacement();

    /**
     * Returns whether the label Y displacement has been set.
     * @return true if the label Y displacement has been set, false otherwise.
     */
    boolean isYDisplacementSet();

    /**
     * Sets the label Y displacement.
     * @param labelYDisplacement the label Y displacement.
     */
    void setYDisplacement(float labelYDisplacement);

    /**
     * Sets the fact that the label Y displacement has been set.
     * @param flag true if the label Y displacement has been set, false otherwise.
     */
    void setYDisplacementSet(boolean flag);

    /**
     * Returns the show label.
     * @return the show label.
     */
    boolean getShowLabel();

    /**
     * Returns whether the show label has been set.
     * @return true if the show label has been set, false otherwise.
     */
    boolean isShowLabelSet();

    /**
     * Sets the show label.
     * @param showLabel the show label.
     */
    void setShowLabel(boolean showLabel);

    /**
     * Sets the fact that the show label has been set.
     * @param flag true if the show label has been set, false otherwise.
     */
    void setShowLabelSet(boolean flag);
}
