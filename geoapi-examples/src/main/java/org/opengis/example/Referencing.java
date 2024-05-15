/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example;

import javax.measure.Unit;
import org.opengis.annotation.Specification;
import org.opengis.metadata.citation.Citation;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.DirectPosition;
import org.opengis.coordinate.MismatchedDimensionException;
import org.opengis.coordinate.MismatchedCoordinateMetadataException;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.RangeMeaning;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransformFactory;
import org.opengis.example.geometry.SimpleDirectPosition;
import org.opengis.example.geometry.SimpleEnvelope;
import org.opengis.example.referencing.SimpleTransformFactory;
import org.opengis.example.referencing.SimpleCRS;
import org.opengis.example.referencing.SimpleAxis;


/**
 * Provides a few CRS definitions and methods related to referencing services.
 * This class is provided only for illustrative purposes with simple implementations.
 * <strong>This class is not for use in production.</strong>
 * For real applications, use a library implementing GeoAPI or providing GeoAPI wrappers.
 *
 * @see Specification#ISO_19111
 * @see Specification#OGC_01009
 */
public final class Referencing {
    /**
     * Do not allow instantiation of this class.
     */
    private Referencing() {
    }

    /**
     * The WGS84 CRS, based on EPSG:4326. The axis order is (φ,λ).
     *
     * @return WGS84.
     */
    public static GeographicCRS WGS84() {
        return SimpleCRS.Geographic.WGS84;
    }

    /**
     * A spherical CRS, used when the datum is unknown, based on EPSG:4047.
     * The axis order is (φ,λ).
     *
     * @return a spherical CRS.
     */
    public static final GeographicCRS sphere() {
        return SimpleCRS.Geographic.SPHERE;
    }

    /**
     * A vertical CRS for mean sea level, based on EPSG:5714.
     * The axis is (H).
     *
     * @return a Mean Sea Level CRS.
     */
    public static final VerticalCRS meanSeaLevel() {
        return SimpleCRS.Vertical.MSL;
    }

    /**
     * A temporal CRS for Julian date, based on OGC:JulianDate.
     *
     * @return a Julian Date CRS.
     */
    public static final TemporalCRS julianDate() {
        return SimpleCRS.Temporal.JULIAN;
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
     * <tr><td>Ω</td> <td>spherical longitude</td>   <td>east</td>           <td>degree</td><td>±90° ({@link RangeMeaning#EXACT})</td></tr>
     * <tr><td>Θ</td> <td>spherical latitude</td>    <td>north</td>          <td>degree</td><td>±180°({@link RangeMeaning#WRAPAROUND})</td></tr>
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
     * Creates a new axis for the given authority, name, units and direction.
     * The following abbreviation are handled specially for determining the range:
     *
     * <ul>
     *   <li>λ for geodetic  longitude</li>
     *   <li>φ for geodetic  latitude</li>
     *   <li>Ω for spherical longitude</li>
     *   <li>Θ for spherical latitude</li>
     *   <li><var>r</var> for geocentric radius</li>
     * </ul>
     *
     * @param authority     organization responsible for definition of the name, or {@code null}.
     * @param name          the name of the new axis.
     * @param abbreviation  the abbreviation used for this coordinate system axes.
     * @param direction     direction of this coordinate system axis.
     * @param unit          the unit of measure used for this coordinate system axis.
     * @return a coordinate system axis of the given properties.
     */
    public static CoordinateSystemAxis createAxis(final Citation authority, final String name, final char abbreviation,
            final AxisDirection direction, final Unit<?> unit)
    {
        return new SimpleAxis(authority, name, abbreviation, direction, unit);
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
     * Returns a factory for creating {@code MathTransformFactory} instances.
     * The simple factory provided as an example can create only linear transforms.
     *
     * @return a factory for creating linear instances of {@link MathTransform}.
     *
     * @see Metadata#getNameFactory()
     */
    public static MathTransformFactory getMathTransformFactory() {
        return SimpleTransformFactory.provider();
    }
}
