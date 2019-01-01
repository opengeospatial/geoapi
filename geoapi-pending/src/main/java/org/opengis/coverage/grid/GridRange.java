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
package org.opengis.coverage.grid;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specifies the range of valid coordinates for each dimension of the coverage.
 * For example this data type is used to access a block of grid coverage data values.
 *
 * <div class="warning"><b>Warning — this class will change</b><br>
 * Current API is derived from OGC <a href="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</a>.
 * We plan to replace it by new interfaces derived from ISO 19123 (<cite>Schema for coverage geometry
 * and functions</cite>). Current interfaces should be considered as legacy and are included in this
 * distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much
 * compatibility as possible, but no migration plan has been determined yet.
 * </div>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 *
 * @deprecated Replaced by {@link GridEnvelope}.
 */
@Deprecated
@UML(identifier="CV_GridRange", specification=OGC_01004)
public interface GridRange extends GridEnvelope {
    /**
     * Returns the valid minimum inclusive grid coordinate.
     * The sequence contains a minimum value for each dimension of the grid coverage.
     *
     * @since GeoAPI 2.1
     *
     * @deprecated Renamed as {@link #getLow()}.
     */
    @Deprecated
    @UML(identifier="lo", obligation=MANDATORY, specification=OGC_01004)
    GridCoordinates getLower();

    /**
     * Returns the valid maximum exclusive grid coordinate.
     * The sequence contains a maximum value for each dimension of the grid coverage.
     *
     * @since GeoAPI 2.1
     *
     * @deprecated Replaced as {@link #getHigh()} with 1 added to all ordinate values.
     */
    @Deprecated
    @UML(identifier="hi", obligation=MANDATORY, specification=OGC_01004)
    GridCoordinates getUpper();

    /**
     * Returns the valid minimum inclusive grid
     * coordinate along the specified dimension.
     *
     * @deprecated Renamed as {@link #getLow(int)}.
     */
    @Deprecated
    int getLower(int dimension);

    /**
     * Returns the valid maximum exclusive grid
     * coordinate along the specified dimension.
     *
     * @deprecated Renamed as {@link #getHigh(int)} <strong>+ 1</strong>.
     */
    @Deprecated
    int getUpper(int dimension);

    /**
     * Returns the number of integer grid coordinates along the specified dimension.
     * This is equals to {@code getUpper(dimension)-getLower(dimension)}.
     *
     * @deprecated Renamed as {@link #getSpan(int)}.
     */
    @Deprecated
    int getLength(int dimension);
}
