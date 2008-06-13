package org.opengis.feature.simple;

import org.opengis.feature.Attribute;
import org.opengis.geometry.BoundingBox;


/**
 * Attribute known to be bound to a BoundingBox class.
 * <p>
 * This class indicates getValue() returns a BoundingBox using Java 5
 * type narrowing, for for those working against java 1.4 interfaces
 * the additional methods getBoundingBox() and setBoundingBox have been
 * provided.
 *
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface BoundingBoxAttribute extends Attribute
{
    BoundingBox getValue();
    void setValue(BoundingBox newValue);

    /**
     * Java 1.4 type safe access to getValue
     * @return (BoundingBox) getValue()
     */
    BoundingBox getBoundingBox();

    /**
     * Java 1.4 type safe access to setValue
     * @param newValue
     */
    void setBoundingBox(BoundingBox newValue);
}
