package org.opengis.feature.simple;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;
import org.opengis.spatialschema.geometry.BoundingBox;

/**
 * Attribute containing a bounding box value: 
 * 	Attribute<T extends AttributeType<BoundingBox>>
 * <p>
 * While we can indicate this with generics, it is helpful to have a named
 * class for this for those working against java 1.4 interfaces.
 * </p>
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface BoundingBoxAttribute<T extends AttributeType<BoundingBox>> 
	extends Attribute<BoundingBox,T> {
	
	
}
