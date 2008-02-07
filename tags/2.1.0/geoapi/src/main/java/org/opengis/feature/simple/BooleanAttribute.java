package org.opengis.feature.simple;

import org.opengis.feature.Attribute;


/**
 * Attribute known to be bound to a Boolean class.
 * <p>
 * This class indicates getValue() returns a Boolean using Java 5
 * type narrowing, for for those working against java 1.4 interfaces
 * the additional methods getBoolean() and setBoolean have been
 * introduced.
 * </p>
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface BooleanAttribute extends Attribute {
    /**
     * Boolean value for this attribute.
     * Java 1.4 developers may wish to use getBoolean.
     * @return Boolean value
     */
    Boolean getValue();
    /**
     * Set the Boolean value for this attribute.
     * Java 1.4 developers may wish to use setBoolean.
     * @param newValue Boolean value for this attribute.
     */
    void setValue(Boolean newValue);

    /**
     * Java 1.4 type safe access to getValue
     * @return (Boolean) getValue()
     */
    Boolean getBoolean();

    /**
     * Java 1.4 type safe access to setValue
     * @param newValue
     */
    void setBoolean(Boolean newValue);
}
