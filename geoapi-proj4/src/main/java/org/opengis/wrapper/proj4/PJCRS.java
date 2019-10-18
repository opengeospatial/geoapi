/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

import javax.measure.Unit;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.GeocentricCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.operation.Projection;


/**
 * Base class of all CRS defined in the {@literal Proj.4} package. The Proj.4 library does not make
 * distinction between Coordinate System and Coordinate Reference System, so we implement the two
 * interfaces by the same class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
class PJCRS extends PJObject implements CoordinateReferenceSystem, CoordinateSystem {
    /**
     * The geodetic datum, which is also the object to use for performing call to {@literal Proj.4} functions.
     */
    final PJDatum pj;

    /**
     * The coordinate system axes. The length of this array is the dimension,
     * which must be greater than or equals to 2.
     */
    final CoordinateSystemAxis[] axes;

    /**
     * Creates a new CRS using the given identifier, {@literal Proj.4} peer and number of dimensions.
     *
     * @param identifier  the name of the new CRS, or {@code null} if none.
     * @param datum       the geodetic datum, which is also the wrapper for Proj.4 native methods.
     * @param dimension   the number of dimensions of the new CRS. Must be at least 2.
     * @param unit        the horizontal axes unit.
     */
    PJCRS(final Identifier identifier, final PJDatum datum, final int dimension, final Unit<?> unit) {
        super(identifier);
        pj = datum;
        axes = new CoordinateSystemAxis[dimension];
        final char[] dir = datum.getAxisDirections();
        for (int i=0; i<dimension; i++) {
            final char d = (i < dir.length) ? Character.toLowerCase(dir[i]) : ' ';
            axes[i] = new PJAxis(d, (d == 'u' || d == 'd') ? datum.getLinearUnit(true) : unit);
        }
    }

    /**
     * Returns a string representation of this object, mostly for debugging purpose.
     * This string representation may change in any future version.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + pj.getDefinition() + ']';
    }

    /**
     * Returns the geodetic datum.
     */
    public final GeodeticDatum getDatum() {
        return pj;
    }

    /**
     * Returns the coordinate system, which is this object since {@literal Proj.4} does not distinguish CS and CRS.
     */
    @Override
    public CoordinateSystem getCoordinateSystem() {
        return this;
    }

    /**
     * Returns the coordinate axis at the given dimension.
     */
    @Override
    public final CoordinateSystemAxis getAxis(final int dimension) throws IndexOutOfBoundsException {
        return axes[dimension];
    }

    /**
     * Returns the number of dimension given at construction time.
     */
    @Override
    public final int getDimension() {
        return axes.length;
    }

    /**
     * The geocentric specialization of {@link PJCRS}.
     */
    static final class Geocentric extends PJCRS implements GeocentricCRS {
        Geocentric(final Identifier identifier, final PJDatum datum, final int dimension) {
            super(identifier, datum, dimension, Units.DEGREE);
        }
    }

    /**
     * The geographic specialization of {@link PJCRS}.
     */
    static final class Geographic extends PJCRS implements GeographicCRS, EllipsoidalCS {
        Geographic(final Identifier identifier, final PJDatum datum, final int dimension) {
            super(identifier, datum, dimension, Units.DEGREE);
        }

        @Override
        public EllipsoidalCS getCoordinateSystem() {
            return this;
        }
    }

    /**
     * The projected specialization of {@link PJCRS}.
     */
    static final class Projected extends PJCRS implements ProjectedCRS, CartesianCS {
        /**
         * The axis orientation of this CRS, as a comma-separated list of Proj.4 declarations.
         * The first element (typically {@code "enu"} is for the projected CRS itself - not
         * really used since it should already be contained in the {@link #pj} object. The
         * second element (typically {@code "neu"}) is for the base CRS.
         *
         * <p>This field may be {@code null} if this information was unspecified.</p>
         */
        private final String axisOrientations;

        /**
         * The value returned by {@link #getBaseCRS()}, created when first needed.
         */
        transient Geographic baseCRS;

        /**
         * The value returned by {@link #getConversionFromBase()}, created when first needed.
         */
        private transient Projection conversion;

        /**
         * Creates a new projected CRS.
         */
        Projected(final Identifier identifier, final PJDatum datum, final int dimension,
                final String axisOrientations)
        {
            super(identifier, datum, dimension, datum.getLinearUnit(false));
            this.axisOrientations = axisOrientations;
        }

        /**
         * Returns the coordinate system, which is {@code this} object.
         */
        @Override
        public CartesianCS getCoordinateSystem() {
            return this;
        }

        /**
         * Adds the vertical axis to the given {@literal Proj.4} orientations code, if the vertical axis is missing.
         * If this method is unsure about the given axis orientations, then it conservatively does nothing.
         * This may cause Proj.4 to reject the axis orientations.
         */
        static String ensure3D(String orientations) {
            if (orientations.length() == 2 && orientations.indexOf('u') < 0 & orientations.indexOf('d') < 0) {
                orientations += 'u';
            }
            return orientations;
        }

        /**
         * Returns the end index of the word beginning at the given index.
         * Only ASCII characters are considered.
         */
        static int findWordEnd(final CharSequence definition, int startAt) {
            final int length = definition.length();
            while (startAt < length) {
                final char c = definition.charAt(startAt);
                if ((c<'a' || c>'z') && (c<'A' || c>'Z')) {
                    break;
                }
                startAt++;
            }
            return startAt;
        }

        /**
         * Returns the base CRS, which is inferred by the {@literal Proj.4} library.
         * This method may need to change the axis order compared to the one used by Proj.4.
         */
        @Override
        public synchronized Geographic getBaseCRS() {
            if (baseCRS == null) {
                int dimension = axes.length;
                PJDatum base = new PJDatum(pj);
                if (axisOrientations != null) {
                    final int s = axisOrientations.indexOf(PJFactory.AXIS_ORDER_SEPARATOR);
                    if (s >= 0) {
                        String orientation = axisOrientations.substring(s+1);
                        dimension = orientation.length();
                        orientation = ensure3D(orientation);
                        if (!String.valueOf(base.getAxisDirections()).equals(orientation)) {
                            final StringBuilder definition = new StringBuilder(base.getDefinition());
                            int ap = definition.indexOf(PJFactory.AXIS_ORDER_PARAM);
                            if (ap < 0) {
                                ap = definition.append(' ').length();
                                definition.append(PJFactory.AXIS_ORDER_PARAM);
                            }
                            ap += PJFactory.AXIS_ORDER_PARAM.length();
                            definition.replace(ap, findWordEnd(definition, ap), orientation);
                            base = new PJDatum(base.getName(), definition.toString());
                        }
                    }
                }
                baseCRS = new Geographic(name, base, dimension);
            }
            return baseCRS;
        }

        /**
         * Returns the conversion from the projected CRS to the base CRS.
         */
        @Override
        public synchronized Projection getConversionFromBase() {
            if (conversion == null) {
                conversion = new PJOperation.Projection(name, baseCRS, this);
            }
            return conversion;
        }
    }
}
