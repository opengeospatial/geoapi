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
 * {@code true} if the first operand is disjoint from the second (in the sense defined
 * in the OGC Simple Features specification).
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema(URL="filter.xsd", element="Disjoint")
public interface Disjoint extends BinarySpatialOperator {
}
