package org.opengis.feature.simple;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;

/**
 * Attribute containing a boolean value: Attribute<T extends AttributeType<Boolean>>
 * <p>
 * While we can indicate this with generics, it is helpful to have a named
 * class for this for those working against java 1.4 interfaces.
 * </p>
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface BooleanAttribute<T extends AttributeType<Boolean>>
	extends Attribute<Boolean,T> {

}
