package org.opengis.sld;

/**
 * This class serves as an abstract base class for the two different classes
 * that specify the placement of text in a style, namely <code>PointPlacement</code>
 * and <code>LinePlacement</code>.
 */
public interface TextPlacement {
    public Object accept(StyleVisitor visitor, Object extraData);
}
