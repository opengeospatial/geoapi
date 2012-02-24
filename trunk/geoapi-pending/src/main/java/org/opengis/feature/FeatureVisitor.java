/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2012 Open Geospatial Consortium, Inc.
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
package org.opengis.feature;

/**
 * FeatureVisitor interface to allow for container optimised traversal.
 * <p>
 * The iterator construct from the Collections api is well understood and
 * loved, but breaks down for working with large GIS data volumes. By using a
 * visitor we allow the implementor of a Feature Collection to make use of
 * additional resources (such as multiple processors or tiled data)
 * concurrently.
 * </p>
 * This interface is most often used for calculations and data
 * transformations and an implementations may intercept known visitors
 * (such as "bounds" or reprojection) and engage an alternate work flow.
 * </p>
 * @author Cory Horner (Refractions Research, Inc)
 */
public interface FeatureVisitor {
    /**
     * Visit the provided feature.
     * <p>
     * Please consult the documentation for the FeatureCollection you are visiting
     * to learn more - the provided feature may be invalid, or read only.
     * @param feature
     */
    void visit(Feature feature);
}
