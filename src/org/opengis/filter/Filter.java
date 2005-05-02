/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// OpenGIS direct dependencies
import org.opengis.feature.Feature;

// Annotations
import org.opengis.annotation.XmlSchema;


/**
 * The abstract base class for OGC-style filters.  Roughly speaking, a
 * filter encodes the information present in the {@code WHERE} clause of a SQL
 * statement.  There are various subclasses of this class that implement many
 * types of filters, such as simple property comparisons or spatial queries.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Filter encoding implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema(URL="filter.xsd", element="Filter")
public interface Filter {
    /**
     * Given a feature, this method determines whether the feature passes the
     * test(s) represented by this filter object.
     */
    boolean evaluate(Feature feature);

    /**
     * Accepts a visitor.  Implementations of all subinterfaces must have with a
     * method whose content is the following:
     * <pre>return visitor.{@linkplain FilterVisitor#visit} visit(this, extraData);</pre>
     */
    Object accept(FilterVisitor visitor, Object extraData);
}
