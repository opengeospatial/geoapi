package org.opengis.feature.simple;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;

/**
 * Attribute containing a text value.
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface TextAttribute<T extends AttributeType<CharSequence>> 
	extends Attribute<CharSequence,T> {

}
