package org.opengis.feature.simple;

import org.opengis.feature.Attribute;


/**
 * Attribute bound to a CharSequence.
 * <p>
 * This class indicates getValue() returns a CharSequence using Java 5
 * type narrowing, for for those working against java 1.4 interfaces
 * the additional methods getText() and setText have been
 * introduced.
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface TextAttribute extends Attribute {
    void setValue(CharSequence newValue);

    CharSequence getValue();

    /**
     * Java 1.4 type safe access to getValue
     * @return (Boolean) getValue()
     */
    CharSequence getText();

    /**
     * Java 1.4 type safe access to setValue
     * @param newValue
     */
    void setText(CharSequence newValue);
}
