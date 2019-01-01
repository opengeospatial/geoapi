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


/**
 * The matchAction attribute can be used to specify how the comparison predicate shall be evaluated for a
 * collection of values (e.g. in XML, properties having maxOccurs &gt; 1) and not including some additional context
 * to identify a specific value from the collection to be tested. Possible values for the attribute are: All, Any or
 * One. A value of All means that all values in the collection shall satisfy the predicate. A value of Any means
 * that any of the value in the collection can satisfy the predicate. Finally, a value of One means that only one of
 * the values in the collection shall satisfy the predicate.
 *
 * <p>Null value should be interpreted as ANY.</p>
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=39968">Implementation specification 2.0</A>
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 3.1
 */
public enum MatchAction {
    ALL,
    ANY,
    ONE
}
