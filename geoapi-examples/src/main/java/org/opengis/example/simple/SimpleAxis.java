/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.simple;

import javax.measure.unit.Unit;
import javax.measure.unit.NonSI;
import javax.measure.converter.ConversionException;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.RangeMeaning;


/**
 * A {@link CoordinateSystemAxis}.
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
     * The abbreviation used for this coordinate system axes. This abbreviation is also
     * used to identify the ordinates in coordinate tuple. Examples are "<var>X</var>"
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
     * Creates a new axis for the given authority, name, units and direction.
     * The following abbreviation are handled specially by this class:
     * <p>
     * <ul>
     *   <li>&lambda; for longitude</li>
     *   <li>&phi; for latitude</li>
     * </ul>
     *
     * @param authority    Organization responsible for definition of the name, or {@code null}.
     * @param name         The name of the new axis.
     * @param abbreviation The abbreviation used for this coordinate system axes.
     * @param direction    Direction of this coordinate system axis.
     * @param unit         The unit of measure used for this coordinate system axis.
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
     * used to identify the ordinates in coordinate tuple. Examples are "<var>X</var>"
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
     * <p>
     * <ul>
     *   <li>-90 for &phi;</li>
     *   <li>-180 for &lambda;</li>
     *   <li>{@link Double#NEGATIVE_INFINITY NEGATIVE_INFINITY} for all other abbreviations</li>
     * </ul>
     */
    @Override
    public double getMinimumValue() {
        switch (abbreviation) {
            case 'φ': return toAngularUnit( -90);
            case 'λ': return toAngularUnit(-180);
        }
        return Double.NEGATIVE_INFINITY;
    }

    /**
     * Returns the maximum value normally allowed for this axis, in the {@linkplain #getUnit unit
     * of measure for the axis}. The default implementation infers the value from the abbreviation
     * symbol:
     * <p>
     * <ul>
     *   <li>90 for &phi;</li>
     *   <li>180 for &lambda;</li>
     *   <li>{@link Double#POSITIVE_INFINITY POSITIVE_INFINITY} for all other abbreviations</li>
     * </ul>
     */
    @Override
    public double getMaximumValue() {
        switch (abbreviation) {
            case 'φ': return toAngularUnit( 90);
            case 'λ': return toAngularUnit(180);
        }
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Returns the meaning of axis value range specified by the {@linkplain #getMinimumValue
     * minimum} and {@linkplain #getMaximumValue maximum} values. The default implementation
     * infers the value from the abbreviation symbol:
     * <p>
     * <ul>
     *   <li>{@link RangeMeaning#EXACT EXACT} for &phi;</li>
     *   <li>{@link RangeMeaning#WRAPAROUND WRAPAROUND} for &lambda;</li>
     *   <li>{@code null} for all other abbreviations</li>
     * </ul>
     */
    @Override
    public RangeMeaning getRangeMeaning() {
        switch (abbreviation) {
            case 'φ': return RangeMeaning.EXACT;
            case 'λ': return RangeMeaning.WRAPAROUND;
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
     * @param  angle The angle to convert.
     * @return The converted angle.
     * @throws IllegalStateException If this axis doesn't use angular units.
     */
    private double toAngularUnit(final double angle) throws IllegalStateException {
        try {
            return NonSI.DEGREE_ANGLE.getConverterToAny(unit).convert(angle);
        } catch (ConversionException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Compares this axis with the given object for equality.
     *
     * @param  object The object to compare with this {@code SimpleCRS}.
     * @return {@code true} if the given object is equals to this object.
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
