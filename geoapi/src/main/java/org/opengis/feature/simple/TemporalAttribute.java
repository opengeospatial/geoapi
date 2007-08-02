package org.opengis.feature.simple;

import java.util.Date;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;

/**
 * Attribute containing a date value: Attribute<T extends AttributeType<Date>>
 * 
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface TemporalAttribute extends Attribute {
	public void setValue(Date newValue);
	public Date getValue();
}
