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
package org.opengis.filter.spatial;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Concrete {@linkplain BinarySpatialOperator binary spatial operator} that evaluates to
 * {@code true} if the the first geometric operand contains the second.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Contains")
public interface Contains extends BinarySpatialOperator {
}
