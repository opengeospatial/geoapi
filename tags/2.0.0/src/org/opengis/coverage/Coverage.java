/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE direct dependencies and extensions
import java.util.List;
import java.util.Locale;
import java.awt.image.Raster;  // For Javadoc
import java.awt.image.renderable.RenderableImage;

// OpenGIS direct dependencies
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.Envelope;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides access to a coverage.
 * The essential property of coverage is to be able to generate a value for any point
 * within its domain. How coverage is represented internally is not a concern.
 *
 * For example consider the following different internal representations of coverage:<br>
 *  <UL>
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
 * </UL>
 *
 * A coverage has a corresponding {@link SampleDimension} for each sample
 * dimension in the coverage.
 * <p>
 * <STRONG>Implementation note:</STRONG><BR>
 * We expect that many implementations of {@link org.opengis.coverage.grid.GridCoverage} will want
 * to leverage the rich set of <A HREF="http://java.sun.com/products/java-media/jai/">Java Advanced
 * Imaging (JAI)</A> features. For those implementations, it is recommended (but not required) to
 * implement the {@link javax.media.jai.PropertySource} interface as well. In this case, implementation
 * of {@link javax.media.jai.PropertySource} methods must be consistent with {@link #getMetadataNames}
 * and {@link #getMetadataValue} methods.
 *
 * <P>&nbsp;</P>
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
 *   <TR><TD>
 *     <P align="justify"><STRONG>WARNING: THIS CLASS WILL CHANGE.</STRONG> Current API is derived from OGC
 *     <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</A>.
 *     We plan to replace it by new interfaces derived from ISO 19123 (<CITE>Schema for coverage geometry
 *     and functions</CITE>). Current interfaces should be considered as legacy and are included in this
 *     distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much 
 *     compatibility as possible, but no migration plan has been determined yet.</P>
 *   </TD></TR>
 * </TABLE>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see RenderableImage
 * @see javax.media.jai.ImageFunction
 */
@UML(identifier="CV_Coverage", specification=OGC_01004)
public interface Coverage {
    /**
     * Specifies the coordinate reference system used when accessing a coverage or grid
     * coverage with the {@code evaluate(...)} methods. It is also the coordinate
     * reference system of the coordinates used with the math transform (see
     * {@link org.opengis.coverage.grid.GridGeometry#getGridToCoordinateSystem
     * gridToCoordinateSystem}).
     *
     * This coordinate reference system is usually different than coordinate system of the grid.
     * Grid coverage can be accessed (re-projected) with new coordinate reference system with the
     * {@link org.opengis.coverage.processing.GridCoverageProcessor} component. In this case, a new instance of a
     * grid coverage is created.
     * <p>
     * Note: If a coverage does not have an associated coordinate reference system,
     * the returned value will be {@code null}.
     * The {@link org.opengis.coverage.grid.GridGeometry#getGridToCoordinateSystem gridToCoordinateSystem})
     * attribute should also be {@code null} if the coordinate reference system is {@code null}.
     *
     * @return The coordinate reference system used when accessing a coverage or
     *         grid coverage with the {@code evaluate(...)} methods, or {@code null}.
     */
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=OGC_01004)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * The bounding box for the coverage domain in {@linkplain #getCoordinateReferenceSystem coordinate reference system}
     * coordinates. For grid coverages, the grid cells are centered on each grid coordinate.
     * The envelope for a 2-D grid coverage includes the following corner positions.
     *
     * <blockquote><pre>
     * (Minimum row - 0.5, Minimum column - 0.5) for the minimum coordinates
     * (Maximum row - 0.5, Maximum column - 0.5) for the maximum coordinates
     * </pre></blockquote>
     *
     * If a grid coverage does not have any associated coordinate reference system,
     * the minimum and maximum coordinate points for the envelope will be empty sequences.
     *
     * @return The bounding box for the coverage domain in coordinate system coordinates.
     */
    @UML(identifier="envelope", obligation=MANDATORY, specification=OGC_01004)
    Envelope getEnvelope();

    /**
     * The names of each dimension in the coverage.
     * Typically these names are <var>x</var>, <var>y</var>, <var>z</var> and <var>t</var>.
     * The number of items in the sequence is the number of dimensions in the coverage.
     * Grid coverages are typically 2D (<var>x</var>, <var>y</var>) while other coverages
     * may be 3D (<var>x</var>, <var>y</var>, <var>z</var>) or
     * 4D (<var>x</var>, <var>y</var>, <var>z</var>, <var>t</var>).
     * The number of dimensions of the coverage is the number of entries in the
     * list of dimension names.
     */
    @UML(identifier="dimensionNames", obligation=MANDATORY, specification=OGC_01004)
    InternationalString[] getDimensionNames();

    /**
     * The number of sample dimensions in the coverage.
     * For grid coverages, a sample dimension is a band.
     *
     * @return The number of sample dimensions in the coverage.
     */
    @UML(identifier="numSampleDimensions", obligation=MANDATORY, specification=OGC_01004)
    int getNumSampleDimensions();

    /**
     * Retrieve sample dimension information for the coverage.
     * For a grid coverage a sample dimension is a band. The sample dimension information
     * include such things as description, data type of the value (bit, byte, integer...),
     * the no data values, minimum and maximum values and a color table if one is
     * associated with the dimension. A coverage must have at least one sample dimension.
     *
     * @param  index Index for sample dimension to retrieve. Indices are numbered 0 to
     *         (<var>{@linkplain #getNumSampleDimensions n}</var>-1).
     * @return Sample dimension information for the coverage.
     * @throws IndexOutOfBoundsException if {@code index} is out of bounds.
     */
    @UML(identifier="getSampleDimension", obligation=MANDATORY, specification=OGC_01004)
    SampleDimension getSampleDimension(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the sources data for a coverage.
     * This is intended to allow applications to establish what {@code Coverage}s
     * will be affected when others are updated, as well as to trace back to the "raw data".
     *
     * This implementation specification does not include interfaces for creating
     * collections of coverages therefore the list size will usually be one indicating
     * an adapted grid coverage, or zero indicating a raw grid coverage.
     *
     * @return The list of sources data for a coverage.
     */
    @UML(identifier="getSource, numSource", obligation=MANDATORY, specification=OGC_01004)
    List<? extends Coverage> getSources();

    /**
     * List of metadata keywords for a coverage.
     * If no metadata is available, the sequence will be empty.
     *
     * @return the list of metadata keywords for a coverage.
     *
     * @see #getMetadataValue
     * @see javax.media.jai.PropertySource#getPropertyNames
     */
    @UML(identifier="metadataNames", obligation=MANDATORY, specification=OGC_01004)
    String[] getMetadataNames();

    /**
     * Retrieve the metadata value for a given metadata name.
     *
     * @param name Metadata keyword for which to retrieve data.
     * @return the metadata value for a given metadata name.
     * @throws MetadataNameNotFoundException if there is no value for the specified metadata name.
     *
     * @see #getMetadataNames
     * @see javax.media.jai.PropertySource#getProperty
     */
    @UML(identifier="getMetadataValue", obligation=MANDATORY, specification=OGC_01004)
    String getMetadataValue(String name) throws MetadataNameNotFoundException;

    /**
     * Return the value vector for a given point in the coverage.
     * A value for each sample dimension is included in the vector.
     * The default interpolation type used when accessing grid values for points
     * which fall between grid cells is nearest neighbor.
     * The coordinate system of the point is the same as the grid coverage coordinate
     * reference system (specified by the {@link #getCoordinateReferenceSystem} method).
     *
     * @param point Point at which to find the grid values.
     * @return The value vector for a given point in the coverage.
     * @throws PointOutsideCoverageException if the point is outside the coverage
     *         {@linkplain #getEnvelope envelope}.
     * @throws CannotEvaluateException If the point can't be evaluate for some othe reason.
     *
     * @see Raster#getDataElements(int, int, Object)
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=OGC_01004)
    Object evaluate(DirectPosition point) throws CannotEvaluateException;

    /**
     * Return a sequence of boolean values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * The coordinate reference system of the point is the same as the grid coverage
     * {@linkplain #getCoordinateReferenceSystem coordinate reference system}.
     *
     * @param  point Point at which to find the coverage values.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return A sequence of boolean values for a given point in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws PointOutsideCoverageException if the point is outside the coverage
     *         {@linkplain #getEnvelope envelope}.
     * @throws CannotEvaluateException if the point can't be evaluate for some othe reason.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     */
    @UML(identifier="evaluateAsBoolean", obligation=MANDATORY, specification=OGC_01004)
    boolean[] evaluate(DirectPosition point, boolean[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of unsigned byte values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * The coordinate reference system of the point is the same as the grid coverage
     * {@linkplain #getCoordinateReferenceSystem coordinate reference system}.
     *
     * @param point Point at which to find the coverage values.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return A sequence of unsigned byte values for a given point in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws PointOutsideCoverageException if the point is outside the coverage
     *         {@linkplain #getEnvelope envelope}.
     * @throws CannotEvaluateException if the point can't be evaluate for some othe reason.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     */
    @UML(identifier="evaluateAsByte", obligation=MANDATORY, specification=OGC_01004)
    byte[] evaluate(DirectPosition point, byte[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of integer values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * The coordinate reference system of the point is the same as the grid coverage
     * {@linkplain #getCoordinateReferenceSystem coordinate reference system}.
     *
     * @param point Point at which to find the grid values.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return A sequence of integer values for a given point in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws PointOutsideCoverageException if the point is outside the coverage
     *         {@linkplain #getEnvelope envelope}.
     * @throws CannotEvaluateException if the point can't be evaluate for some othe reason.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     *
     * @see Raster#getPixel(int, int, int[])
     */
    @UML(identifier="evaluateAsInteger", obligation=MANDATORY, specification=OGC_01004)
    int[] evaluate(DirectPosition point, int[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of float values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * The coordinate reference system of the point is the same as the grid coverage
     * {@linkplain #getCoordinateReferenceSystem coordinate reference system}.
     *
     * @param point Point at which to find the grid values.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return A sequence of float values for a given point in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws PointOutsideCoverageException if the point is outside the coverage
     *         {@linkplain #getEnvelope envelope}.
     * @throws CannotEvaluateException if the point can't be evaluate for some othe reason.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     *
     * @see Raster#getPixel(int, int, float[])
     */
    float[] evaluate(DirectPosition point, float[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of double values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * The coordinate reference system of the point is the same as the grid coverage
     * {@linkplain #getCoordinateReferenceSystem coordinate reference system}.
     *
     * @param point Point at which to find the grid values.
     * @param  destination An optionally preallocated array in which to store the values,
     *         or {@code null} if none.
     * @return A sequence of double values for a given point in the coverage.
     *         If {@code destination} was non-null, then it is returned.
     *         Otherwise, a new array is allocated and returned.
     * @throws PointOutsideCoverageException if the point is outside the coverage
     *         {@linkplain #getEnvelope envelope}.
     * @throws CannotEvaluateException If the point can't be evaluate for some othe reason.
     * @throws ArrayIndexOutOfBoundsException if the {@code destination} array is not null
     *         and too small to hold the output.
     *
     * @see Raster#getPixel(int, int, double[])
     */
    @UML(identifier="evaluateAsDouble", obligation=MANDATORY, specification=OGC_01004)
    double[] evaluate(DirectPosition point, double[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;
    
    /**
     * Returns 2D view of this coverage as a renderable image.
     * This optional operation allows interoperability with
     * <A HREF="http://java.sun.com/products/java-media/2D/">Java2D</A>.
     * If this coverage is a {@link org.opengis.coverage.grid.GridCoverage} backed
     * by a {@link java.awt.image.RenderedImage}, the underlying image can be obtained
     * with:
     *
     * <code>getRenderableImage(0,1).{@linkplain RenderableImage#createDefaultRendering()
     * createDefaultRendering()}</code>
     *
     * @param  xAxis Dimension to use for the <var>x</var> axis.
     * @param  yAxis Dimension to use for the <var>y</var> axis.
     * @return A 2D view of this coverage as a renderable image.
     * @throws UnsupportedOperationException if this optional operation is not supported.
     * @throws IndexOutOfBoundsException if {@code xAxis} or {@code yAxis} is out of bounds.
     */
    RenderableImage getRenderableImage(int xAxis, int yAxis)
            throws UnsupportedOperationException, IndexOutOfBoundsException;
}
