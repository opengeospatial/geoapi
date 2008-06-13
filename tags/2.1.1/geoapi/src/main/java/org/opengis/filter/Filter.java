/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;
import org.opengis.feature.Feature;
import org.opengis.feature.type.FeatureType;  // For javadoc


/**
 * Fefines a constraint that can be checked against an instance of an Object (usually a Feature).
 * <p>
 * Often a filter is used to to define a set {@linkplain Feature feature} instances
 * that are to be operated upon. The operating set can be comprised of one or more
 * enumerated features or a set of features defined by specifying spatial and
 * non-spatial constraints on the geometric and scalar properties of a feature type.
 * <p>
 * Roughly speaking, a filter encodes the information present in the {@code WHERE}
 * clause of a SQL statement.  There are various subclasses of this class that
 * implement many types of filters, such as simple property comparisons or spatial
 * queries.
 * <p>
 * The second use of Filter focuses on expressing constraints (or Facets). This use
 * places restrictions on the allowable and is captured as part of schema information
 * (@linkplain FeatureType). This is similar to the XML concept of "facets".
 * </p>
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Filter")
public interface Filter {
    /**
     * Placeholder Filter that evaulates to <code>true</code>.
     * <p>
     * Filtering a set with Filter.INCLUDE results in the origional set.
     * </p>
     */
    IncludeFilter INCLUDE = new IncludeFilter();

    /**Placeholder Filter that evaulates to <code>false</code>.
     * <p>
     * Filtering a set with Filter.EXCLUDE results in the Empty Set.
     * */
    ExcludeFilter EXCLUDE = new ExcludeFilter();

    /**
     * Give an object, this method determines if the test(s) represented by this filter object
     * are passed.
     * <p>
     * This ability is used to allow Queries against both Features and and non spatial data (such as Record) and
     * to express constraints on permissable data values.
     * </p>
     * @param object
     * @return <code>true</true> if the test(s) are passed for the provided object
     */
    boolean evaluate(Object object);

    /**
     * Accepts a visitor.
     * <p>
     * Implementations of all subinterfaces must have with a
     * method whose content is the following:
     * <pre>return visitor.{@linkplain FilterVisitor#visit visit}(this, extraData);</pre>
     * </p>
     */
    @Extension
    Object accept(FilterVisitor visitor, Object extraData);
}
