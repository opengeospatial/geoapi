package org.opengis.feature.simple;

import java.util.Collection;
import java.util.Set;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AssociationType;
import org.opengis.feature.type.FeatureCollectionType;

/**
 * Extension of FeatureCollectionType for a simple collection.
 * <br>
 * <p>
 * Maintains the following properties:
 * <ul>
 * 	<li>Contains a single member feature type.
 * 	<li>Contains no attributes of its own
 * 	<li>Has no super type
 * </ul>
 * <br>
 * With code the amounts to the following assertions being held:
 * <pre>
 * 	<code>getMemberTypes().size() == 1</code>
 * 	<code>getType().getAttribute().isEmpty()</code>
 * 	<code>getSuper() == null</code>
 * </pre>
 * </p>
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface SimpleFeatureCollectionType<M extends SimpleFeatureType> 
	extends FeatureCollectionType<Attribute,Collection<Attribute>,M> {

	/**
	 * Returns the single feature of allowed members of the collection.
	 */
	M getMemberType();
	
	/**
	 * Returned set contains a single member.
	 */
	Set<AssociationDescriptor<? extends AssociationType<M>>> getMembers();
	//Set<M> getMemberTypes();
	
}
