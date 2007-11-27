package org.opengis.feature.simple;

import org.opengis.feature.Attribute;


/**
 * Attribute bound to a Numeric class.
 * <p>
 * This class indicates getValue() returns a Numeric using Java 5
 * type narrowing, for for those working against java 1.4 interfaces
 * the additional methods getNumber() and setNumber have been
 * introduced.
 *
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface NumericAttribute extends Attribute
{
    void setValue(Number newValue);
    Number getValue();
    /**
     * Java 1.4 type safe access to getValue
     * @return (Number) getValue()
     */
    Number getNumber();

    /**
     * Java 1.4 type safe access to setValue
     * @param newValue
     */
    void setNumber(Number newValue);
}
