/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.wrapper.proj4;

import org.opengis.util.FactoryException;
import org.opengis.referencing.ReferenceIdentifier;
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
 * Base class of all CRS defined in the Proj4 package. The Proj.4 library does not make
 * distinction between Coordinate System and Coordinate Reference System, so we implement
 * the two interfaces by the same class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
class PJCRS extends PJObject implements CoordinateReferenceSystem, CoordinateSystem {
    /**
     * The geodetic datum, which is also the object to use for performing call to Proj4 functions.
     */
    final PJDatum pj;

    /**
     * The coordinate system axes. The length of this array is the dimension,
     * which must be greater than or equals to 2.
     */
    final CoordinateSystemAxis[] axes;

    /**
     * Creates a new CRS using the given identifier, Proj4 peer and number of dimensions.
     *
     * @param identifier The name of the new CRS, or {@code null} if none.
     * @param datum The geodetic datum, which is also the wrapper for Proj.4 native methods.
     * @param dimension The number of dimensions of the new CRS. Must be at least 2.
     */
    PJCRS(final ReferenceIdentifier identifier, final PJDatum datum, final int dimension) {
        super(identifier);
        pj = datum;
        axes = new CoordinateSystemAxis[dimension];
        final char[] dir = datum.getAxisDirections();
        for (int i=0; i<dimension; i++) {
            axes[i] = new PJAxis((i < dir.length) ? dir[i] : ' ');
        }
    }

    /**
     * Returns the geodetic datum.
     */
    public final GeodeticDatum getDatum() {
        return pj;
    }

    /**
     * Returns the coordinate system, which is this object since Proj4 does not distinguish CS and CRS.
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
        Geocentric(final ReferenceIdentifier identifier, final PJDatum datum, final int dimension) {
            super(identifier, datum, dimension);
        }
    }

    /**
     * The geographic specialization of {@link PJCRS}.
     */
    static final class Geographic extends PJCRS implements GeographicCRS, EllipsoidalCS {
        Geographic(final ReferenceIdentifier identifier, final PJDatum datum, final int dimension) {
            super(identifier, datum, dimension);
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
         * The value returned by {@link #getBaseCRS()}, created when first needed.
         */
        private transient Geographic baseCRS;

        /**
         * The value returned by {@link #getConversionFromBase()}, created when first needed.
         */
        private transient Projection conversion;

        /**
         * Creates a new projected CRS.
         */
        Projected(final ReferenceIdentifier identifier, final PJDatum datum, final int dimension) {
            super(identifier, datum, dimension);
        }

        /**
         * Returns the coordinate system, which is {@code this} object.
         */
        @Override
        public CartesianCS getCoordinateSystem() {
            return this;
        }

        /**
         * Returns the base CRS, which is inferred by the Proj.4 library.
         */
        @Override
        public synchronized Geographic getBaseCRS() {
            if (baseCRS == null) try {
                baseCRS = new Geographic(name, new PJDatum(pj), axes.length);
            } catch (FactoryException e) {
                throw new IllegalStateException(e.getLocalizedMessage(), e);
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
