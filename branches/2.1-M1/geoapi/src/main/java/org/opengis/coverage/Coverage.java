/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE direct dependencies
import java.util.Set;
import java.util.List;
import java.util.Collection;
import java.awt.image.Raster;  // For Javadoc
import java.awt.image.renderable.RenderableImage;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.spatialschema.geometry.Envelope;
import org.opengis.spatialschema.geometry.Geometry;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.temporal.Period;
import org.opengis.util.Record;
import org.opengis.util.RecordType;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A function from a spatial, temporal or spatiotemporal domain to an attribute range. A coverage
 * associates a {@linkplain DirectPosition position} within its domain to a record of values of
 * defined data types. Examples include a raster image, polygon overlay, or digital elevation matrix.
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
 * <h3>Metadata</h3>
 * The legacy {@linkplain Specification#OGC_01004 OGC 01-004} specification provided some methods for
 * fetching metadata values attached to a coverage. The {@linkplain Specification#ISO_19123 ISO 19123}
 * specification do not provides such methods. Implementations that want to provide such metadata are
 * encouraged to implement the {@link javax.media.jai.PropertySource} or
 * {@link javax.media.jai.WritablePropertySource} interface.
 *
 * @author Stephane Fellah
 * @author Martin Desruisseaux
 * @author Wim Koolhoven
 * @author Alexander Petkov
 */
@UML(identifier="CV_Coverage", specification=ISO_19123)
public interface Coverage {
	/**
     * Returns the coordinate reference system to which the objects in its domain are referenced.
     * This is the CRS used when accessing a coverage or grid coverage with the {@code evaluate(...)}
     * methods. It is also the coordinate reference system of the coordinates used with the math
     * transform (see {@link org.opengis.coverage.grid.GridGeometry#getGridToCoordinateSystem
     * gridToCoordinateSystem}).
     * <p>
     * This coordinate reference system is usually different than coordinate system of the grid.
     * Grid coverage can be accessed (re-projected) with new coordinate reference system with the
     * {@link org.opengis.coverage.processing.GridCoverageProcessor} component. In this case, a new
     * instance of a grid coverage is created.
     *
     * @return The coordinate reference system used when accessing a coverage or
     *         grid coverage with the {@code evaluate(...)} methods.
     */
    @UML(identifier="CRS", obligation=MANDATORY, specification=ISO_19123)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * The bounding box for the coverage domain in {@linkplain #getCoordinateReferenceSystem
     * coordinate reference system} coordinates. For grid coverages, the grid cells are centered
     * on each grid coordinate. The envelope for a 2-D grid coverage includes the following corner
     * positions.
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
     *
     * @deprecated No replacement.
     */
    @UML(identifier="envelope", obligation=MANDATORY, specification=OGC_01004)
    Envelope getEnvelope();

    /**
     * Returns the set of domain objects in the domain.
     * The collection must contains at least one element.
     */
    @UML(identifier="domainElement", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends DomainObject> getDomainElements();

    /**
     * Returns the extent of the domain of the coverage. Extents may be specified in space,
     * time or space-time. The collection must contains at least one element.
     */
    @UML(identifier="domainExtent", obligation=MANDATORY, specification=ISO_19123)
    Set<Extent> getDomainExtents();

    /**
     * Returns the set of attribute values in the range. The range of a coverage shall be a
     * homogeneous collection of records. That is, the range shall have a constant dimension
     * over the entire domain, and each field of the record shall provide a value of the same
     * attribute type over the entire domain.
     * <p>
     * In the case of a {@linkplain DiscreteCoverage discrete coverage}, the size of the range
     * collection equals that of the {@linkplain #getDomainElements domains} collection. In other
     * words, there is one instance of {@link AttributeValues} for each instance of {@link DomainObject}.
     * Usually, these are stored values that are accessed by the
     * {@link #evaluate(DirectPosition,Collection) evaluate} operation.
     * <p>
     * In the case of a {@linkplain ContinuousCoverage continuous coverage}, there is a transfinite
     * number of instances of {@link AttributeValues} for each {@link DomainObject}. A few instances
     * may be stored as input for the {@link #evaluate(DirectPosition,Collection) evaluate} operation,
     * but most are generated as needed by that operation.
     * <p>
     * <B>NOTE:</B> ISO 19123 does not specify how the {@linkplain #getDomainElements domain}
     * and {@linkplain #getRangeElements range} associations are to be implemented. The relevant
     * data may be generated in real time, it may be held in persistent local storage, or it may
     * be electronically accessible from remote locations.
     */
    @UML(identifier="rangeElement", obligation=OPTIONAL, specification=ISO_19123)
    Set<AttributeValues> getRangeElements();

    /**
     * Describes the range of the coverage. It consists of a list of attribute name/data type pairs.
     * A simple list is the most common form of range type, but {@code RecordType} can be used
     * recursively to describe more complex structures. The range type for a specific coverage
     * shall be specified in an application schema.
     */
    @UML(identifier="rangeType", obligation=MANDATORY, specification=ISO_19123)
    RecordType getRangeType();

    /**
     * Identifies the procedure to be used for evaluating the coverage at a position that falls
     * either on a boundary between geometric objects or within the boundaries of two or more
     * overlapping geometric objects. The geometric objects are either {@linkplain DomainObject
     * domain objects} or {@linkplain ValueObject value objects}.
     */
    @UML(identifier="commonPointRule", obligation=MANDATORY, specification=ISO_19123)
    CommonPointRule getCommonPointRule();

    /**
     * Returns the dictionary of <var>geometry</var>-<var>value</var> pairs that contain the
     * {@linkplain DomainObject objects} in the domain of the coverage each paired with its
     * record of feature attribute values. In the case of an analytical coverage, the operation
     * shall return the empty set.
     *
     * @todo Consider using a Map. May force a renaming of this method.
     */
    @UML(identifier="list", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends GeometryValuePair> list();

    /**
     * Returns the set of <var>geometry</var>-<var>value</var> pairs that contain
     * {@linkplain DomainObject domain objects} that lie within the specified geometry and period.
     * If {@code s} is null, the operation shall return all <var>geometry</var>-<var>value</var>
     * pairs that contain {@linkplain DomainObject domain objects} within {@code t}. If the value
     * of {@code t} is null, the operation shall return all <var>geometry</var>-<var>value</var>
     * pair that contain {@linkplain DomainObject domain objects} within {@code s}. In the case
     * of an analytical coverage, the operation shall return the empty set.
     *
     * @todo Consider using a Map as return type.
     */
    @UML(identifier="select", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends GeometryValuePair> select(Geometry s, Period t);

    /**
     * Returns the sequence of <var>geometry</var>-<var>value</var> pairs that include the
     * {@linkplain DomainObject domain objects} nearest to the direct position and their distances
     * from the direction position. The sequence shall be ordered by distance from the direct position,
     * beginning with the record containing the {@linkplain DomainObject domain object} nearest to the
     * direct position. The length of the sequence (the number of <var>geometry</var>-<var>value</var>
     * pairs returned) shall be no greater than the number specified by the parameter {@code limit}.
     * The default shall be to return a single <var>geometry</var>-<var>value</var> pair. The operation
     * shall return a warning if the last {@linkplain DomainObject domain object} in the sequence is at
     * a distance from the direct position equal to the distance of other {@linkplain DomainObject domain
     * objects} that are not included in the sequence. In the case of an analytical coverage, the operation
     * shall return the empty set.
     * <p>
     * <B>NOTE:</B> This operation is useful when the domain of a coverage does not exhaustively
     * partition the extent of the coverage. Even in that case, the first element of the sequence
     * returned may be the <var>geometry</var>-<var>value</var> pair that contains the input direct
     * position.
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    List<? extends GeometryValuePair> find(DirectPosition p, int limit);

    /**
     * Returns the nearest <var>geometry</var>-<var>value</var> pair from the specified direct
     * position. This is a shortcut for <code>{@linkplain #find(DirectPosition,int) find}(p,1)</code>.
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    GeometryValuePair find(DirectPosition p);

    /**
     * Returns a set of records of feature attribute values for the specified direct position. The
     * parameter {@code list} is a sequence of feature attribute names each of which identifies a
     * field of the range type. If {@code list} is null, the operation shall return a value for
     * every field of the range type. Otherwise, it shall return a value for each field included in
     * {@code list}. If the direct position passed is not in the domain of the coverage, then an
     * exception is thrown. If the input direct position falls within two or more geometric objects
     * within the domain, the operation shall return records of feature attribute values computed
     * according to the {@linkplain #getCommonPointRule common point rule}.
     * <P>
     * <B>NOTE:</B> Normally, the operation will return a single record of feature attribute values.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set<Record> evaluate(DirectPosition p, Set<String> list);

    /**
     * Return the value vector for a given point in the coverage.
     * A value for each sample dimension is included in the vector.
     * The default interpolation type used when accessing grid values for points
     * which fall between grid cells is nearest neighbor.
     * <p>
     * The coordinate reference system of the point is the same as the grid coverage coordinate
     * reference system (specified by the {@link #getCoordinateReferenceSystem} method).
     *
     * @param point Point at which to find the grid values.
     * @return The value vector for a given point in the coverage.
     * @throws PointOutsideCoverageException if the point is outside the coverage
     *         {@linkplain #getEnvelope envelope}.
     * @throws CannotEvaluateException If the point can't be evaluate for some other reason.
     * @see Raster#getDataElements(int, int, Object)
     *
     * The original return type (Object) is replaced by something that returns a set (The ISO 19123
     * implementation will always return a set).
     * 
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=OGC_01004)
    Set evaluate(DirectPosition point) throws CannotEvaluateException;

    /**
     * Return a sequence of boolean values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * <p>
     * The coordinate reference system of the point is the same as the grid coverage coordinate
     * reference system (specified by the {@link #getCoordinateReferenceSystem} method).
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
     *
     * @deprecated No replacement.
     * @todo Consider keeping this method as undeprecated.
     */
    @UML(identifier="evaluateAsBoolean", obligation=MANDATORY, specification=OGC_01004)
    boolean[] evaluate(DirectPosition point, boolean[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of unsigned byte values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * <p>
     * The coordinate reference system of the point is the same as the grid coverage coordinate
     * reference system (specified by the {@link #getCoordinateReferenceSystem} method).
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
     *
     * @deprecated No replacement.
     * @todo Consider keeping this method as undeprecated.
     */
    @UML(identifier="evaluateAsByte", obligation=MANDATORY, specification=OGC_01004)
    byte[] evaluate(DirectPosition point, byte[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of integer values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * <p>
     * The coordinate reference system of the point is the same as the grid coverage coordinate
     * reference system (specified by the {@link #getCoordinateReferenceSystem} method).
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
     *
     * @deprecated No replacement.
     * @todo Consider keeping this method as undeprecated.
     */
    @UML(identifier="evaluateAsInteger", obligation=MANDATORY, specification=OGC_01004)
    int[] evaluate(DirectPosition point, int[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of float values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * <p>
     * The coordinate reference system of the point is the same as the grid coverage coordinate
     * reference system (specified by the {@link #getCoordinateReferenceSystem} method).
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
     *
     * @deprecated No replacement.
     * @todo Consider keeping this method as undeprecated.
     */
    float[] evaluate(DirectPosition point, float[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;

    /**
     * Return a sequence of double values for a given point in the coverage.
     * A value for each sample dimension is included in the sequence.
     * The default interpolation type used when accessing grid values for points which
     * fall between grid cells is nearest neighbor.
     * <p>
     * The coordinate reference system of the point is the same as the grid coverage coordinate
     * reference system (specified by the {@link #getCoordinateReferenceSystem} method).
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
     *
     * @deprecated No replacement.
     * @todo Consider keeping this method as undeprecated.
     */
    @UML(identifier="evaluateAsDouble", obligation=MANDATORY, specification=OGC_01004)
    double[] evaluate(DirectPosition point, double[] destination)
            throws CannotEvaluateException, ArrayIndexOutOfBoundsException;

    /**
     * Returns a set of {@linkplain DomainObject domain objects} for the specified record of feature
     * attribute values. Normally, this method returns the set of {@linkplain DomainObject objects}
     * in the domain that are associated with values equal to those in the input record. However,
     * the operation may return other {@linkplain DomainObject objects} derived from those in the
     * domain, as specified by the application schema.
     * <p>
     * <B>Example:</B> The {@code evaluateInverse} operation could return a set
     * of contours derived from the feature attribute values associated with the
     * {@linkplain org.opengis.coverage.grid.GridPoint grid points} of a grid coverage.
     */
    @UML(identifier="evaluateInverse", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends DomainObject> evaluateInverse(Record v);

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
     * @deprecated No replacement.
     */
    @UML(identifier="dimensionNames", obligation=MANDATORY, specification=OGC_01004)
    InternationalString[] getDimensionNames();

    /**
     * The number of sample dimensions in the coverage.
     * For grid coverages, a sample dimension is a band.
     *
     * @return The number of sample dimensions in the coverage.
     *
     * @deprecated No replacement.
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
     *
     * @deprecated No replacement.
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
     *
     * @deprecated No replacement.
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
     *
     * @deprecated Replaced by {@link javax.media.jai.PropertySource#getPropertyNames()}.
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
     *
     * @deprecated Replaced by {@link javax.media.jai.PropertySource#getProperty}.
     */
    @UML(identifier="getMetadataValue", obligation=MANDATORY, specification=OGC_01004)
    String getMetadataValue(String name) throws MetadataNameNotFoundException;

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
     *
     * @deprecated No replacement.
     * @todo Consider keeping this method as undeprecated.
     */
    RenderableImage getRenderableImage(int xAxis, int yAxis)
            throws UnsupportedOperationException, IndexOutOfBoundsException;
}
