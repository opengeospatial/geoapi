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
package org.opengis.filter.spatial;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * {@linkplain SpatialOperator Spatial operator} that evaluates to {@code true} when the bounding
 * box of the feature's geometry overlaps the bounding box provided in this object's properties.
 * An implementation may choose to throw an exception if one attempts to test
 * features that are in a different SRS than the SRS contained here.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("BBOX")
public interface BBOX extends SpatialOperator {
    /**
     * Returns the name of the geometric property that will be used in this
     * spatial operator.  This may be null if the default spatial property is
     * to be used.
     */
    @XmlElement("PropertyName")
    String getPropertyName();

    /**
     * Returns the spatial reference system in which the bounding box
     * coordinates contained by this object should be interpreted.  This string
     * must take one of two forms: either "EPSG:xxxxx" where "xxxxx" is a valid
     * EPSG coordinate system code, or an OGC well-known-text representation of
     * a coordinate system as defined in the OGC Simple Features for SQL
     * specification.
     */
    String getSRS();

    /**
     * Returns the minimum value for the first coordinate.
     */
    double getMinX();

    /**
     * Returns the minimum value for the second coordinate.
     */
    double getMinY();

    /**
     * Returns the maximum value for the first coordinate.
     */
    double getMaxX();

    /**
     * Returns the maximum value for the second coordinate.
     */
    double getMaxY();
}
