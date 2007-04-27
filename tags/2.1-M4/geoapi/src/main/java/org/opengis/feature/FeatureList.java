package org.opengis.feature;

import java.util.Collection;
import java.util.List;

import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;
import org.opengis.filter.Filter;

/**
 * An ordered List of Features.
 * <p>
 * A FeatureList is usually retrived from a FeatureCollection with the
 * subCollection( Filter ) operation using the Filter 1.1 construct SortBy.
 * </p>
 * <p>
 * You may check to see if the result of subCollection( Filter ) is
 * a FeatureList using an instanceof check. When not using an explicit
 * sortBy parameter the order will be based on an internal index, often
 * the one used to define the feature ID.
 * </p>
 * @author Jody Garnett, Refractions Research, Inc.
 */
public interface FeatureList<E extends Property,C extends Collection<E>, M extends FeatureType, T extends FeatureCollectionType<E,C,M>, F extends Feature> 
	extends FeatureCollection<E,C,M,T,F>, List<F> {
    
	/**
	 * Similar to subCollection, explicitly constructs a ordered List.
	 * <p>
	 * The list will be ordered:
	 * <ul>
	 * <li>As indicated using Filter 1.1 sortBy
	 * <li>occuring to their appearance in this FeatureList
	 * </ul>
	 * </p>
	 * @param filter
	 * @return FeatureList based on features selected by filter
	 */
    FeatureList subList( Filter filter );
}