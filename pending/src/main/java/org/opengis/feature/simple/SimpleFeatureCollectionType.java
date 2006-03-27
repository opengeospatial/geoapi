package org.opengis.feature.simple;

import java.util.Collection;
import java.util.Set;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;

/**
 * Extension of FeatureCollectionType for a simple collection.
 * <br>
 * <p>
 * Maintains the following properties:
 * <ul>
 * 	<li>The type of the collection is empty 
 *  <li>The set of member types contains a single feature type 
 *  <li>The type is not abstract
 *  <li>The type cannot inherit from a parent type.
 *  /ul>
 * <br>
 * With code the amounts to the following assertions being held:
 * <pre>
 * 	<code>getType().getAttribute().isEmpty()</code>
 * 	<code>getMemberTypes().size() == 1</code>
 * </pre>
 * </p>
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface SimpleFeatureCollectionType<M extends FeatureType> extends FeatureCollectionType<Collection<Attribute>,M> {

	/**
	 * Returns the single feature of allowed members of the collection.
	 */
	M getMemberType();
	/**
	 * Returned set contains a single member.
	 */
	Set<M> getMemberTypes();
	
	/**
	 * Returns false.
	 */
	boolean isAbstract();
	
	/**
	 * Returns null.
	 */
	AttributeType getSuper();
}
