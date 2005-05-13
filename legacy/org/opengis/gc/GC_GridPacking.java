/*
 * OpenGIS® Grid Coverage Implementation Specification
 *
 * This Java profile is derived from OpenGIS's specification
 * available on their public web site:
 *
 *     http://www.opengis.org/techno/implementation.htm
 *
 * You can redistribute it, but should not modify it unless
 * for greater OpenGIS compliance.
 */
package org.opengis.gc;


/**
 * Describes the packing of data values within grid coverages.
 * It includes the packing scheme of data values with less then 8 bits per value
 * within a byte, byte packing (Little Endian / Big Endian) for values with more
 * than 8 bits and the packing of the values within the dimensions.
 *
 * @version 1.00
 * @since   1.00
 *
 * @deprecated
 */
@Deprecated
public interface GC_GridPacking {
    /**
     * Order of bytes packed in values for sample dimensions with greater than 8 bits.
     *
     * @deprecated
     */
    @Deprecated
    GC_ByteInValuePacking getByteInValuePacking();

    /**
     * Order of values packed in a byte for
     * {@link org.opengis.cv.CV_SampleDimensionType#CV_1BIT CV_1BIT},
     * {@link org.opengis.cv.CV_SampleDimensionType#CV_2BIT CV_2BIT} and
     * {@link org.opengis.cv.CV_SampleDimensionType#CV_4BIT CV_4BIT} data types.
     *
     * @deprecated
     */
    @Deprecated
    GC_ValueInBytePacking getValueInBytePacking();

    /**
     * Gives the ordinate index for the band.
     * This index indicates how to form a band-specific coordinate from a grid coordinate
     * and a sample dimension number. This indicates the order in which the grid values
     * are stored in streamed data. This packing order is used when grid values are
     * retrieved using the <code>getPackedDataBlock</code> or set using
     * <code>setPackedDataBlock</code> operations on {@link GC_GridCoverage}.
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
     * @deprecated
     */
    @Deprecated
    int getBandPacking();
}
