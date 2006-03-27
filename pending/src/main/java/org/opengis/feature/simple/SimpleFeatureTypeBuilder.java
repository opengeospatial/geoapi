package org.opengis.feature.simple;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureTypeBuilder;

/**
 * A builder for simple feature types. 
 * <p>
 * This interface adds no more information to the creation of the type but 
 * implmentations should restrict calls such that content of the type forms
 * a valid SimpleFeatureType. For instance, ensuring that no contained types
 * are instances of ComplexType.
 * </p>
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 * @param <B> The binding for the returned type. 
 */
public interface SimpleFeatureTypeBuilder<L extends List<Attribute>, T extends SimpleFeatureType<L>>
	extends FeatureTypeBuilder<L,T> {
	
	/**
	 * Imposes the following restrictions:
	 * <ul>
	 *  <li><code>type</code> paramter to a non-complex type.
	 *  <li><code>minOccurs</code> == 1
	 *  <li><code>maxOccurs</code> == 1 
	 * </ul>
	 * @throws IllegalArgumentException In the event that any of the above 
	 * restrictions does not hold.
	 */
	void add(String name, AttributeType type, int minOccurs, int maxOccurs, boolean isNillable);
	
	/**
	 * Returns null.
	 */
	<E extends T> E getSuper();
}