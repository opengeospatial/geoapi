/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Objects;
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.IncommensurableException;
import tech.uom.seshat.Units;

import org.opengis.metadata.citation.Citation;
import org.opengis.example.metadata.SimpleCitation;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.RangeMeaning;


/**
 * A {@link CoordinateSystemAxis} defined by conventional symbols.
 * This implementation infers some axis properties from the
 * {@linkplain #abbreviation}, as documented in the following table:
 *
 * <blockquote><table class="ogc">
 * <caption>Axis properties inferred from the abbreviation</caption>
 * <tr><th>Symbol</th>   <th>Common name</th>     <th>Axis direction</th>                                        <th>Unit</th>                     <th>Range</th> <th>Meaning</th></tr>
 * <tr><th>λ</th> <td>geodetic longitude</td>     <td>{@link AxisDirection#EAST            EAST}</td>            <td>degree</td>                   <td>±90°</td>  <td>{@link RangeMeaning#EXACT      EXACT}</td></tr>
 * <tr><th>φ</th> <td>geodetic latitude</td>      <td>{@link AxisDirection#NORTH           NORTH}</td>           <td>degree</td>                   <td>±180°</td> <td>{@link RangeMeaning#WRAPAROUND WRAPAROUND}</td></tr>
 * <tr><th>h</th> <td>ellipsoidal height</td>     <td>{@link AxisDirection#UP              UP}</td>              <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>H</th> <td>gravity-related height</td> <td>{@link AxisDirection#UP              UP}</td>              <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>d</th> <td>depth</td>                  <td>{@link AxisDirection#DOWN            DOWN}</td>            <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>r</th> <td>geocentric radius</td>      <td>{@link AxisDirection#UP              UP}</td>              <td>{@link Units#METRE}</td>      <td>[0…∞]</td> <td></td></tr>
 * <tr><th>Ω</th> <td>spherical longitude</td>    <td>{@link AxisDirection#EAST            EAST}</td>            <td>degree</td>                   <td>±90°</td>  <td>{@link RangeMeaning#EXACT      EXACT}</td></tr>
 * <tr><th>Θ</th> <td>spherical latitude</td>     <td>{@link AxisDirection#NORTH           NORTH}</td>           <td>degree</td>                   <td>±180°</td> <td>{@link RangeMeaning#WRAPAROUND WRAPAROUND}</td></tr>
 * <tr><th>X</th> <td>geocentric X</td>           <td>{@link AxisDirection#GEOCENTRIC_X    GEOCENTRIC_X}</td>    <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>Y</th> <td>geocentric Y</td>           <td>{@link AxisDirection#GEOCENTRIC_Y    GEOCENTRIC_Y}</td>    <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>Z</th> <td>geocentric Z</td>           <td>{@link AxisDirection#GEOCENTRIC_Z    GEOCENTRIC_Z}</td>    <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>E</th> <td>easting</td>                <td>{@link AxisDirection#EAST            EAST}</td>            <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>W</th> <td>westing</td>                <td>{@link AxisDirection#WEST            WEST}</td>            <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>N</th> <td>northing</td>               <td>{@link AxisDirection#NORTH           NORTH}</td>           <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>S</th> <td>southing</td>               <td>{@link AxisDirection#SOUTH           SOUTH}</td>           <td>{@link Units#METRE}</td>      <td></td>      <td></td></tr>
 * <tr><th>t</th> <td>time</td>                   <td>{@link AxisDirection#FUTURE          FUTURE}</td>          <td>{@link Units#SECOND}</td>     <td></td>      <td></td></tr>
 * <tr><th>i</th> <td>column</td>                 <td>{@link AxisDirection#COLUMN_POSITIVE COLUMN_POSITIVE}</td> <td>{@link AbstractUnit#ONE}</td> <td></td>      <td></td></tr>
 * <tr><th>j</th> <td>row</td>                    <td>{@link AxisDirection#ROW_POSITIVE    ROW_POSITIVE}</td>    <td>{@link AbstractUnit#ONE}</td> <td></td>      <td></td></tr>
 * </table></blockquote>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleAxis extends SimpleIdentifiedObject implements CoordinateSystemAxis {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 3541182320484949668L;

    /**
     * The unit of measurement for degrees of angle. Will be removed in a future GeoAPI version
     * if a future Unit of Measurement implementation provides a predefined constant for this unit.
     */
    static final Unit<Angle> DEGREE = Units.RADIAN.multiply(Math.PI/180);

    /**
     * The <cite>geodetic latitude</cite> axis.
     * Values are increasing toward East, in decimal degrees.
     */
    public static final CoordinateSystemAxis LATITUDE = new SimpleAxis(SimpleCitation.EPSG, "geodetic latitude", 'φ');

    /**
     * The <cite>geodetic longitude</cite> axis.
     * Values are increasing toward North, in decimal degrees.
     */
    public static final CoordinateSystemAxis LONGITUDE = new SimpleAxis(SimpleCitation.EPSG, "geodetic longitude", 'λ');

    /**
     * The abbreviation used for this coordinate system axes. This abbreviation is also
     * used to identify the coordinates in coordinate tuple. Examples are "<var>X</var>"
     * and "<var>Y</var>".
     *
     * @see #getAbbreviation()
     */
    protected final char abbreviation;

    /**
     * Direction of this coordinate system axis.
     *
     * @see #getDirection()
     */
    protected final AxisDirection direction;

    /**
     * The unit of measure used for this coordinate system axis.
     *
     * @see #getUnit()
     */
    protected final Unit<?> unit;

    /**
     * Creates a new axis for the given authority, name and abbreviation. The axis direction
     * and units are inferred from the abbreviation using the table documented in the class
     * javadoc.
     *
     * @param  authority     organization responsible for definition of the name, or {@code null}.
     * @param  name          the name of the new axis.
     * @param  abbreviation  the abbreviation used for this coordinate system axes.
     * @throws IllegalArgumentException if the abbreviation is not one of the documented ones.
     */
    public SimpleAxis(final Citation authority, final String name, final char abbreviation)
            throws IllegalArgumentException
    {
        super(authority, name);
        this.abbreviation = abbreviation;
        switch (abbreviation) {
            case 'X':                               direction = AxisDirection.GEOCENTRIC_X;    break;
            case 'Y':                               direction = AxisDirection.GEOCENTRIC_Y;    break;
            case 'Z':                               direction = AxisDirection.GEOCENTRIC_Z;    break;
            case 'E': case 'x': case 'λ': case 'Ω': direction = AxisDirection.EAST;            break;
            case 'N': case 'y': case 'φ': case 'Θ': direction = AxisDirection.NORTH;           break;
            case 'H': case 'z': case 'h': case 'r': direction = AxisDirection.UP;              break;
            case 'W':                               direction = AxisDirection.WEST;            break;
            case 'S':                               direction = AxisDirection.SOUTH;           break;
            case 'd':                               direction = AxisDirection.DOWN;            break;
            case 't':                               direction = AxisDirection.FUTURE;          break;
            case 'i':                               direction = AxisDirection.COLUMN_POSITIVE; break;
            case 'j':                               direction = AxisDirection.ROW_POSITIVE;    break;
            default: throw new IllegalArgumentException("Unknown abbreviation: " + abbreviation);
        }
        switch (abbreviation) {
            case 'λ': case 'Ω':
            case 'φ': case 'Θ': unit = DEGREE;       break;
            case 'i': case 'j': unit = Units.UNITY;  break;
            case 't':           unit = Units.SECOND; break;
            default:            unit = Units.METRE;  break;
        }
    }

    /**
     * Creates a new axis for the given authority, name, units and direction.
     * The following abbreviation are handled specially by this class:
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
     */
    public SimpleAxis(final Citation authority, final String name, final char abbreviation,
            final AxisDirection direction, final Unit<?> unit)
    {
        super(authority, name);
        Objects.requireNonNull(direction);
        Objects.requireNonNull(unit);
        this.abbreviation = abbreviation;
        this.direction    = direction;
        this.unit         = unit;
    }

    /**
     * The abbreviation used for this coordinate system axes. This abbreviation is also
     * used to identify the coordinates in coordinate tuple. Examples are "<var>X</var>"
     * and "<var>Y</var>".
     */
    @Override
    public String getAbbreviation() {
        return String.valueOf(abbreviation);
    }

    /**
     * Direction of this coordinate system axis.
     */
    @Override
    public AxisDirection getDirection() {
        return direction;
    }

    /**
     * Returns the minimum value normally allowed for this axis, in the {@linkplain #getUnit unit
     * of measure for the axis}. The default implementation infers the value from the abbreviation
     * symbol:
     *
     * <ul>
     *   <li>0 for <var>r</var></li>
     *   <li>-90 for φ and Θ</li>
     *   <li>-180 for λ and Ω</li>
     *   <li>{@link Double#NEGATIVE_INFINITY NEGATIVE_INFINITY} for all other abbreviations</li>
     * </ul>
     */
    @Override
    public double getMinimumValue() {
        switch (abbreviation) {
            case 'r':           return 0;
            case 'φ': case 'Θ': return toAngularUnit( -90);
            case 'λ': case 'Ω': return toAngularUnit(-180);
        }
        return Double.NEGATIVE_INFINITY;
    }

    /**
     * Returns the maximum value normally allowed for this axis, in the {@linkplain #getUnit unit
     * of measure for the axis}. The default implementation infers the value from the abbreviation
     * symbol:
     *
     * <ul>
     *   <li>90 for φ and Θ</li>
     *   <li>180 for λ and Ω</li>
     *   <li>{@link Double#POSITIVE_INFINITY POSITIVE_INFINITY} for all other abbreviations</li>
     * </ul>
     */
    @Override
    public double getMaximumValue() {
        switch (abbreviation) {
            case 'φ': case 'Θ': return toAngularUnit( 90);
            case 'λ': case 'Ω': return toAngularUnit(180);
        }
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Returns the meaning of axis value range specified by the {@linkplain #getMinimumValue
     * minimum} and {@linkplain #getMaximumValue maximum} values. The default implementation
     * infers the value from the abbreviation symbol:
     *
     * <ul>
     *   <li>{@link RangeMeaning#EXACT EXACT} for φ and Θ</li>
     *   <li>{@link RangeMeaning#WRAPAROUND WRAPAROUND} for λ and Ω</li>
     *   <li>{@code null} for all other abbreviations</li>
     * </ul>
     */
    @Override
    public RangeMeaning getRangeMeaning() {
        switch (abbreviation) {
            case 'φ': case 'Θ': return RangeMeaning.EXACT;
            case 'λ': case 'Ω': return RangeMeaning.WRAPAROUND;
        }
        return null;
    }

    /**
     * The unit of measure used for this coordinate system axis.
     */
    @Override
    public Unit<?> getUnit() {
        return unit;
    }

    /**
     * Converts the given angle from degrees to the angular units used by this axis.
     *
     * @param  angle  the angle to convert.
     * @return the converted angle.
     * @throws IllegalStateException if this axis does not use angular units.
     */
    private double toAngularUnit(final double angle) throws IllegalStateException {
        try {
            return DEGREE.getConverterToAny(unit).convert(angle);
        } catch (IncommensurableException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Compares this axis with the given object for equality.
     *
     * @param  object  the object to compare with this {@code SimpleCRS}.
     * @return {@code true} if the given object is equal to this object.
     */
    @Override
    public boolean equals(final Object object) {
        if (super.equals(object)) {
            final SimpleAxis other = (SimpleAxis) object;
            return abbreviation == other.abbreviation &&
                   direction.equals(other.direction) &&
                   unit.equals(other.unit);
        }
        return false;
    }
}
