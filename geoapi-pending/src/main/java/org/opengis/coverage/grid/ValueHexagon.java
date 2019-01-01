/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2019 Open Geospatial Consortium, Inc.
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
import org.opengis.coverage.DomainObject;
import org.opengis.geometry.Geometry;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basis for interpolating within a {@linkplain HexagonalGridCoverage continuous hexagonal grid
 * coverage}. A {@code ValueHexagon} is a collection of {@linkplain GridPointValuePair grid-point
 * value pairs} with a geometric structure.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_ValueHexagon", specification=ISO_19123)
public interface ValueHexagon extends ValueObject {
    /**
     * Returns the geometry of the value hexagon centred on the {@linkplain GridPointValuePair
     * grid point-value pairs} identified by the {@linkplain #getControlValues control values}.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject<Geometry> getGeometry();

    /**
     * Returns the <var>grid point</var>-<var>value</var> pairs at the {@code ValueHexagon} centre.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPointValuePair> getControlValues();
}
