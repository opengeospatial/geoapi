package org.opengis.feature.simple;

import java.util.Collection;
import java.util.Set;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureCollectionTypeBuilder;
import org.opengis.feature.type.FeatureType;

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
		FeatureCollectionTypeBuilder<Collection<Attribute>,FeatureType,SimpleFeatureCollectionType<FeatureType>> {

	/**
	 * SimpleFeatureCollectionType does not support attributes.
	 * 
	 * @throws UnsupportedOperationException
	 */
	void add(String name, AttributeType type, int minOccurs, int maxOccurs, boolean isNillable);

	/**
	 * Ensures that the total number of added types == 1.
	 * 
	 * @throws IllegalArgumentException If this call leaves in the builder in 
	 * a state such that {@link #getMemberTypes()}.size() > 1
	 */
	void addMemberType(FeatureType memberType);
	
	/**
	 * Ensures that the supplied set is of sizs 1.
	 * 
	 * @throws IllegalArgumentException If <code>memberTypes.size()</code> > 1.
	 */
	void setMemberTypes(Set<FeatureType> memeberTypes);
	
}
