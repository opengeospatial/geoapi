package org.opengis.sld;

import java.util.List;

/**
 * Not to be confused with GO-1's Graphic, this represents a little picture,
 * such as a GIF or JPG, that can be used in rendering.
 */
public interface Graphic {
    /**
     * Returns pointer to a "live" list.  Must only contain ExternalGraphic
     * objects or Mark objects.
     */
    public List getExternalGraphicOrMark();
    
    public ParameterValue getOpacity();
    public ParameterValue getSize();
    public ParameterValue getRotation();
}
