/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
public interface BBOX extends BinarySpatialOperator {
	/** Operator name used to check FilterCapabilities */
	public static String NAME = "BBOX";

    /**
     * Name of the geometric property that will be used in this spatial operator.
     * <p>
     * This may be null if the default spatial property is to be used.
     * @deprecated Please check getExpression1(), if it is a PropertyName
     */
    @XmlElement("PropertyName")
    String getPropertyName();

    /**
     * Returns the spatial reference system in which the bounding box
     * coordinates contained by this object should be interpreted.
     * <p>
     * This string must take one of two forms: either
     * <ul>
     * <li>"EPSG:xxxxx" where "xxxxx" is a valid EPSG coordinate system code;
     * <li>OGC URI format
     * <li>or an OGC well-known-text representation of a coordinate system as
     *     defined in the OGC Simple Features for SQL specification.
     * </ul>
     * @deprecated please use getExpression2(), if it is a literal Envelope.getCoordinateReferenceSystem()
     */
    String getSRS();

    /**
     * Assuming getExpression2() is a literal bounding box access
     * the minimum value for the first coordinate.
     *
     * @deprecated please use getExpression2(), to check for a literal Envelope.getMinimum(0)
     */
    double getMinX();

    /**
     * Assuming getExpression2() is a literal bounding box access
     * the minimum value for the second ordinate.
     * @deprecated please use getExpression2(), to check for a literal Envelope.getMinimum(1)
     */
    double getMinY();

    /**
     * Assuming getExpression2() is a literal bounding box access
     * the maximum value for the first ordinate.
     *
     * @deprecated please use getExpression2(), to check for a literal Envelope.getMaximum(0)
     */
    double getMaxX();

    /**
     * Assuming getExpression2() is a literal bounding box access
     * the maximum value for the second coordinate.
     * @deprecated please use getExpression2(), to check for a literal Envelope.getMaximum(1)
     */
    double getMaxY();
}
