package org.opengis.feature.simple;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;


/**
 * Attribute containing a numeric value: <N extends Number,T extends AttributeType<N>>
 * 
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface NumericAttribute extends Attribute
{
	public void setValue(Number newValue);
	public Number getValue();
}
