package org.opengis.feature.simple;

import java.util.Collection;

import org.opengis.feature.Attribute;
import org.opengis.feature.FeatureCollection;
import org.opengis.feature.type.FeatureType;

//Java 1.4 imports
//import org.opengis.feature.type.AttributeType;

/**
 * A FeatureCollection which placed the following contraints on its
 * contents.
 * <ol>
 * 	<li>It contains features implementing a single feature type.
 * 	<li>It itself, contains no attributes.
 *  </ol>
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 */
public interface SimpleFeatureCollection<M extends SimpleFeatureType, T extends SimpleFeatureCollectionType<M>> 
	extends FeatureCollection<Attribute,Collection<Attribute>,M,T,SimpleFeature> {

	/**
	 * Restricted to return a SimpleFeatureCollectionType.
	 */
	T getType();
	
	/**
	 * Returns the single feature of allowed members of the collection.
	 * <br>
	 * <p>
	 * This method is simply convenience and is equivalent to :
	 * <pre>
	 * 	<code>getType().getMemberType()</code>
	 * </pre>
	 * </p>
	 */
	FeatureType memberType();
}
