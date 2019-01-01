/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage.grid.quadrilateral;


/**
 * This is the primary method of constructing {@linkplain GridCoordinates}.
 * Each GridCoordinatesFactory is associated with a single backing data type
 * (e.g., byte, short, or integer), and is capable of manufacturing
 * {@linkplain GridCoordinates} of a specified dimensionality.
 * Specialized methods are provided to create {@linkplain GridCoordinates} objects
 * which are initialized to the supplied two, three or four-dimensional values.
 *
 * @author  Alexander Petkov
 */
public interface GridCoordinatesFactory {
    /**
     * Allows the user to specify the dimensionality of the desired {@linkplain GridCoordinates} object, but does not specify the initial values.
     * This will create an uninitialized object of the desired dimensionality if this factory is capable of it.
     */
    GridCoordinates createCoordinates(int dimensions);

    GridCoordinates createCoordinates(int x0, int x1);

    GridCoordinates createCoordinates(int x0, int x1, int x2);

    GridCoordinates createCoordinates(int x0, int x1, int x2, int x3);
}
