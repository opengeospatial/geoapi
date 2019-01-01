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

import java.util.List;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.image.RenderedImage;
import org.opengis.coverage.Coverage;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represent the basic implementation which provides access to grid coverage data.
 * A {@code GridCoverage} implementation may provide the ability to update grid values.
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
 * @see RenderedImage
 */
@UML(identifier="CV_GridCoverage", specification=OGC_01004)
public interface GridCoverage extends Coverage {
    /**
     * Returns {@code true} if grid data can be edited.
     *
     * @return {@code true} if grid data can be edited.
     */
    @UML(identifier="dataEditable", obligation=MANDATORY, specification=OGC_01004)
    boolean isDataEditable();

    /**
     * Information for the packing of grid coverage values.
     *
     * @return the information for the packing of grid coverage values.
     */
    @UML(identifier="gridPacking", obligation=MANDATORY, specification=OGC_01004)
    GridPacking getGridPacking();

    /**
     * Information for the grid coverage geometry.
     * Grid geometry includes the valid range of grid coordinates and the georeferencing.
     *
     * @return the information for the grid coverage geometry.
     */
    @UML(identifier="gridGeometry", obligation=MANDATORY, specification=OGC_01004)
    GridGeometry getGridGeometry();

    /**
     * Optimal size to use for each dimension when accessing grid values.
     * These values together give the optimal block size to use when retrieving
     * grid coverage values.
     * For example, a client application can achieve better performance for a 2-D grid
     * coverage by reading blocks of 128 by 128 if the grid is tiled into blocks of
     * this size.
     * The sequence is ordered by dimension.
     * If the implementation does not have optimal sizes, the sequence will be {@code null}.
     *
     * @return the optimal size to use for each dimension when accessing grid values,
     *         or {@code null} if none.
     */
    @UML(identifier="optimalDataBlockSizes", obligation=OPTIONAL, specification=OGC_01004)
    int[] getOptimalDataBlockSizes();

    /**
     * Number of predetermined overviews for the grid.
     *
     * @return the number of predetermined overviews for the grid.
     */
    @UML(identifier="numOverviews", obligation=MANDATORY, specification=OGC_01004)
    int getNumOverviews();

    /**
     * Returns the grid geometry for an overview.
     *
     * @param  index Overview index for which to retrieve grid geometry. Indices start at 0.
     * @return the grid geometry for an overview.
     * @throws IndexOutOfBoundsException if {@code overviewIndex} is out of bounds.
     */
    @UML(identifier="getOverviewGridGeometry", obligation=MANDATORY, specification=OGC_01004)
    GridGeometry getOverviewGridGeometry(int index) throws IndexOutOfBoundsException;

    /**
     * Returns a pre-calculated overview for a grid coverage. The overview indices are numbered
     * from 0 to <code>{@linkplain #getNumOverviews numberOverviews}-1</code>.
     * The overviews are ordered from highest (index 0) to lowest
     * (<code>{@linkplain #getNumOverviews numberOverviews}-1</code>) resolution.
     * Overview grid coverages will have overviews which are the overviews for
     * the grid coverage with lower resolution than the overview.
     * For example, a 1 meter grid coverage with 3, 9, and 27 meter overviews
     * will be ordered as in the left side below. The 3 meter overview will have
     * 2 overviews as in the right side below:
     *
     * <blockquote><table border="0" summary="Overview indices">
     * <tr>
     *   <th align="center">1 meter GC</th> <th>&nbsp;</th>
     *   <th align="center">3 meter overview</th>
     * </tr>
     * <tr>
     *   <td valign="top"><table border="0" align="center" summary="Overview indices">
     *     <tr> <th>Index&nbsp;</th>      <th>&nbsp;resolution</th>  </tr>
     *     <tr> <td align="center">0</td> <td align="center"> 3</td> </tr>
     *     <tr> <td align="center">1</td> <td align="center"> 9</td> </tr>
     *     <tr> <td align="center">2</td> <td align="center">27</td> </tr>
     *   </table></td>
     *   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
     *   <td valign="top"><table border="0" align="center" summary="Overview indices">
     *     <tr> <th>Index&nbsp;</th>      <th>&nbsp;resolution</th>  </tr>
     *     <tr> <td align="center">0</td> <td align="center"> 9</td> </tr>
     *     <tr> <td align="center">1</td> <td align="center">27</td> </tr>
     *   </table></td>
     * </table></blockquote>
     *
     * @param  index Index of grid coverage overview to retrieve. Indexes start at 0.
     * @return a pre-calculated overview for a grid coverage.
     * @throws IndexOutOfBoundsException if {@code overviewIndex} is out of bounds.
     */
    @UML(identifier="getOverview", obligation=MANDATORY, specification=OGC_01004)
    GridCoverage getOverview(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the sources data for a grid coverage. If the {@code GridCoverage} was
     * produced from an underlying dataset, this method should returns an empty list.
     *
     * If the {@code GridCoverage} was produced using
     * {link org.opengis.coverage.processing.GridCoverageProcessor} then it should return the
     * source grid coverages of the one used as input to {@code GridCoverageProcessor}.
     * In general this method is intended to return the original {@code GridCoverage}
     * on which it depends.
     *
     * This is intended to allow applications to establish what {@code GridCoverage}s
     * will be affected when others are updated, as well as to trace back to the "raw data".
     *
     * @return the sources data for a grid coverage.
     */
    List<GridCoverage> getSources();

    /**
     * Return a sequence of boolean values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  range Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return a sequence of boolean values for a given block in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     *
     * @see #setDataBlock(GridRange, boolean[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="getDataBlockAsBoolean", obligation=MANDATORY, specification=OGC_01004)
    boolean[] getDataBlock(GridRange range, boolean[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of 8 bits values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  range Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return a sequence of 8 bits values for a given block in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     *
     * @see #setDataBlock(GridRange, byte[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="getDataBlockAsByte", obligation=MANDATORY, specification=OGC_01004)
    byte[] getDataBlock(GridRange range, byte[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of 16 bits values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  range Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return a sequence of 16 bits values for a given block in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     *
     * @see #setDataBlock(GridRange, int[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="getDataBlockAsInteger", obligation=MANDATORY, specification=OGC_01004)
    short[] getDataBlock(GridRange range, short[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of 32 bits values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  range Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return a sequence of 32 bits values for a given block in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     *
     * @see #setDataBlock(GridRange, int[])
     * @see Raster#getPixels(int,int,int,int,int[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="getDataBlockAsInteger", obligation=MANDATORY, specification=OGC_01004)
    int[] getDataBlock(GridRange range, int[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of float values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  range Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return a sequence of float values for a given block in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     *
     * @see #setDataBlock(GridRange, float[])
     * @see Raster#getPixels(int,int,int,int,float[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    float[] getDataBlock(GridRange range, float[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of double values for a block.
     * A value for each sample dimension will be returned.
     *
     * The return value is an <VAR>N</VAR>+1 dimensional safe-array, with dimensions
     * (sample dimension, dimension <var>n</var>, dimension <var>n</var>-1, ... dimension 1).
     *
     * For 2 dimensional grid coverages, this safe array will be accessed as
     * (sample dimension, column, row).
     *
     * The index values will be based from 0. The indices in the returned <VAR>N</VAR> dimensional
     * safe array will need to be offset by {@code range} {@linkplain GridRange#getLower()
     * minimum coordinates} to get equivalent grid coordinates.
     * <p>
     * The requested grid range must satisfy the following rules for each dimension of the grid
     * coverage:
     * <center>
     * minimum grid coordinate &lt;= {@linkplain GridRange#getLower() grid range minimum} &lt;=
     * {@linkplain GridRange#getUpper() grid range maximum} &lt;= maximum grid coordinate
     * </center>
     *
     * The number of values returned will equal:
     * <center>
     * (max₁ - min₁ + 1) *
     * (max₂ - min₂ + 1) ... *
     * (max<sub>n</sub> - min<sub>n</sub> + 1) *
     * {@link #getNumSampleDimensions numSampleDimensions}
     * </center>
     *
     * Where <var>min</var> is the minimum ordinate in the grid range,
     * <var>max</var> is the maximum ordinate in the grid range and
     * <VAR>N</VAR> is the number of dimensions in the grid coverage.
     *
     * @departure integration
     *   OGC 01-004 defines this method as <code>getValueBlockAsDouble(GridRange)</code>.
     *   GeoAPI adds the <code>double[]</code> argument for reusing pre-allocated arrays,
     *   which is consistent with usage in <code>java.awt.image.Raster</code>.
     *
     * @param  range Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return a sequence of double values for a given block in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     *
     * @departure rename
     *   Renamed {@code getValueBlockAsDouble} as {@code getDataBlockAsDouble}
     *   for consistency with all others {@code getDataBlock...} methods and
     *   {@code setDataBlockAsDouble}.
     *
     * @todo Which indices vary fastest?
     *
     * @see #setDataBlock(GridRange, double[])
     * @see Raster#getPixels(int,int,int,int,double[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="getValueBlockAsDouble", obligation=MANDATORY, specification=OGC_01004)
    double[] getDataBlock(GridRange range, double[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a block of grid coverage data for all sample dimensions.
     * A value for each sample dimension will be returned.
     * This operation provides efficient access of the grid values.
     * The sequencing order of the values in the sequence will follow the rules
     * given by {@code valueInBytePacking} and {@code bandPacking}
     * defined in {@link GridPacking}.
     *
     * The requested grid range must satisfy the following rules for each dimension
     * of the grid coverage:
     *
     * <center>
     * minimum grid coordinate &lt;= {@linkplain GridRange#getLower() grid range mimimun} &lt;=
     * {@linkplain GridRange#getUpper() grid range maximum} &lt;= maximum grid coordinate
     * </center>
     *
     * The sequence of bytes returned will match the data type of
     * the dimension. For example, a grid with one 16 bit unsigned
     * ({@link org.opengis.coverage.SampleDimensionType#UNSIGNED_16BITS UNSIGNED_16BITS})
     * sample dimension will return 2 bytes for every cell in the block.
     * <p>
     * <strong>Byte padding rules for grid values of less than 8 bits</strong><br>
     * For 2D grid coverages, padding is to the nearest byte for the following cases:
     *
     * <table cellpadding="6" border="0" summary="Padding">
     * <tr> <td>For PixelInterleaved</td>
     *      <td>For grids with multiple sample dimensions, padding occurs between
     *          pixels for each change in dimension type.</td>
     * </tr>
     * <tr> <td>For LineInterleaved</td>
     *      <td>Padding occurs at the end of each row or column (depending on the
     *          valueSequence of the grid).</td>
     * </tr>
     * <tr> <td>For BandSequential</td>
     *      <td>Padding occurs at the end of every sample dimension.</td>
     * </tr>
     * </table>
     *
     * For grid values smaller than 8 bits, their order within each byte is given by the
     * value defined in {@link GridPacking#getValueInBytePacking valueInBytePacking}.
     * For grid values bigger than 8 bits, the order of their bytes is given by the
     * value defined in {@link GridPacking#getByteInValuePacking byteInValuePacking}.
     *
     * @param range Grid range for block of data to be accessed.
     * @return a block of grid coverage data for all sample dimensions.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     *
     * @todo This operation can't be implemented efficiently in Java with a {@code byte[]}
     *       return type, since there is no way to cast an array of arbitrary type to an array
     *       of type {@code byte[]}. Even the {@code java.nio.Buffer} doesnt allow
     *       that (it allows the opposite way however).
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="getPackedDataBlock", obligation=MANDATORY, specification=OGC_01004)
    byte[] getPackedDataBlock(GridRange range) throws InvalidRangeException;

    /**
     * Set a block of boolean values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param range Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the {@code values} array is too small.
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, boolean[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="setDataBlockAsBoolean", obligation=MANDATORY, specification=OGC_01004)
    void setDataBlock(GridRange range, boolean[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of 8 bits values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param range Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the {@code values} array is too small.
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, byte[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="setDataBlockAsByte", obligation=MANDATORY, specification=OGC_01004)
    void setDataBlock(GridRange range, byte[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of 16 bits values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param range Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the {@code values} array is too small.
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, short[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="setDataBlockAsByte", obligation=MANDATORY, specification=OGC_01004)
    void setDataBlock(GridRange range, short[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of 32 bits values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param range Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the {@code values} array is too small.
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, int[])
     * @see WritableRaster#setPixels(int,int,int,int,int[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="setDataBlockAsInteger", obligation=MANDATORY, specification=OGC_01004)
    void setDataBlock(GridRange range, int[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of float values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param range Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the {@code values} array is too small.
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, float[])
     * @see WritableRaster#setPixels(int,int,int,int,float[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="setDataBlockAsInteger", obligation=MANDATORY, specification=OGC_01004)
    void setDataBlock(GridRange range, float[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of double values for all sample dimensions.
     * The requested grid range must satisfy the following rules for each
     * dimension of the grid coverage:
     *
     * <center>
     * minimum grid coordinate &lt;= {@linkplain GridRange#getLower() grid range mimimun} &lt;=
     * {@linkplain GridRange#getUpper() grid range maximum} &lt;= maximum grid coordinate
     * </center>
     *
     * The number of values must equal:
     *
     * <center>
     * (max₁ - min₁ + 1) *
     * (max₂ - min₂ + 1) ... *
     * (max<sub>n</sub> - min<sub>n</sub> + 1) *
     * {@link #getNumSampleDimensions numSampleDimensions}
     * </center>
     *
     * Where <var>min</var> is the minimum ordinate in the grid range,
     * <var>max</var> is the maximum ordinate in the grid range and
     * <VAR>N</VAR> is the number of dimensions in the grid coverage.
     *
     * @param range Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if {@code range} is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the {@code values} array is too small.
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, double[])
     * @see WritableRaster#setPixels(int,int,int,int,double[])
     *
     * @deprecated We should use some higher level construct instead (multi-dimensional array
     *             or something similar).
     */
    @Deprecated
    @UML(identifier="setDataBlockAsDouble", obligation=MANDATORY, specification=OGC_01004)
    void setDataBlock(GridRange range, double[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;
}
