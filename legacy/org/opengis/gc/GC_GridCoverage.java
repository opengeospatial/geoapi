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

// Remote Method Invocation
import java.rmi.Remote;
import java.rmi.RemoteException;

// GCS dependencies
import org.opengis.cv.*;


/**
 * Represent the basic implementation which provides access to grid coverage data.
 * A <code>GC_GridCoverage</code> implementation may provide the ability to update
 * grid values. A basic read-only implementation would be fairly easy to implement.
 *
 * @version 1.00
 * @since   1.00
 *
 * @deprecated
 */
@Deprecated
public interface GC_GridCoverage extends CV_Coverage {
    /**
     * Returns <code>true</code> if grid data can be edited.
     *
     * @return <code>true</code> if grid data can be edited.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    boolean isDataEditable() throws RemoteException;

    /**
     * Information for the packing of grid coverage values.
     *
     * @return the information for the packing of grid coverage values.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    GC_GridPacking getGridPacking() throws RemoteException;

    /**
     * Information for the grid coverage geometry.
     * Grid geometry includes the valid range of grid coordinates and the georeferencing.
     *
     * @return the information for the grid coverage geometry.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    GC_GridGeometry getGridGeometry() throws RemoteException;

    /**
     * Number of predetermined overviews for the grid.
     *
     * @return the number of predetermined overviews for the grid.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    int getNumOverviews() throws RemoteException;

    /**
     * Optimal size to use for each dimension when accessing grid values.
     * These values together give the optimal block size to use when retrieving
     * grid coverage values.
     * For example, a client application can achieve better performance for a 2-D grid
     * coverage by reading blocks of 128 by 128 if the grid is tiled into blocks of
     * this size.
     * The sequence is ordered by dimension.
     * If the implementation does not have optimal sizes the sequence will be empty.
     *
     * @return the optimal size to use for each dimension when accessing grid values.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    int[] getOptimalDataBlockSizes() throws RemoteException;

    /**
     * Return the grid geometry for an overview.
     *
     * @param overviewIndex Overview index for which to retrieve grid geometry. Indices start at 0.
     * @return the grid geometry for an overview.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    GC_GridGeometry getOverviewGridGeometry(int overviewIndex) throws RemoteException;

    /**
     * Returns a pre-calculated overview for a grid coverage.
     * The overview indices are numbered from 0 to <code>numberOverviews-1</code>.
     * The overviews are ordered from highest (index 0)
     * to lowest (numberOverviews -1) resolution.
     * Overview grid coverages will have overviews which are the overviews for
     * the grid coverage with lower resolution than the overview.
     * For example, a 1 meter grid coverage with 3, 9, and 27 meter overviews
     * will be ordered as follows:
     *
     * <table border=0 align="center">
     *   <tr> <td align="center">Index</td> <td align="center">resolution</td> </tr>
     *   <tr> <td align="center">  0  </td> <td align="center">   3      </td> </tr>
     *   <tr> <td align="center">  1  </td> <td align="center">   9      </td> </tr>
     *   <tr> <td align="center">  2  </td> <td align="center">   27     </td> </tr>
     * </table><br><br>
     *
     * The 3 meter overview will have 2 overviews as follows:
     * <table border=0 align="center">
     *   <tr> <td align="center">Index</td> <td align="center">resolution</td> </tr>
     *   <tr> <td align="center">  0  </td> <td align="center">   9      </td> </tr>
     *   <tr> <td align="center">  1  </td> <td align="center">   27     </td> </tr>
     * </table>
     *
     * @param overviewIndex Index of grid coverage overview to retrieve. Indexes start at 0.
     * @return a pre-calculated overview for a grid coverage.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    GC_GridCoverage getOverview(int overviewIndex) throws RemoteException;


    /**
     * Return a sequence of boolean values for a block.
     * A value for each sample dimension will be returned.
     *
     * @param  gridRange Grid range for block of data to be accessed.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    boolean[] getDataBlockAsBoolean(GC_GridRange gridRange) throws RemoteException;

    /**
     * Return a sequence of byte values for a block.
     * A value for each sample dimension will be returned.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    byte[] getDataBlockAsByte(GC_GridRange gridRange) throws RemoteException;

    /**
     * Return a sequence of int values for a block.
     * A value for each sample dimension will be returned.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    int[] getDataBlockAsInteger(GC_GridRange gridRange) throws RemoteException;

    /**
     * Return a sequence of double values for a block.
     * A value for each sample dimension will be returned.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    double [] getValueBlockAsDouble(GC_GridRange gridRange) throws RemoteException;

    /**
     * Return a block of grid coverage data for all sample dimensions.
     * A value for each sample dimension will be returned.
     * This operation provides efficient access of the grid values.
     * The sequencing order of the values in the sequence will follow the rules
     * given by valueInBytePacking and bandPacking defined in GC_GridPacking.
     *
     * The requested grid range must satisfy the following rules for each dimension
     * of the grid coverage:
     *
     * <blockquote><pre>
     * Min grid coordinate <= grid range minimum <= grid range maximum <= maximum grid coordinate
     * </pre></blockquote>
     *
     * The sequence of bytes returned will match the data type of the dimension.
     * For example, a grid with one 16 bit unsigned (CV_16BIT_U) sample dimension will
     * return 2 bytes for every cell in the block.
     * <br><br>
     * <strong>Byte padding Rules for grid values of less than 8 bits</strong><br>
     * For 2 D grid coverages, padding is to the nearest byte for the following cases:
     *
     * <table border=0>
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
     * value defined in {@link GC_GridPacking#getValueInBytePacking valueInBytePacking}.
     * For grid values bigger than 8 bits, the order of their bytes is given by the
     * value defined in {@link GC_GridPacking#getByteInValuePacking byteInValuePacking}.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @return a block of grid coverage data for all sample dimensions.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    byte[] getPackedDataBlock(GC_GridRange gridRange) throws RemoteException;

    /**
     * Set a block of boolean values for all sample dimensions.
     * The requested grid range must satisfy the following rules for each
     * dimension of the grid coverage:
     *
     * <blockquote><pre>
     * Min grid coordinate <= grid range minimum <= grid range maximum <= maximum grid coordinate
     * </pre></blockquote>
     *
     * The number of values must equal:
     *
     * <blockquote><pre>
     * (Max1   Min1 + 1) * (Max2   Min2 + 1)... * (Maxn   Minn + 1) * numberSampleDimensions
     * </pre></blockquote>
     *
     *  Where
     *
     * <UL>
     *   <li>Min is the mimium ordinate in the grid range</li>
     *   <li> Max is the maximum ordinate in the grid range</li>
     *   <li> N is the number of dimensions in the grid coverage</li>
     * </UL>
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    void setDataBlockAsBoolean(GC_GridRange gridRange, boolean [] values) throws RemoteException;

    /**
     * Set a block of byte values for all sample dimensions.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    void setDataBlockAsByte(GC_GridRange gridRange, byte [] values) throws RemoteException;

    /**
     * Set a block of bint values for all sample dimensions.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    void setDataBlockAsInteger(GC_GridRange gridRange, int [] values) throws RemoteException;

    /**
     * Set a block of double values for all sample dimensions.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    void setDataBlockAsDouble(GC_GridRange gridRange, double [] values) throws RemoteException;

    /**
     * Set a block of grid coverage data for all sample dimensions.
     * See <code>getDataBlock</code> for details on how to pack the values.
     *
     * The requested grid range must satisfy the following rules for each dimension
     * of the grid coverage:
     *
     * <blockquote><pre>
     * Min grid coordinate <= grid range minimum <= grid range maximum <= maximum grid coordinate
     * </pre></blockquote>
     *
     * For byte padding rules see <code>getDataBlock</code>.
     *
     * @param gridRange Grid range for block of data to be accessed.
     * @param values Sequence of grid values for the given region.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated
     */
    @Deprecated
    void setPackedDataBlock(GC_GridRange gridRange, byte [] values) throws RemoteException;
}
