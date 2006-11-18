package org.opengis.feature.type;

import java.util.Collection;
import java.util.Set;

import org.opengis.feature.Property;

/**
 * Represents a FeatureCollection
 * <p>
 * The following restrictions are used:
 * <ul>
 * <li>C is the Collection of Attributes for the FeatureCollection itself, these usually are derrived from the members (ie. "bounds")
 * <li>M is the Set of member FeatureTypes, in many cases this will be of size() == 1
 * </ul>
 * <p>
 * Features are actually associated with the collection, according to the guidelines for
 * AssociationType. You can use this information to describe in what manner the contents
 * are related.
 * </p>
 * 
 * @author Jody
 *
 * @param <C>
 * @param <M>
 */
public interface FeatureCollectionType<E extends Property,C extends Collection<E>, M extends FeatureType> 
	extends FeatureType<E,C> {
	
	/**
	 * FeatureTypes allowable as members of this set.
	 * <p>
	 * Please note that all the normal AttributeTypes defined by this FeatureCollection
	 * (such as bounds) are considered to be derivative from the member features
	 * </p>
	 * <p>
	 * Please note that the you can continue to use the isInline() to indicate any ordering
	 * constraints placed on memberFeatures for xPath.
	 * </p>
	 */
	Set<AssociationDescriptor<? extends AssociationType<M>>> getMembers();
}