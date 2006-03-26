package org.opengis.feature.simple;

import org.opengis.feature.FeatureCollection;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureType;

/**
 * A FeatureCollection which placed the following contraints on its
 * contents.
 * <ol>
 * 	<li>It contains features implementing a single feature type.
 * 	<li>It itself, contains no attributes.
 *  </ol>
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface SimpleFeatureCollection extends FeatureCollection {

	/**
	 * Restricted to return a SimpleFeatureCollectionType.
	 */
	SimpleFeatureCollectionType getType();
	
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
