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
package org.opengis.cv;

// CSS dependencies
import org.opengis.pt.PT_Envelope;
import org.opengis.pt.PT_CoordinatePoint;
import org.opengis.cs.CS_CoordinateSystem;

// Forward GCS dependencies
import org.opengis.gc.GC_GridCoverage;

// Remote Method Invocation
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Provides access to an OpenGIS coverage.
 * The essential property of coverage is to be able to generate a value for any point
 * within its domain. How coverage is represented internally is not a concern.
 *
 * For example consider the following different internal representations of coverage:<br>
 *  <OL>
 *    <li>A coverage may be represented by a set of polygons which exhaustively
 *        tile a plane (that is each point on the plane falls in precisely one polygon).
 *        The value returned by the coverage for a point is the value of an attribute of
 *        the polygon that contains the point.</li>
 *    <li>A coverage may be represented by a grid of values.
 *        The value returned by the coverage for a point is that of the grid value
 *        whose location is nearest the point.</li>
 *    <li>Coverage may be represented by a mathematical function.
 *        The value returned by the coverage for a point is just the return value
 *        of the function when supplied the coordinates of the point as arguments.</li>
 *    <li>Coverage may be represented by combination of these.
 *        For example, coverage may be represented by a combination of mathematical
 *        functions valid over a set of polynomials.</LI>
 * </OL>
 *
 * A coverage has a corresponding {@link CV_SampleDimension} for each sample
 * dimension in the coverage.
 *
 * @version 1.00
 * @since   1.00
 */
public interface CV_Coverage extends Remote {
    /**
     * The number of sample dimensions in the coverage.
     * For grid coverages, a sample dimension is a band.
     *
     * @return the number of sample dimensions in the coverage.
     * @throws RemoteException if a remote method call failed.
     */
    int getNumSampleDimensions() throws RemoteException;

    /**
     * The names of each dimension in the coverage.
     * Typically these names are <var>x</var>, <var>y</var>, <var>z</var> and <var>t</var>.
     * The number of items in the sequence is the number of dimensions in the coverage.
     * Grid coverages are typically 2D (<var>x</var>, <var>y</var>) while other coverages
     * may be 3D (<var>x</var>, <var>y</var>, <var>z</var>) or
     * 4D (<var>x</var>, <var>y</var>, <var>z</var>, <var>t</var>).
     * The number of dimensions of the coverage is the number of entries in the
     * list of dimension names.
     *
     * @return the names of each dimension in the coverage.
     * @throws RemoteException if a remote method call failed.
     */
    String[] getDimensionNames() throws RemoteException;

    /**
     * Number of grid coverages which the grid coverage was derived from.
     * This implementation specification does not include interfaces for creating
     * collections of coverages therefore this value will usually be one indicating
     * an adapted grid coverage, or zero indicating a raw grid coverage.
     *
     * @return the number of grid coverages which the grid coverage was derived from.
     * @throws RemoteException if a remote method call failed.
     */
    int getNumSources() throws RemoteException;

    /**
     * List of metadata keywords for a coverage.
     * If no metadata is available, the sequence will be empty.
     *
     * @return the list of metadata keywords for a coverage.
     * @throws RemoteException if a remote method call failed.
     */
    String[] getMetadataNames() throws RemoteException;

    /**
     * This specifies the coordinate system used when accessing a coverage or grid
     * coverage with the <code>evaluate</code> methods. It is also the coordinate
     * system of the coordinates used with the math transform (see {@link
     * org.opengis.gc.GC_GridGeometry#getGridToCoordinateSystem gridToCoordinateSystem}).
     *
     * This coordinate system is usually different than the grid coordinate system
     * of the grid. Grid coverage can be accessed (re-projected) with new coordinate
     * system with the {@link org.opengis.gp.GP_GridCoverageProcessor} component.
     * In this case, a new instance of a grid coverage is created.
     * <br><br>
     * Note: If a coverage does not have an associated coordinate system,
     * the returned value will be <code>null</code>.
     * The {@link org.opengis.gc.GC_GridGeometry#getGridToCoordinateSystem
     * gridToCoordinateSystem}) attribute should also be <code>null</code>
     * if the coordinate system is <code>null</code>.
     *
     * @return the coordinate system used when accessing a coverage or
     *         grid coverage with the <code>evaluate</code> methods.
     * @throws RemoteException if a remote method call failed.
     */
    CS_CoordinateSystem getCoordinateSystem() throws RemoteException;

    /**
     * The bounding box for the coverage domain in coordinate system coordinates.
     * For grid coverages, the grid cells are centered on each grid coordinate.
     * The envelope for a 2-D grid coverage includes the following corner positions.
     *
     * <blockquote><pre>
     * (Minimum row - 0.5, Minimum column - 0.5) for the minimum coordinates
     * (Maximum row - 0.5, Maximum column - 0.5) for the maximum coordinates
     * </pre></blockquote>
     *
     * If a grid coverage does not have any associated coordinate system,
     * the minimum and maximum coordinate points for the envelope will be empty sequences.
     *
     * @return the bounding box for the coverage domain in coordinate system coordinates.
     * @throws RemoteException if a remote method call failed.
     */
    PT_Envelope getEnvelope() throws RemoteException;

    /**
     * Retrieve sample dimension information for the coverage.
     * For a grid coverage a sample dimension is a band. The sample dimension information
     * include such things as description, data type of the value (bit, byte, integer...),
     * the no data values, minimum and maximum values and a color table if one is
     * associated with the dimension. A coverage must have at least one sample dimension.
     *
     * @param  index Index for sample dimension to retrieve. Indices are numbered 0 to (n-1).
     * @return Sample dimension information for the coverage.
     * @throws RemoteException if a remote method call failed.
     */
    CV_SampleDimension getSampleDimension(int index) throws RemoteException;

    /**
     * Returns the source data for a grid coverage.
     * If the {@link GC_GridCoverage} was produced from an underlying dataset
     * (by <code>createFromName</code> or <code>createFromSubName</code> for
     * instance) the {@link #getNumSources} method should returns zero, and this
     * method should not be called.
     *
     * If the <code>GC_GridCoverage}</code> was produced using
     * {link org.opengis.gp.GP_GridCoverageProcessor} then it should return the source
     * grid coverage of the one used as input to <code>GP_GridCoverageProcessor</code>.
     * In general the source() method is intended to return the original
     * <code>GC_GridCoverage</code> on which it depends.
     *
     * This is intended to allow applications to establish what <code>GC_GridCoverage</code>s
     * will be affected when others are updated, as well as to trace back to the "raw data".
     *
     * @param sourceDataIndex Source grid coverage index. Indexes start at 0.
     * @return the source data for a grid coverage.
     * @throws RemoteException if a remote method call failed.
     */
    GC_GridCoverage getSource(int sourceDataIndex) throws RemoteException;

    /**
     * Retrieve the metadata value for a given metadata name.
     *
     * @param name Metadata keyword for which to retrieve data.
     * @return the metadata value for a given metadata name.
     * @throws RemoteException if a remote method call failed.
     */
    String getMetadataValue(String name) throws RemoteException;

    /**
     * Return the value vector for a given point in the coverage.
     * A value for each sample dimension is included in the vector.
     * The default interpolation type used when accessing grid values for points
     * which fall between grid cells is nearest neighbor.
     * The coordinate system of the point is the same as the grid coverage coordinate
     * system (specified by the {@link #getCoordinateSystem}).
     *
     * @param point Point at which to find the grid values.
     * @return the value vector for a given point in the coverage.
     * @throws RemoteException if a remote method call failed.
     */
    Object evaluate(PT_CoordinatePoint point) throws RemoteException;

    /**
     * Return a sequence of Boolean values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * The coordinate system of the point is the same as the grid coverage coordinate system.
     *
     * @param point Point at which to find the coverage values.
     * @return a sequence of boolean values for a given point in the coverage.
     * @throws RemoteException if a remote method call failed.
     */
    boolean[] evaluateAsBoolean(PT_CoordinatePoint point) throws RemoteException;

    /**
     * Return a sequence of unsigned byte values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * The coordinate system of the point is the same as the grid coverage coordinate system.
     *
     * @param point Point at which to find the coverage values.
     * @return a sequence of unsigned byte values for a given point in the coverage.
     * @throws RemoteException if a remote method call failed.
     */
    byte[] evaluateAsByte(PT_CoordinatePoint point) throws RemoteException;

    /**
     *  Return a sequence of integer values for a given point in the coverage.
     *  A value for each sample dimension is included in the sequence.
     *  The default interpolation type used when accessing grid values for points which
     *  fall between grid cells is nearest neighbor.
     *  The coordinate system of the point is the same as the grid coverage coordinate system.
     *
     * @param point Point at which to find the grid values.
     * @return a sequence of integer values for a given point in the coverage.
     * @throws RemoteException if a remote method call failed.
     */
    int[] evaluateAsInteger(PT_CoordinatePoint point) throws RemoteException;

    /**
     * Return a sequence of double values for a given point in the coverage.
     *  A value for each sample dimension is included in the sequence.
     *  The default interpolation type used when accessing grid values for points which
     *  fall between grid cells is nearest neighbor.
     *  The coordinate system of the point is the same as the grid coverage coordinate system.
     *
     * @param point Point at which to find the grid values.
     * @return a sequence of double values for a given point in the coverage.
     * @throws RemoteException if a remote method call failed.
     */
    double[] evaluateAsDouble(PT_CoordinatePoint point) throws RemoteException;
}
