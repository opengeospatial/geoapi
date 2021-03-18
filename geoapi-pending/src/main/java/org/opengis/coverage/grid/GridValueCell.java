/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage.grid;

import java.util.Set;
import org.opengis.coverage.ValueObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basis for interpolating within a {@linkplain ContinuousQuadrilateralGridCoverage
 * continuous quadrilateral grid coverage}. A {@code GridValueCell} is a collection
 * of {@linkplain GridPointValuePair grid point value pairs} with a geometric structure
 * defined by a {@linkplain GridCell grid cell}.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_GridValueCell", specification=ISO_19123)
public interface GridValueCell extends ValueObject {
    /**
     * Returns the {@linkplain GridCell grid cell} that defines the structure of the
     * {@linkplain GridPointValuePair grid point value pairs} that support the interpolation
     * of a feature attribute value at a {@linkplain org.opengis.geometry.DirectPosition direct position} within the
     * {@linkplain GridCell grid cell}.
     *
     * @departure constraint
     *   ISO defines this method as a specialization of <code>getGeometry()</code>. We
     *   can not reflect this association in Java because of incompatible return type.
     *
     * @return the structure of grid points.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    GridCell getGridCell();

    /**
     * Returns the set of <var>grid point</var>-<var>value</var> pairs at the corners of this
     * {@code GridValueCell}.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPointValuePair> getControlValues();
}
