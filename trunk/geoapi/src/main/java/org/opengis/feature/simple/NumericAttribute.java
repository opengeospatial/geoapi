package org.opengis.feature.simple;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;

/**
 * Attribute containing a numeric value: <N extends Number,T extends AttributeType<N>>
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface NumericAttribute<N extends Number,T extends AttributeType<N>> 
	extends Attribute<N,T> {

}