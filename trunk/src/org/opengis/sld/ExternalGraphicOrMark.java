package org.opengis.sld;

/**
 * This class serves only as a common subclass for the two types of markers
 * that can appear as children of a <code>Graphic</code> object, namely
 * <code>Mark</code> and <code>ExternalGraphic</code>.
 */
public interface ExternalGraphicOrMark {
    public Object accept(StyleVisitor visitor, Object extraData);
}
