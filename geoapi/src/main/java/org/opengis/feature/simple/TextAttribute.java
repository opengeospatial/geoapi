package org.opengis.feature.simple;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;

/**
 * Attribute containing a text value.
 * 
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface TextAttribute extends Attribute {
	public void setValue(CharSequence newValue);
	public CharSequence getValue();
}
