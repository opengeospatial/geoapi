/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example;

import java.util.Map;
import java.awt.geom.RectangularShape;
import org.opengis.util.NameFactory;
import org.opengis.util.GenericName;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransformFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.RangeMeaning;
import org.opengis.coordinate.MismatchedDimensionException;
import org.opengis.coordinate.MismatchedCoordinateMetadataException;
import org.opengis.annotation.UML;
import org.opengis.annotation.Specification;
import org.opengis.example.util.SimpleNameFactory;
import org.opengis.example.geometry.SimpleEnvelope;
import org.opengis.example.geometry.SimpleDirectPosition;
import org.opengis.example.metadata.MetadataProxyFactory;
import org.opengis.example.metadata.SimpleCitation;
import org.opengis.example.metadata.SimpleIdentifier;
import org.opengis.example.metadata.SimpleGeographicBoundingBox;
import org.opengis.example.referencing.SimpleAuthorityFactory;
import org.opengis.example.referencing.SimpleTransformFactory;
import org.opengis.example.referencing.SimpleAxis;


/**
 * Provides simple factories backed by the example implementations.
 * This class is provided only for illustrative purposes with simple implementations.
 * <strong>This class is not for use in production.</strong>
 * For real applications, use a library implementing GeoAPI or providing GeoAPI wrappers.
 *
 * @see Specification#ISO_19115
 * @see Specification#ISO_19103
 */
public final class SimpleFactories {
    /**
     * Do not allow instantiation of this class.
     */
    private SimpleFactories() {
    }

    /**
     * Creates a new citation having the given title.
     * This citation can be used for defining the authority of a set of identifiers.
     *
     * @param  title  the citation title.
     * @return a citation with the given title.
     */
    public static Citation createCitation(String title) {
        return new SimpleCitation(title);
    }

    /**
     * Creates a new identifier of the given authority and code.
     *
     * @param  authority  the authority, or {@code null} if none.
     * @param  code       the identifier code.
     * @return identifier of the given code and (optionally) authority.
     */
    public static Identifier createIdentifier(Citation authority, String code) {
        return new SimpleIdentifier(authority, code);
    }

    /**
     * Creates a new direct position of the given dimension.
     * The Coordinate Reference System is left unspecified.
     * All coordinate values are initialized to zero.
     *
     * @param  dimension  the dimension.
     * @return a new direct position of the given number of dimensions.
     */
    public static DirectPosition createPosition(final int dimension) {
        return new SimpleDirectPosition(dimension);
    }

    /**
     * Creates a new direct position associated to the given coordinate reference system.
     * The {@linkplain DirectPosition#getDimension() dimension} of the new direct position
     * is the dimension of the given CRS. All coordinate values are initialized to zero.
     *
     * @param crs  the coordinate reference system to associate to the new direct position.
     * @return a new direct position associated to the given <abbr>CRS</abbr>.
     */
    public static DirectPosition createPosition(CoordinateReferenceSystem crs) {
        return new SimpleDirectPosition(crs);
    }

    /**
     * Creates a new envelope defined by two direct positions.
     * The CRS of the envelope will be the CRS of the given direct positions, which shall be the equal.
     * The coordinates of the given direct positions are copied.
     *
     * <p>This simple implementation does not support envelopes crossing the anti-meridian.
     * Therefore, lower coordinate values shall not be greater than corresponding upper coordinate values.</p>
     *
     * @param  lowerCorner  the limits in the direction of decreasing coordinate values for each dimension.
     * @param  upperCorner  the limits in the direction of increasing coordinate values for each dimension.
     * @return the envelope defined by the given corners.
     * @throws MismatchedCoordinateMetadataException if the CRS of the two position are not equal.
     * @throws MismatchedDimensionException if the two positions do not have the same dimension.
     * @throws IllegalArgumentException if an coordinate value in the lower corner is greater than
     *         the corresponding coordinate value in the upper corner.
     */
    public static Envelope createEnvelope(DirectPosition lowerCorner, DirectPosition upperCorner) {
        return new SimpleEnvelope(lowerCorner, upperCorner);
    }

    /**
     * Creates a geographic bounding box from the specified rectangular area.
     * The coordinate values are assumed in WGS84 <abbr>CRS</abbr>.
     *
     * @param bounds  the rectangle to use for the geographic bounding box.
     * @return a geographic bounding box with the coordinates of the given rectangle.
     * @throws IllegalArgumentException if (<var>west bound</var> &gt; <var>east bound</var>)
     *         or (<var>south bound</var> &gt; <var>north bound</var>).
     *         Note that {@linkplain Double#NaN NaN} values are allowed.
     */
    public static GeographicBoundingBox createGeographicBoundingBox(RectangularShape bounds) {
        return new SimpleGeographicBoundingBox(bounds);
    }

    /**
     * Creates a new implementation of the given metadata interface which will contains the values in the given map.
     * The metadata values are specified by a {@link Map} of attributes in which keys are {@linkplain UML#identifier()
     * UML identifiers}, and values must be assignable to the return value of the corresponding GeoAPI methods.
     *
     * <p><b>Example:</b> create an {@code Individual} instance:</p>
     *
     * {@snippet lang="java" :
     * var attributes = new HashMap<String,Object>();
     * attributes.put("name", getNameFactory().createInternationalString("Aristotle"));
     * Individual party = createMetadataView(Individual.class, attributes);
     * }
     *
     * The returned metadata is <i>live</i>, i.e. any change to the given map of attributes
     * will be immediately reflected in the values returned by the metadata object.
     *
     * @param  <T>         the compile-time type of the {@code type} argument.
     * @param  type        the metadata interface for which to get an instance.
     * @param  attributes  the attribute values to give to the metadata instance.
     * @return a metadata object which will fetch the values in the given map.
     * @throws IllegalArgumentException if the given type is not an interface
     *         from the GeoAPI metadata package.
     */
    public <T> T createMetadataView(Class<T> type, Map<String,?> attributes) {
        return MetadataProxyFactory.INSTANCE.create(type, attributes);
    }

    /**
     * Creates a new axis for the given authority, name and abbreviation.
     * This is a simplified constructor which infers {@linkplain AxisDirection axis direction}
     * and units from the abbreviation using the following table.
     *
     * <blockquote><table class="ogc">
     * <caption>Axis properties inferred from the abbreviation</caption>
     * <tr><th>Symbol</th>   <th>Common name</th>    <th>Axis direction</th> <th>Unit</th>  <th>Range</th></tr>
     * <tr><td>λ</td> <td>geodetic longitude</td>    <td>east</td>           <td>degree</td><td>±90°  ({@link RangeMeaning#EXACT})</td></tr>
     * <tr><td>φ</td> <td>geodetic latitude</td>     <td>north</td>          <td>degree</td><td>±180° ({@link RangeMeaning#WRAPAROUND})</td></tr>
     * <tr><td>h</td> <td>ellipsoidal height</td>    <td>up</td>             <td>metre</td> <td></td></tr>
     * <tr><td>H</td> <td>gravity-related height</td><td>up</td>             <td>metre</td> <td></td></tr>
     * <tr><td>d</td> <td>depth</td>                 <td>down</td>           <td>metre</td> <td></td></tr>
     * <tr><td>r</td> <td>geocentric radius</td>     <td>up</td>             <td>metre</td> <td>[0…∞]</td></tr>
     * <tr><td>Ω</td> <td>spherical longitude</td>   <td>east</td>           <td>degree</td><td>±90°  ({@link RangeMeaning#EXACT})</td></tr>
     * <tr><td>Θ</td> <td>spherical latitude</td>    <td>north</td>          <td>degree</td><td>±180° ({@link RangeMeaning#WRAPAROUND})</td></tr>
     * <tr><td>X</td> <td>geocentric X</td>          <td>geocentric X</td>   <td>metre</td> <td></td></tr>
     * <tr><td>Y</td> <td>geocentric Y</td>          <td>geocentric Y</td>   <td>metre</td> <td></td></tr>
     * <tr><td>Z</td> <td>geocentric Z</td>          <td>geocentric Z</td>   <td>metre</td> <td></td></tr>
     * <tr><td>E</td> <td>easting</td>               <td>east</td>           <td>metre</td> <td></td></tr>
     * <tr><td>W</td> <td>westing</td>               <td>west</td>           <td>metre</td> <td></td></tr>
     * <tr><td>N</td> <td>northing</td>              <td>north</td>          <td>metre</td> <td></td></tr>
     * <tr><td>S</td> <td>southing</td>              <td>south</td>          <td>metre</td> <td></td></tr>
     * <tr><td>t</td> <td>time</td>                  <td>future</td>         <td>second</td><td></td></tr>
     * <tr><td>i</td> <td>column</td>                <td>column positive</td><td>unity</td> <td></td></tr>
     * <tr><td>j</td> <td>row</td>                   <td>row positive</td>   <td>unity</td> <td></td></tr>
     * </table></blockquote>
     *
     * @param  authority     organization responsible for definition of the name, or {@code null}.
     * @param  name          the name of the new axis.
     * @param  abbreviation  the abbreviation used for this coordinate system axes.
     * @return a coordinate system axis of the given name and abbreviation.
     * @throws IllegalArgumentException if the abbreviation is not recognized.
     */
    public static CoordinateSystemAxis createAxis(Citation authority, String name, char abbreviation) {
        return new SimpleAxis(authority, name, abbreviation);
    }

    /**
     * Returns a factory for creating {@code GenericName} instances.
     * This factory provides methods for constructing
     * {@link org.opengis.util.InternationalString},
     * {@link org.opengis.util.GenericName},
     * {@link org.opengis.util.LocalName},
     * {@link org.opengis.util.ScopedName},
     * {@link org.opengis.util.TypeName} and
     * {@link org.opengis.util.MemberName}.
     *
     * @return a factory for creating instances of {@link GenericName}.
     */
    public static NameFactory getNameFactory() {
        return SimpleNameFactory.provider();
    }

    /**
     * Returns a factory for creating {@code CoordinateReferenceSystem} instances.
     * The simple factory provided as an example defines only the following codes:
     *
     * <blockquote><table class="ogc">
     * <caption>CRS codes defined by "GeoAPI example" pseudo-authority</caption>
     * <tr><th>Code</th>        <th>Type</th>       <th>Name</th></tr>
     * <tr><td>4326</td>        <td>Geographic</td> <td>WGS 84</td></tr>
     * <tr><td>4047</td>        <td>Geographic</td> <td>GRS 1980 Authalic Sphere</td></tr>
     * <tr><td>5714</td>        <td>Vertical</td>   <td>Mean Sea Level (MSL) height</td></tr>
     * <tr><td>JulianDate</td>  <td>Temporal</td>   <td>Julian Date</td></tr>
     * </table></blockquote>
     *
     * @return a factory for creating a few {@code CoordinateReferenceSystem} instances.
     */
    public static CRSAuthorityFactory getCRSAuthorityFactory() {
        return SimpleAuthorityFactory.provider();
    }

    /**
     * Returns a factory for creating {@code MathTransformFactory} instances.
     * The simple factory provided as an example can create only linear transforms.
     *
     * @return a factory for creating linear instances of {@link MathTransform}.
     */
    public static MathTransformFactory getMathTransformFactory() {
        return SimpleTransformFactory.provider();
    }
}
