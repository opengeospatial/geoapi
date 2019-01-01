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

import org.opengis.coverage.SampleDimensionType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Describes the packing of data values within grid coverages.
 * It includes the packing scheme of data values with less then 8 bits per value
 * within a byte, byte packing (Little Endian / Big Endian) for values with more
 * than 8 bits and the packing of the values within the dimensions.
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
 * @deprecated In favor of migrating to ISO 19123 definition for Coverage.
 */
@Deprecated
@UML(identifier="GC_GridPacking", specification=OGC_01004)
public interface GridPacking {
    /**
     * Order of bytes packed in values for sample dimensions with greater than 8 bits.
     */
    @UML(identifier="byteInValuePacking", obligation=MANDATORY, specification=OGC_01004)
    ByteInValuePacking getByteInValuePacking();

    /**
     * Order of values packed in a byte for sample dimensions with less than 8 bits.
     * This include
     * {@link SampleDimensionType#UNSIGNED_1BIT UNSIGNED_1BIT},
     * {@link SampleDimensionType#UNSIGNED_2BITS UNSIGNED_2BITS} and
     * {@link SampleDimensionType#UNSIGNED_4BITS UNSIGNED_4BITS} data types.
     */
    @UML(identifier="valueInBytePacking", obligation=MANDATORY, specification=OGC_01004)
    ValueInBytePacking getValueInBytePacking();

    /**
     * Gives the ordinate index for the band.
     * This index indicates how to form a band-specific coordinate from a grid coordinate
     * and a sample dimension number. This indicates the order in which the grid values
     * are stored in streamed data. This packing order is used when grid values are
     * retrieved using the {@code getPackedDataBlock} or set using
     * {@code setPackedDataBlock} operations on {@link GridCoverage}.
     *
     *  bandPacking of
     *  <UL>
     *    <li>0 : the full band-specific coordinate is (b, n1, n2...)</li>
     *    <li>1 : the full band-specific coordinate is (n1, b, n2...)</li>
     *    <li>2 : the full band-specific coordinate is (n1, n2, b...)</li>
     *  </UL>
     *  Where
     *  <UL>
     *    <li>b is band</li>
     *    <li>n1 is dimension 1</li>
     *    <li>n2 is dimension 2</li>
     *  </UL>
     *  For 2 dimensional grids, band packing of 0 is referred to as band sequential,
     *  1 line interleaved and 2 pixel interleaved.
     *
     *
     * @see java.awt.image.BandedSampleModel
     * @see java.awt.image.PixelInterleavedSampleModel
     */
    @UML(identifier="bandPacking", obligation=MANDATORY, specification=OGC_01004)
    int getBandPacking();
}
