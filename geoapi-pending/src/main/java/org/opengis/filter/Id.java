/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
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
 * <p>
 * You can check what kind of Identifiers are supported using:<pre><code>
 * idCapabilities.hasFID() == true;             // for FeatureId
 * idCapabilities.hasEID() == true;             // no idea ...
 * </code></pre>
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
