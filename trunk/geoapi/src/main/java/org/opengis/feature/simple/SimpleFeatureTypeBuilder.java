package org.opengis.feature.simple;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureTypeBuilder;

//Java 1.4 imports
//import org.opengis.feature.type.ComplexType;

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
public interface SimpleFeatureTypeBuilder extends FeatureTypeBuilder<Attribute,List<Attribute>,SimpleFeatureType> {
	
	/**
	 * Returns 1.
	 */
	int getMinOccurs();
	
	/**
	 * Returns 1.
	 */
	int getMaxOccurs();
	
}