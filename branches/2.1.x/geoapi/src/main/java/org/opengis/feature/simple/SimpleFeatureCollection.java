package org.opengis.feature.simple;

import java.util.Collection;
import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.FeatureCollection;
import org.opengis.feature.type.FeatureType;

//Java 1.4 imports
//import org.opengis.feature.type.AttributeType;
/**
 * A FeatureCollection which placed the following contraints on its
 * contents.
 * <ol>
 *   <li>It contains features implementing a single feature type.</li>
 *   <li>It itself, contains no attributes.</li>
 * </ol>
 *
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface SimpleFeatureCollection extends FeatureCollection {
    /**
     * Restricted to SimpleFeatureCollectionType.
     * @return SimpleFeatureCollectionType for this collection
     */
    SimpleFeatureCollectionType getType();

    /**
     *
     * This is the same as getType(), this method is used for Java 1.4 applications.
     * @return SimpleFeatureCollectionType for this collection
     */
    SimpleFeatureCollectionType getFeatureCollectionType();

    /**
     * Returns the single feature of allowed members of the collection.
     * <p>
     * This method is simply convenience and is equivalent to :
     * <pre>
     * getType().getMemberType()
     * </pre>
     * </p>
     */
    SimpleFeatureType getMemberType();
}
