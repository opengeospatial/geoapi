/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

// J2SE direct dependencies
import java.awt.image.Raster;              // For Javadoc
import java.awt.image.WritableRaster;      // For Javadoc
import java.awt.image.RenderedImage;       // For Javadoc

// OpenGIS direct dependencies
import org.opengis.coverage.Coverage;


/**
 * Represent the basic implementation which provides access to grid coverage data.
 * A <code>GridCoverage</code> implementation may provide the ability to update
 * grid values.
 *
 * @UML abstract CV_GridCoverage
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 *
 * @see RenderedImage
 * @see javax.media.jai.PixelAccessor
 */
public interface GridCoverage extends Coverage {
    /**
     * Returns <code>true</code> if grid data can be edited.
     *
     * @return <code>true</code> if grid data can be edited.
     * @UML mandatory dataEditable
     */
    boolean isDataEditable();

    /**
     * Information for the packing of grid coverage values.
     *
     * @return The information for the packing of grid coverage values.
     * @UML mandatory gridPacking
     */
    GridPacking getGridPacking();

    /**
     * Information for the grid coverage geometry.
     * Grid geometry includes the valid range of grid coordinates and the georeferencing.
     *
     * @return The information for the grid coverage geometry.
     * @UML mandatory gridGeometry
     */
    GridGeometry getGridGeometry();

    /**
     * Optimal size to use for each dimension when accessing grid values.
     * These values together give the optimal block size to use when retrieving
     * grid coverage values.
     * For example, a client application can achieve better performance for a 2-D grid
     * coverage by reading blocks of 128 by 128 if the grid is tiled into blocks of
     * this size.
     * The sequence is ordered by dimension.
     * If the implementation does not have optimal sizes, the sequence will be <code>null</code>.
     *
     * @return The optimal size to use for each dimension when accessing grid values,
     *         or <code>null</code> if none.
     * @UML optional optimalDataBlockSizes
     */
    int[] getOptimalDataBlockSizes();

    /**
     * Number of predetermined overviews for the grid.
     *
     * @return The number of predetermined overviews for the grid.
     * @UML mandatory numOverviews
     */
    int getNumOverviews();

    /**
     * Returns the grid geometry for an overview.
     *
     * @param overviewIndex Overview index for which to retrieve grid geometry. Indices start at 0.
     * @return The grid geometry for an overview.
     * @throws IndexOutOfBoundsException if <code>overviewIndex</code> is out of bounds.
     * @UML operation getOverviewGridGeometry
     */
    GridGeometry getOverviewGridGeometry(int overviewIndex) throws IndexOutOfBoundsException;

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
     * <blockquote><table border=0>
     * <tr>
     *   <th align="center">1 meter GC</th> <th>&nbsp;</th>
     *   <th align="center">3 meter overview</th>
     * </tr>
     * <tr>
     *   <td valign="top"><table border=0 align="center">
     *     <tr> <th>Index&nbsp;</th>      <th>&nbsp;resolution</th>  </tr>
     *     <tr> <td align="center">0</td> <td align="center"> 3</td> </tr>
     *     <tr> <td align="center">1</td> <td align="center"> 9</td> </tr>
     *     <tr> <td align="center">2</td> <td align="center">27</td> </tr>
     *   </table></td>
     *   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
     *   <td valign="top"><table border=0 align="center">
     *     <tr> <th>Index&nbsp;</th>      <th>&nbsp;resolution</th>  </tr>
     *     <tr> <td align="center">0</td> <td align="center"> 9</td> </tr>
     *     <tr> <td align="center">1</td> <td align="center">27</td> </tr>
     *   </table></td>
     * </table></blockquote>
     *
     * @param overviewIndex Index of grid coverage overview to retrieve. Indexes start at 0.
     * @return a pre-calculated overview for a grid coverage.
     * @throws IndexOutOfBoundsException if <code>overviewIndex</code> is out of bounds.
     * @UML operation getOverview
     */
    GridCoverage getOverview(int overviewIndex) throws IndexOutOfBoundsException;

    /**
     * Returns the source data for a grid coverage.
     * If the <code>GridCoverage</code> was produced from an underlying dataset
     * (by {@link GridCoverageReader#read read(...)} for instance) the
     * {@link #getNumSources getNumSources()} method should returns
     * zero, and this method should not be called.
     *
     * If the <code>GridCoverage</code> was produced using
     * {link org.opengis.coverage.processing.GridCoverageProcessor} then it should return the source
     * grid coverage of the one used as input to <code>GridCoverageProcessor</code>.
     * In general the <code>getSource(i)</code> method is intended to return the original
     * <code>GridCoverage</code> on which it depends.
     *
     * This is intended to allow applications to establish what <code>GridCoverage</code>s
     * will be affected when others are updated, as well as to trace back to the "raw data".
     *
     * @param sourceDataIndex Source grid coverage index. Indexes start at 0.
     * @return The source data for a grid coverage.
     * @throws IndexOutOfBoundsException if <code>sourceDataIndex</code> is out of bounds.
     * @UML operation Coverage.getSource
     */
/// GridCoverage getSource(int sourceDataIndex) throws IndexOutOfBoundsException;

    /**
     * Return a sequence of boolean values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  gridRange Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or <code>null</code> if none.
     * @return A sequence of boolean values for a given block in the coverage.
     *         If <code>destination</code> was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the <code>destination</code> array is not null
     *         and too small to hold the output.
     * @UML operation getDataBlockAsBoolean
     *
     * @see #setDataBlock(GridRange, boolean[])
     */
    boolean[] getDataBlock(GridRange gridRange, boolean[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of 8 bits values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  gridRange Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or <code>null</code> if none.
     * @return A sequence of 8 bits values for a given block in the coverage.
     *         If <code>destination</code> was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the <code>destination</code> array is not null
     *         and too small to hold the output.
     * @UML operation getDataBlockAsByte
     *
     * @see #setDataBlock(GridRange, byte[])
     * @see javax.media.jai.UnpackedImageData#getByteData()
     */
    byte[] getDataBlock(GridRange gridRange, byte[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of 16 bits values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  gridRange Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or <code>null</code> if none.
     * @return A sequence of 16 bits values for a given block in the coverage.
     *         If <code>destination</code> was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the <code>destination</code> array is not null
     *         and too small to hold the output.
     * @UML operation getDataBlockAsInteger
     *
     * @see #setDataBlock(GridRange, int[])
     * @see javax.media.jai.UnpackedImageData#getShortData()
     */
    short[] getDataBlock(GridRange gridRange, short[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of 32 bits values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  gridRange Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or <code>null</code> if none.
     * @return A sequence of 32 bits values for a given block in the coverage.
     *         If <code>destination</code> was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the <code>destination</code> array is not null
     *         and too small to hold the output.
     * @UML operation getDataBlockAsInteger
     *
     * @see #setDataBlock(GridRange, int[])
     * @see Raster#getPixels(int,int,int,int,int[])
     * @see javax.media.jai.UnpackedImageData#getIntData()
     */
    int[] getDataBlock(GridRange gridRange, int[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of float values for a block.
     * A value for each sample dimension will be returned.
     * The semantic is the same as {@link #getDataBlock(GridRange, double[])}
     * except for the return type.
     *
     * @param  gridRange Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or <code>null</code> if none.
     * @return A sequence of float values for a given block in the coverage.
     *         If <code>destination</code> was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the <code>destination</code> array is not null
     *         and too small to hold the output.
     *
     * @see #setDataBlock(GridRange, float[])
     * @see Raster#getPixels(int,int,int,int,float[])
     * @see javax.media.jai.UnpackedImageData#getFloatData()
     */
    float[] getDataBlock(GridRange gridRange, float[] destination)
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
     * safe array will need to be offset by <code>gridRange</code> {@linkplain GridRange#getLower()
     * minimum coordinates} to get equivalent grid coordinates.
     * <br><br>
     * The requested grid range must satisfy the following rules for each dimension of the grid
     * coverage:
     * <center>
     * minimum grid coordinate <= {@linkplain GridRange#getLower() grid range mimimun} <=
     * {@linkplain GridRange#getUpper() grid range maximum} <= maximum grid coordinate
     * </center>
     *
     * The number of values returned will equal:
     * <center>
     * (max<sub>1</sub> � min<sub>1</sub> + 1) *
     * (max<sub>2</sub> � min<sub>2</sub> + 1) ... *
     * (max<sub>n</sub> � min<sub>n</sub> + 1) *
     * {@link #getNumSampleDimensions numSampleDimensions}
     * </center>
     *
     * Where <var>min</var> is the minimum ordinate in the grid range,
     * <var>max</var> is the maximum ordinate in the grid range and
     * <VAR>N</VAR> is the number of dimensions in the grid coverage.
     *
     * @param  gridRange Grid range for block of data to be accessed.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or <code>null</code> if none.
     * @return A sequence of double values for a given block in the coverage.
     *         If <code>destination</code> was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws ArrayIndexOutOfBoundsException if the <code>destination</code> array is not null
     *         and too small to hold the output.
     * @UML operation getValueBlockAsDouble
     *
     * @rename Renamed <code>getValueBlockAsDouble</code> as <code>getDataBlockAsDouble</code>
     *         for consistency with all others <code>getDataBlock...</code> methods and
     *         <code>setDataBlockAsDouble</code>.
     *
     * @revisit Which indices vary fastest?
     *
     * @see #setDataBlock(GridRange, double[])
     * @see Raster#getPixels(int,int,int,int,double[])
     * @see javax.media.jai.UnpackedImageData#getDoubleData()
     */
    double[] getDataBlock(GridRange gridRange, double[] destination)
            throws InvalidRangeException, ArrayIndexOutOfBoundsException;

    /**
     * Return a block of grid coverage data for all sample dimensions.
     * A value for each sample dimension will be returned.
     * This operation provides efficient access of the grid values.
     * The sequencing order of the values in the sequence will follow the rules
     * given by <code>valueInBytePacking</code> and <code>bandPacking</code>
     * defined in {@link GridPacking}.
     *
     * The requested grid range must satisfy the following rules for each dimension
     * of the grid coverage:
     *
     * <center>
     * minimum grid coordinate <= {@linkplain GridRange#getLower() grid range mimimun} <=
     * {@linkplain GridRange#getUpper() grid range maximum} <= maximum grid coordinate
     * </center>
     *
     * The sequence of bytes returned will match the data type of
     * the dimension. For example, a grid with one 16 bit unsigned
     * ({@link org.opengis.coverage.SampleDimensionType#UNSIGNED_16BITS UNSIGNED_16BITS})
     * sample dimension will return 2 bytes for every cell in the block.
     * <br><br>
     * <strong>Byte padding rules for grid values of less than 8 bits</strong><br>
     * For 2D grid coverages, padding is to the nearest byte for the following cases:
     *
     * <table cellpadding=6 border=0>
     * <tr> <td>For PixelInterleaved</td>
     *      <td>For grids with multiple sample dimensions, padding occurs between
     *          pixels for each change in dimension type.</td>
     * </tr>
     * <tr> <td>For LineInterleaved</td>
     *      <td>Padding occurs at the end of each row or column (depending on the
     *          valueSequence of the grid).</td>
     * </tr>
     * <tr> <td>For BandSequencial</td>
     *      <td>Padding occurs at the end of every sample dimension.</td>
     * </tr>
     * </table>
     *
     * For grid values smaller than 8 bits, their order within each byte is given by the
     * value defined in {@link GridPacking#getValueInBytePacking valueInBytePacking}.
     * For grid values bigger than 8 bits, the order of their bytes is given by the
     * value defined in {@link GridPacking#getByteInValuePacking byteInValuePacking}.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @return a block of grid coverage data for all sample dimensions.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @UML operation #getPackedDataBlock
     *
     * @revisit This operation can't be implemented efficiently in Java with a <code>byte[]</code>
     *          return type, since there is no way to cast an array of arbitrary type to an array
     *          of type <code>byte[]</code>. Even the <code>java.nio.Buffer</code> doesnt allow
     *          that (it allows the opposite way however).
     */
    byte[] getPackedDataBlock(GridRange gridRange) throws InvalidRangeException;

    /**
     * Set a block of boolean values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the <code>values</code> array is too small.
     * @UML operation setDataBlockAsBoolean
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, boolean[])
     */
    void setDataBlock(GridRange gridRange, boolean[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of 8 bits values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the <code>values</code> array is too small.
     * @UML operation setDataBlockAsByte
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, byte[])
     */
    void setDataBlock(GridRange gridRange, byte[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of 16 bits values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the <code>values</code> array is too small.
     * @UML operation setDataBlockAsByte
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, short[])
     */
    void setDataBlock(GridRange gridRange, short[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of 32 bits values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the <code>values</code> array is too small.
     * @UML operation setDataBlockAsInteger
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, int[])
     * @see WritableRaster#setPixels(int,int,int,int,int[])
     */
    void setDataBlock(GridRange gridRange, int[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of float values for all sample dimensions.
     * The semantic is the same as {@link #setDataBlock(GridRange, double[])}.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the <code>values</code> array is too small.
     * @UML operation setDataBlockAsInteger
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, float[])
     * @see WritableRaster#setPixels(int,int,int,int,float[])
     */
    void setDataBlock(GridRange gridRange, float[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of double values for all sample dimensions.
     * The requested grid range must satisfy the following rules for each
     * dimension of the grid coverage:
     *
     * <center>
     * minimum grid coordinate <= {@linkplain GridRange#getLower() grid range mimimun} <=
     * {@linkplain GridRange#getUpper() grid range maximum} <= maximum grid coordinate
     * </center>
     *
     * The number of values must equal:
     *
     * <center>
     * (max<sub>1</sub> � min<sub>1</sub> + 1) *
     * (max<sub>2</sub> � min<sub>2</sub> + 1) ... *
     * (max<sub>n</sub> � min<sub>n</sub> + 1) *
     * {@link #getNumSampleDimensions numSampleDimensions}
     * </center>
     *
     * Where <var>min</var> is the minimum ordinate in the grid range,
     * <var>max</var> is the maximum ordinate in the grid range and
     * <VAR>N</VAR> is the number of dimensions in the grid coverage.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the <code>values</code> array is too small.
     * @UML operation setDataBlockAsDouble
     *
     * @see #isDataEditable
     * @see #getDataBlock(GridRange, double[])
     * @see WritableRaster#setPixels(int,int,int,int,double[])
     */
    void setDataBlock(GridRange gridRange, double[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;

    /**
     * Set a block of grid coverage data for all sample dimensions.
     * See {@link #getPackedDataBlock} for details on how to pack the values.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws InvalidRangeException if <code>gridRange</code> is out of this grid range bounds.
     * @throws GridNotEditableException if the grid coverage is not {@linkplain #isDataEditable editable}.
     * @throws ArrayIndexOutOfBoundsException if the <code>values</code> array is too small.
     * @UML operation setPackedDataBlock
     *
     * @revisit This operation can hardly be implemented efficiently in Java with a
     *          <code>byte[]</code> argument type, since we can't easily cast an array
     *          of <code>byte[]</code> to an array of arbitrary type.
     */
    void setPackedDataBlock(GridRange gridRange, byte[] values)
            throws InvalidRangeException, GridNotEditableException, ArrayIndexOutOfBoundsException;
}
