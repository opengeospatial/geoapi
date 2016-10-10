/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Objects;
import javax.measure.Unit;
import javax.measure.format.ParserException;
import tec.units.ri.format.SimpleUnitFormat;

import ucar.nc2.constants.CF;
import ucar.nc2.constants.AxisType;
import ucar.nc2.dataset.CoordinateAxis;
import ucar.nc2.dataset.CoordinateAxis1D;

import org.opengis.util.InternationalString;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.RangeMeaning;


/**
 * A {@link CoordinateSystemAxis} implementation backed by a NetCDF {@link CoordinateAxis1D} object.
 *
 * <p>{@code NetcdfAxis} is a <cite>view</cite>: every methods in this class delegate their work to the
 * wrapped NetCDF axis. Consequently any change in the wrapped axis is immediately reflected in this
 * {@code NetcdfAxis} instance. However users are encouraged to not change the wrapped axis after
 * construction, since GeoAPI referencing objects are expected to be immutable.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class NetcdfAxis extends NetcdfIdentifiedObject implements CoordinateSystemAxis {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -7151982715212018557L;

    /**
     * The NetCDF coordinate axis wrapped by this {@code NetcdfAxis} instance.
     */
    private final CoordinateAxis1D axis;

    /**
     * The unit, computed when first needed.
     */
    transient volatile Unit<?> unit;

    /**
     * Creates a new {@code NetcdfAxis} object wrapping the given NetCDF coordinate axis.
     *
     * @param axis The NetCDF coordinate axis to wrap.
     */
    public NetcdfAxis(final CoordinateAxis1D axis) {
        Objects.requireNonNull(axis);
        this.axis = axis;
    }

    /**
     * Returns the wrapped NetCDF axis.
     */
    @Override
    public CoordinateAxis1D delegate() {
        return axis;
    }

    /**
     * Returns the axis name. The default implementation delegates to
     * {@link CoordinateAxis1D#getShortName()}.
     *
     * @see CoordinateAxis1D#getShortName()
     */
    @Override
    public String getCode() {
        return axis.getShortName();
    }

    /**
     * Returns the axis abbreviation. The default implementation returns
     * an acronym of the value returned by {@link CoordinateAxis1D#getShortName()}.
     *
     * @see CoordinateAxis1D#getShortName()
     */
    @Override
    public String getAbbreviation() {
        final String name = axis.getShortName().trim();
        if (name.equalsIgnoreCase("longitude")) return "λ";
        if (name.equalsIgnoreCase("latitude"))  return "φ";
        return name.substring(0, 1);
    }

    /**
     * Returns the axis direction. The default implementation delegates to
     * {@link #getDirection(CoordinateAxis)}.
     *
     * @see CoordinateAxis1D#getAxisType()
     * @see CoordinateAxis1D#getPositive()
     */
    @Override
    public AxisDirection getDirection() {
        return getDirection(axis);
    }

    /**
     * Returns the direction of the given axis. This method infers the direction from
     * {@link CoordinateAxis#getAxisType()} and {@link CoordinateAxis#getPositive()}.
     * If the direction can not be determined, then this method returns
     * {@link AxisDirection#OTHER}.
     *
     * @param  axis The axis for which to get the direction.
     * @return The direction of the given axis.
     */
    public static AxisDirection getDirection(final CoordinateAxis axis) {
        final AxisType type = axis.getAxisType();
        final boolean down = CF.POSITIVE_DOWN.equals(axis.getPositive());
        if (type != null) {
            switch (type) {
                case Time: return down ? AxisDirection.PAST : AxisDirection.FUTURE;
                case Lon:
                case GeoX: return down ? AxisDirection.WEST : AxisDirection.EAST;
                case Lat:
                case GeoY: return down ? AxisDirection.SOUTH : AxisDirection.NORTH;
                case Pressure:
                case Height:
                case GeoZ: return down ? AxisDirection.DOWN : AxisDirection.UP;
            }
        }
        return AxisDirection.OTHER;
    }

    /**
     * Returns the axis minimal value. The default implementation delegates
     * to {@link CoordinateAxis1D#getMinValue()}.
     *
     * @see CoordinateAxis1D#getMinValue()
     */
    @Override
    public double getMinimumValue() {
        return axis.getMinValue();
    }

    /**
     * Returns the axis maximal value. The default implementation delegates
     * to {@link CoordinateAxis1D#getMaxValue()}.
     *
     * @see CoordinateAxis1D#getMaxValue()
     */
    @Override
    public double getMaximumValue() {
        return axis.getMaxValue();
    }

    /**
     * Returns the number of ordinates in the NetCDF axis. This method delegates to the
     * {@link CoordinateAxis1D#getShape(int)} method.
     *
     * @return The number or ordinates in the NetCDF axis.
     */
    public int length() {
        return axis.getShape(0);
    }

    /**
     * Returns {@code null} since the range meaning is unspecified.
     */
    @Override
    public RangeMeaning getRangeMeaning() {
        return null;
    }

    /**
     * Returns the units as a string. If the axis direction or the time epoch
     * was appended to the units, then this part of the string is removed.
     */
    private String getUnitsString() {
        String symbol = axis.getUnitsString();
        if (symbol != null) {
            int i = symbol.lastIndexOf('_');
            if (i > 0) {
                final String direction = getDirection().name();
                if (symbol.regionMatches(true, i+1, direction, 0, direction.length())) {
                    symbol = symbol.substring(0, i).trim();
                }
            }
            i = symbol.indexOf(" since ");
            if (i > 0) {
                symbol = symbol.substring(0, i);
            }
            symbol = symbol.trim();
        }
        return symbol;
    }

    /**
     * Returns the units, or {@code null} if unknown.
     *
     * @see CoordinateAxis1D#getUnitsString()
     * @see Unit#valueOf(CharSequence)
     */
    @Override
    public Unit<?> getUnit() {
        Unit<?> unit = this.unit;
        if (unit == null) {
            final String symbol = getUnitsString();
            if (symbol != null) try {
                this.unit = unit = SimpleUnitFormat.getInstance().parse(symbol);
            } catch (ParserException e) {
                // Unsupported unit symbol.
            }
        }
        return unit;
    }

    /**
     * Returns the NetCDF description, or {@code null} if none.
     * The default implementation delegates to {@link CoordinateAxis1D#getDescription()}.
     *
     * @see CoordinateAxis1D#getDescription()
     */
    @Override
    public InternationalString getRemarks() {
        final String description = axis.getDescription();
        if (description != null) {
            return new SimpleCitation(description);
        }
        return super.getRemarks();
    }
}
