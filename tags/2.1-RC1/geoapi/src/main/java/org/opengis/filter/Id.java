package org.opengis.filter;

import java.util.Set;

import org.opengis.filter.identity.Identifier;

/**
 * A filter that passes only the Identifiers listed.
 * <p>
 * This application of this filter for Features is well established:
 * <ul>
 *   <li>FeatureId - used for GML2 Features</li>
 *   <li>GeometryId - used for GML3 Features and Geometry constructs</li>
 * </ul>
 * We also have the following identifiers:
 * <ul>
 *   <li>RecordId - from CSW-2</li>
 *   <li>ObjectId - from CSW-2</li>
 * </ul>
 *
 * @author Chris Dillard (SYS Technologies)
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface Id extends Filter {
    /**
     * Set of IDs representing the Identifiers used by this filter.
     */
    Set<Object> getIDs();

    /**
     * Returns the set of identifiers used by this filter.
     */
    Set<Identifier> getIdentifiers();
}
