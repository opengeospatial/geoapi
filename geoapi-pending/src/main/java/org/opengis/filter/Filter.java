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

import org.opengis.annotation.XmlElement;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;


/**
 * Defines a constraint that can be checked against an instance of an object (Usually a Feature).
 *
 * <p>This is an abstract super type of the Filters defined by the Filter specification; you are not
 * free to define your own filters. For extensibility please explore the definition of
 * your own {@code Function}.</p>
 *
 * <p>Often a filter is used to to define a set {@linkplain Feature feature} instances
 * that are to be operated upon. The operating set can be comprised of one or more
 * enumerated features or a set of features defined by specifying spatial and
 * non-spatial constraints on the geometric and scalar properties of a feature type.</p>
 *
 * <p>Roughly speaking, a filter encodes the information present in the {@code WHERE}
 * clause of a SQL statement.  There are various subclasses of this class that
 * implement many types of filters, such as simple property comparisons or spatial
 * queries.</p>
 *
 * <p>The second use of Filter focuses on expressing constraints (or Facets). This use
 * places restrictions on the allowable and is captured as part of schema information
 * {@linkplain FeatureType}. This is similar to the XML concept of "facets".</p>
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Filter")
public interface Filter {
    /**
     * Placeholder Filter that evaluates to {@code true}.
     *
     * Filtering a set with {@code Filter.INCLUDE} results in the origional set.
     */
    IncludeFilter INCLUDE = new IncludeFilter();

    /**
     * Placeholder Filter that evaluates to {@code false}.
     *
     * Filtering a set with {@code Filter.EXCLUDE} results in the empty Set.
     */
    ExcludeFilter EXCLUDE = new ExcludeFilter();

    /**
     * Give an object, this method determines if the test(s) represented by this filter object
     * are passed.
     *
     * This ability is used to allow Queries against both Features and and non spatial data (such as Record) and
     * to express constraints on permissable data values.
     *
     * @return {@code true} if the test(s) are passed for the provided object
     */
    boolean evaluate(Object object);

    /**
     * Accepts a visitor.
     *
     * <p>Implementations of all subinterfaces must have with a
     * method whose content is the following:</p>
     * <pre>return visitor.{@linkplain FilterVisitor#visit visit}(this, extraData);</pre>
     */
    Object accept(FilterVisitor visitor, Object extraData);
}
