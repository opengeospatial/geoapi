/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// OpenGIS direct dependencies
import org.opengis.feature.Feature;

// Annotations
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;


/**
 * The abstract base class for filters. A filter is used to define a set of
 * {@linkplain Feature feature} instances that are to be operated upon. The
 * operating set can be comprised of one or more enumerated features or a set
 * of features defined by specifying spatial and non-spatial constraints on the
 * geometric and scalar properties of a feature type.
 * <p>
 * Roughly speaking, a filter encodes the information present in the {@code WHERE}
 * clause of a SQL statement.  There are various subclasses of this class that
 * implement many types of filters, such as simple property comparisons or spatial
 * queries.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Filter")
public interface Filter {
    /**
     * Given a feature, this method determines whether the feature passes the
     * test(s) represented by this filter object.
     */
    @Extension
    boolean evaluate(Feature feature);

    /**
     * Accepts a visitor.  Implementations of all subinterfaces must have with a
     * method whose content is the following:
     * <pre>return visitor.{@linkplain FilterVisitor#visit visit}(this, extraData);</pre>
     */
    @Extension
    Object accept(FilterVisitor visitor, Object extraData);
}
