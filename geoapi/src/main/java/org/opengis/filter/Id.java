package org.opengis.filter;

import java.util.Set;

/**
 * Instances of this interface represent a filter that passes only the IDs given to this object.
 * <p>
 * This application of this filter for Features is well understood. For other identifiable Objects
 * such as Geometry or Records we may have to provide more thought.
 * </p>
 * 
 * @author Chris Dillard (SYS Technologies)
 * @author Justin Deoliveira, The Open Planning Project
 *
 */
public interface Id extends Filter {

	/**
     * Returns a {@linkplain Set} containing the IDs of that will pass this filter.
     */
	Set<Identifier> getIDs();
}
