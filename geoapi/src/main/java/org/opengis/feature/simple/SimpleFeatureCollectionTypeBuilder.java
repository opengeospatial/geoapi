package org.opengis.feature.simple;

import java.util.Collection;
import java.util.Set;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureCollectionTypeBuilder;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.Name;

/**
 * Builder for building SimpleFeatureCollectionType's.
 * <p>
 * This interface adds no more information to the creation of the type but 
 * implementations should ensure that type content forms a valid 
 * SimpleFeatureCollectionType as per the interface contract. For instance
 * ensuring that is contains exactly one member FeatureType. 
 * </p>
 * 
 * @author Justin Deoliveira, The Open Planning Project. jdeolive@openplans.org
 *
 */
public interface SimpleFeatureCollectionTypeBuilder extends
		FeatureCollectionTypeBuilder<Attribute,Collection<Attribute>,FeatureType,SimpleFeatureCollectionType<FeatureType>> {

	/**
	 * SimpleFeatureCollectionType does not support attributes.
	 * 
	 * @throws UnsupportedOperationException
	 */
	void attribute(Name name, AttributeType type);
	void attribute(Name name, Class binding);
	void attribute(String name, AttributeType type);
	void attribute(String name, Class binding);
	void attribute(String name, String namespaceURI, AttributeType type);
	void attribute(String name, String namespaceURI, Class binding);
	
	/**
	 * Sets the single member type to be associated to the simple feature 
	 * collection.
	 */
	void setMemberType(FeatureType memberType);
	
}
