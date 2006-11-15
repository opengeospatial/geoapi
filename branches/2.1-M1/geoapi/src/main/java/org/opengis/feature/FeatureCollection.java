/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2006 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

import java.util.Collection;
import java.util.Iterator;

//import org.opengis.feature.type.AttributeType;
import org.opengis.annotation.XmlElement;
import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;
import org.opengis.util.ProgressListener;

/**
 * Represents a FeatureCollection (explicitly a Collection<Feature>.
 * <p>
 * Note the "attributes" available in a FeatureCollection are to be
 * considered a derrived quality based on the contents (or members) of the
 * collection. An "empty" FeatureCollection should not exist.
 * </p>
 * Implementations and client code should adhere to the rules set forth
 * by {@link java.util.Collection}. That is, some methods are optional
 * to implement, and may throw an {@link UnsupportedOperationException}.
 * </p>
 * 
 * @author Ian Turton (CCG)
 * @author Rob Hranac (VFNY)
 * @author Ian Schneider (USDA-ARS)
 * @author Jody Garnett (Refracitons Research)
 * 
 * @version GeoAPI 2.1
 * @since GeoAPI 2.0
 * @see java.util.Collection
 */
@XmlElement("FeatureCollection")
public interface FeatureCollection<E extends Property,C extends Collection<E>, M extends FeatureType, T extends FeatureCollectionType<E,C,M>,F extends Feature> 
	extends Feature<E,C,T>,
	Collection<F> {
	
	/**
	 * Restricted to return a FeatureCollectionType.
	 */
    T getType();

	
    /**
	 * Access contents of this collection, you are required to close iterators
	 * after use.
	 * <p>
	 * Two points to using iterator successfully:
	 * <ul>
	 * <li>FeatureCollections are often backed by resource use and the
	 * iterators must be passed to FeatureCollection.close( Iterator ) after
	 * use. This is a requirement over an beyond that imposed by the Java
	 * Collections API, it does represent a commmon solution seen in other
	 * Java libraries.
	 * <lil>The Feature returned by <code>next()</code> is only "valid" until the
	 * call to <code>next()</code> is made.
	 * </ul>
	 * <p>
	 * <h3>Common Problems</h4>
	 * 
	 * The first common mistake is trying to store Features obtained from an
	 * iterator. This is a mistake as you may get the same instance returned
	 * each time with different content and fid). The solution is to store the
	 * IDs in a Set and and use a Feature ID Filter to produce the needed collection.
	 * <p>
	 * Please note you will be limited to a single worker thread to access
	 * an iterator, this is a consequence of a feature only being valid until
	 * next is called. For an alternative please use visitor. Conversly plit you
	 * request into sections with one for each worker thread.
	 * <p>
	 * <h3>XPath Conventions</h4>
	 * Conventions for XPath Access: the contents of a GML collection are
	 * represented by either featureMember or featureMembers. When interpretting
	 * an XPath expression, you should consider this function to visit both
	 * elements for you.
	 * </p>
	 * <p>
	 * XPath Mapping:
	 * <ul>
	 * <li>Preferred:<code>featureMember/*</code>
	 * <li>Legal:<code>featureMembers</code>
	 * </ul>
     * @return Iterator over the contents of this feature collection.
     */
    Iterator<F> iterator();
    
    /**
     * You are required to close iterators after use.
     * <p>
     * Many FeatureCollections are backed by IO resources that need
     * to be returned to the opperating system after use.
     * </p>
     */
    void close( Iterator<Feature> iterator );
    

    
    /**
     * FeatureCollection "view" indicated by provided filter.
     * <p>
     * The contents of the returned FeatureCollection are determined by
     * applying the provider Fitler to the entire contents of this FeatureCollection.
     * The result is "live" aqnd modifications will be shared.
     * <p>
     * This method is used cut down on the number of filter based methods
     * required for a useful FeatureCollection construct. The FeatureCollections
     * returned really should be considered as a temporary "view" used to
     * control the range of a removeAll, or modify operation.
     * <p>
     * Example Use:
     * <pre><code>
     * collection.subCollection( filter ).removeAll();
     * </code></pre>
     * The above recommended use is agreement with the Collections API precident of
     * List.subList( start, end ).
     * <p>
     * The results of subCollection:
     * <ul>
     * <li>are to be considered unordered
     * <li>may be an ordered FeatureList if requested when sortBy is indicated
     * </ul>
     * </p>
     * @see FeatureList
     * @param filter
     * @return FeatureCollection identified as subset.
     */
    FeatureCollection subCollection( Filter filter );
    
    /**
     * A sorted "view" of this collection in the order indicated.
     *  
     * @param order
     * @return FeatureList in requested order
     */
    FeatureList sort( SortBy order  );
    
    /**
     * Convenience method for obtaining the collection of memeber types in 
     * which members of this collection may implement.
     * <br>
     * <p>
     * Equivalent to calling the following:
     * <pre>
     * 	<code>
     * 		getType().getMemberTypes()
     * 	</code>
     * </pre>
     *  </p>
     * 
     */
    Collection<FeatureType> memberTypes();
    
    /**
     * Will visit the contents of the feature collection.
     * <p>
     * 
     * </p>
     * @see FeatureVisitor For more comparison with iterator based access
     * @param visitor
     * @throws IOException 
     */
    void accepts( FeatureVisitor visitor, ProgressListener progress );
       
}