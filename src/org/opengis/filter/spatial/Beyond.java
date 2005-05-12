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
import org.opengis.annotation.XmlElement;


/**
 * Concrete {@linkplain DistanceBufferOperator distance buffer operator} that evaluates as
 * {@code true} when all of a feature's geometry lies beyond (i.e. is more distant) than
 * the given distance from this object's geometry.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
@XmlElement(name="Beyond")
public interface Beyond extends DistanceBufferOperator {
}
