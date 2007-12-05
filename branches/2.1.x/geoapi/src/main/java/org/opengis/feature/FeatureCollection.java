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

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.opengis.annotation.XmlElement;
import org.opengis.feature.type.FeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;
import org.opengis.geometry.BoundingBox;
import org.opengis.util.ProgressListener;
//import org.opengis.feature.type.AttributeType;
/**
 * Access to a collection of features.
 * <p>
 * Many of the values available for this FeatureCollection (such as getBounds())
 * are to be considered a derived quantities based on the contents (or members)
 * of this collection.
 *
 * <h4>Resource Access</h4>
 *
 * FeatureCollection implementations make use of resources to access sometimes
 * vast quantities of information. The preferred way of accessing the contents
 * of a feature collection is using a FeatureVisitor.
 * <pre><code>BoundingBox box = geometryFactory.createBoundingBox();
 * features.accepts( new FeatureVisitor(){
 *     public void visit(Feature feature){
 *         box.include( feature.getBounds() );
 *     }
 * }, progress );
 * </code></pre>
 * This technique is <b>safe</b> and ensures that and memory cache or database connection
 * associated with data access will be returned.
 *
 * <h4>Use of Iterator with try finally</h4>
 * If you are using a for loop please use a try/finally statement to close any iterators used.
 * <pre><code>BoundingBox box = geometryFactory.createBoundingBox();
 * Iterator<Feature> iterator = features.iterator();
 * try {
 *    while( iterator.hasNext() ){
 *        Feature feature = iterator.next();
 *        box.include( feature.getBounds() );
 *    }
 * }
 * finally {
 *    features.close( iterator );
 * }
 * </code></pre>
 * Often database connections and memory caches are maintained for the
 * life of the iterator.
 *
 * <h4>Java 5</h4>
 * This class no longer implements Collection<Feature> as Java 5 syntactic sugar has
 * made it too easy to avoid closing your FeatureIterators.
 * <p>
 * <h4>Geographic Markup Language</h4>
 * In GML a <code>AbstractFeatureCollection</code> is defined as both a feature and as
 * an associations to contained "members". This can be represented within the abilities
 * of our general feature model.
 * <ul>
 * <li>members: is an Association with mutiplicity 0:*, the reference type of this association
 * is the type of features contained in the collection, the association captures the reason the
 * members are in the collection.
 * </ul>
 * If you wish you can model AbstractFeatureCollection as a normal Feature as outlined above,
 * you may optionally implement this FeatureCollection interface as follows:
 * <ul>
 * <li>getMemberTypes(): the reference type for your members association.
 * <li>iterator() and visit(): navigate the members association visiting each feature in turn
 * </ul>
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
public interface FeatureCollection {
    /**
     * The bounds of the feature collection contents.
     * <p>
     * This value is derived from the members of the feature collection
     * and may be O(N).
     * </p>
     * <p>
     * In the case that the collection is empty, or the members have
     * no geometric attributes this method an empty bounds,
     * ie, <code>bounds.isEmpty() == true</code>.
     * This method should never return <code>null</code>.
     * </p>
     * <p>
     * The coordinate reference system of the returned bounds is derived from
     * the geometric attributes which were used to compute the bounds. In the
     * event that the feature contains multiple geometric attributes which have
     * different crs's, the one defined by {@link #getDefaultGeometry()} should
     * take precedence and the others should be reprojected accordingly.
     * </p>
     * @return the feature bounds, possibly empty.
     */
    BoundingBox getBounds();

    /**
     * Access contents of this collection, you are required to close iterators
     * after use.
     * <p>
     * Two points to using iterator successfully:
     * <ul>
     * <li>FeatureCollections are often backed by resource use and the
     * iterators must be passed to FeatureCollection.close( Iterator ) after
     * use. This is a requirement over an beyond that imposed by the Java
     * Collections API, it does represent a common solution seen in other
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
    Iterator<Feature> iterator();

    /**
     * You are required to close iterators after use.
     * <p>
     * Many FeatureCollections are backed by IO resources that need
     * to be returned to the operating system after use.
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
    FeatureCollection sort( SortBy order  );

    /**
     * The first member type.
     *
     * @return getMemberTypes().iterator()
     */
    FeatureType getMemberType();

    /**
     * Convenience method for obtaining the collection of member types in
     * which members of this collection may implement.
     * <br>
     * <p>
     * Equivalent to calling the following:
     * <pre>
     *  <code>
     *      getType().getMemberTypes()
     *  </code>
     * </pre>
     *  </p>
     *
     */
    Collection<FeatureType> getMemberTypes();

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
