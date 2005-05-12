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

// OpenGIS direct dependencies
import org.opengis.filter.Filter;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Abstract base class for operators that perform a spatial comparison on
 * geometric attributes of a feature.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
@XmlElement(name="SpatialOpsType")
public interface SpatialOperator extends Filter {
}
