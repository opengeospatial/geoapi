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
     * The number of dimensions.
     */
    final int dimension;

    /**
     * Creates a new CRS using the given identifier, Proj4 peer and number of dimensions.
     */
    PJCRS(final ReferenceIdentifier identifier, final PJDatum datum, final int dimension) {
        super(identifier);
        this.pj = datum;
        this.dimension = dimension;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the number of dimension given at construction time.
     */
    @Override
    public final int getDimension() {
        return dimension;
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
        Projected(final ReferenceIdentifier identifier, final PJDatum datum, final int dimension) {
            super(identifier, datum, dimension);
        }

        @Override
        public CartesianCS getCoordinateSystem() {
            return this;
        }

        @Override
        public GeographicCRS getBaseCRS() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Projection getConversionFromBase() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
