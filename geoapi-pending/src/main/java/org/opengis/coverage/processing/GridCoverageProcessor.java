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
package org.opengis.coverage.processing;

import java.util.Collection;
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.coverage.SampleDimensionType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides operations for different ways of accessing the grid coverage values as well as
 * image processing functionality. The list of available processing operations is implementation
 * dependent. The interface has a discovery mechanism to determine the available processing
 * operations.
 * <p>
 * These processing operations will transform values within a single sample dimension, and
 * leave the values in other sample dimensions unaffected. The modified sample dimension may
 * also change its type (e.g. from {@link SampleDimensionType#UNSIGNED_4BITS UNSIGNED_4BITS} to
 * {@link SampleDimensionType#UNSIGNED_1BIT UNSIGNED_1BIT}). The actual underlying grid data
 * remains unchanged.
 * <p>
 * The interface has been designed to allow the adaptations to be done in a "pipe-lined" manner.
 * The interface operates on {@link GridCoverage} to create new a {@link GridCoverage}. The
 * interface does not need to make a copy of the source grid data. Instead, it can return a
 * grid coverage object which applies the adaptations on the original grid coverage whenever
 * a block of data is requested. In this way, a pipeline of several grid coverages can be
 * constructed cheaply.
 * <p>
 * This interface can perform any of the following:
 * <ul>
 *   <li>Change the number of bands being accessed.</li>
 *   <li>Change the value sequencing in which the grid values are retrieved.</li>
 *   <li>Allow re-sampling of the grid coverage for a different geometry.
 *       Creating a new {@link GridCoverage} with different grid geometry allows for reprojecting
 *       the grid coverage to another projection and another georeferencing type, resampling to
 *       another cell resolution and subsetting the grid coverage.</li>
 *   <li>Modify the way the grid values are accessed (filtered, classified...).</li>
 *   <li>Change the interpolation method used when evaluating points which fall between grid cells.</li>
 *   <li>Filtering.</li>
 *   <li>Image enhancements.</li>
 *   <li><i>etc.</i></li>
 * </ul>
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
 * @deprecated Need to be replaced by a mechanism better aligned on WCPS specification.
 */
@Deprecated
@UML(identifier="GP_GridCoverageProcessor", specification=OGC_01004)
public interface GridCoverageProcessor {
    /**
     * Retrieves grid processing operations information.
     * Each operation information will contain the name of the operation as well
     * as a list of its parameters.
     *
     * @return the available operations.
     */
    @UML(identifier="getOperation", obligation=MANDATORY, specification=OGC_01004)
    Collection<Operation> getOperations();
}
