package org.opengis.feature.simple;

import java.util.Date;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;


/**
 * Attribute bound to a Date class.
 * <p>
 * This class indicates getValue() returns a Date using Java 5
 * type narrowing, for for those working against java 1.4 interfaces
 * the additional methods getDate() and setDate have been
 * introduced.
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface TemporalAttribute extends Attribute {
    public void setValue(Date newValue);

    public Date getValue();

    /**
     * Java 1.4 type safe access to getValue
     * @return (Boolean) getValue()
     */
    public Date getDate();

    /**
     * Java 1.4 type safe access to setValue
     * @param newValue
     */
    public void setDate(Date newValue);
}
