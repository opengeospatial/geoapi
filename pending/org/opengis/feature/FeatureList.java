package org.opengis.feature;

import java.util.List;

import org.opengis.filter.Filter;

/**
 * An ordered List of Features. 
 * <p>
 * A FeatureList is usually retrived from a FeatureCollection with the
 * subCollection( Filter ) operation. How you ask - make use of Filter
 * 1.1 sortBy.
 * </p>
 * <p>
 * You may check to see if the result of subCollection( Filter ) is
 * a FeatureList using an instanceof check. This often the case,
 * when using not using sortBy the order is usually based on FID.
 * </p>
 * @author Jody Garnett, Refractions Research, Inc.
 */
public interface FeatureList extends FeatureCollection, List<Feature> {
    
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