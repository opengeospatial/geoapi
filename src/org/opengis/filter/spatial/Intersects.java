/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter.spatial;

// Annotations
import org.opengis.annotation.XmlSchema;


/**
 * Concrete {@linkplain BinarySpatialOperator binary spatial operator} that evaluates to
 * {@code true} if the two geometric operands intersect.  This is the opposite of the
 * {@link Disjoint} operator.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema(URL="filter.xsd", element="Intersects")
public interface Intersects extends BinarySpatialOperator {
}
