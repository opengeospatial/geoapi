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
package org.proj4;

import org.opengis.util.FactoryException;


/**
 * Wraps the <a href="http://proj.osgeo.org/">Proj4</a> {@code PJ} native data structure.
 * The PJ structure contains in a single place many of the information that ISO 19111 was
 * use to split in different interfaces: {@link org.opengis.referencing.datum.Ellipsoid},
 * {@link org.opengis.referencing.datum.Datum}, a {@link org.opengis.referencing.datum.PrimeMeridian},
 * {@link org.opengis.referencing.cs.CoordinateSystem}, {@link org.opengis.referencing.crs.CoordinateReferenceSystem}
 * and their sub-interfaces. The relationship with the GeoAPI methods is indicated in the
 * "See" tags when appropriate.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PJ {
    /**
     * Loads the Proj4 library.
     */
    static {
        System.loadLibrary("proj");
    }

    /**
     * The pointer to {@code PJ} structure allocated in the C/C++ heap. This value has no
     * meaning in Java code. <strong>Do not modify</strong>, since this value is used by Proj4.
     * Do not rename neither, unless you update accordingly the C code in JNI wrappers.
     */
    private final long ptr;

    /**
     * Creates a new {@code PJ} structure from the given Proj4 data.
     *
     * @param  definition The Proj4 definition string.
     * @throws FactoryException If the PJ structure can not be created from the given string.
     */
    public PJ(final String definition) throws FactoryException {
        ptr = allocatePJ(definition);
        if (ptr == 0) {
            throw new FactoryException(definition);
        }
    }

    /**
     * Allocate a PJ native data structure and returns the pointer to it. This method should be
     * invoked by the constructor only, and the return value <strong>must</strong> be assigned
     * to the {@link #ptr} field. The allocated structure is released by the {@link #finalize()}
     * method.
     *
     * @param  definition The Proj4 definition string.
     * @return A pointer to the PJ native data structure, or 0 if the operation failed.
     */
    private static native long allocatePJ(String definition);

    /**
     * Returns the version number of the Proj4 library.
     *
     * @return The Proj4 release string.
     */
    public static native String getVersion();

    /**
     * Returns the Proj4 definition string. This is the string given to the constructor,
     * expanded with as much information as possible.
     *
     * @return The Proj4 definition string.
     */
    public native String getDefinition();

    /**
     * Returns the {@code pj.a_orig} value.
     *
     * @return The axis length stored in {@code pj.a_orig}.
     *
     * @see org.opengis.referencing.datum.Ellipsoid#getSemiMajorAxis()
     */
    public native double getSemiMajorAxis();

    /**
     * Returns the {@code sqrt((pj.a_orig)² * (1 - pj.es_orig))} value.
     *
     * @return The axis length computed by {@code sqrt((pj.a_orig)² * (1 - pj.es_orig))}.
     *
     * @see org.opengis.referencing.datum.Ellipsoid#getSemiMinorAxis()
     */
    public native double getSemiMinorAxis();

    /**
     * Returns the {@code sqrt((pj.a_orig)² * (1 - pj.es_orig))} value.
     *
     * @return The inverse flattening computed by {@code sqrt((pj.a_orig)² * (1 - pj.es_orig))}.
     *
     * @see org.opengis.referencing.datum.Ellipsoid#getInverseFlattening()
     */
    public native double getInverseFlattening();

    /**
     * Returns {@code true} if this ellipsoid is a sphere.
     *
     * @return {@code true} if this ellipsoid is a sphere.
     *
     * @see org.opengis.referencing.datum.Ellipsoid#isSphere()
     */
    public native boolean isSphere();

    /**
     * Returns {@code true} if this PJ structure is for a geographic coordinate system.
     *
     * @return {@code true} if the CRS is geographic.
     */
    public native boolean isGeographic();

    /**
     * Returns {@code true} if this PJ structure is for a geocentric coordinate system.
     *
     * @return {@code true} if the CRS is geocentric.
     */
    public native boolean isGeocentric();

    /**
     * Returns an array of character indicating the direction of each axis. Directions are
     * characters like {@code 'e'} for East, {@code 'n'} for North and {@code 'u'} for Up.
     *
     * @return The axis directions.
     *
     * @see org.opengis.referencing.cs.CoordinateSystemAxis#getDirection()
     */
    public native char[] getAxisDirections();

    /**
     * Longitude of the prime meridian measured from the Greenwich meridian, positive eastward.
     *
     * @return The prime meridian longitude, in degrees.
     *
     * @see org.opengis.referencing.datum.PrimeMeridian#getGreenwichLongitude()
     */
    public native double getGreenwichLongitude();

    /**
     * Transforms in-place the coordinates in the given array.
     *
     * @param  target The target CRS.
     * @param  hasZ {@code true} if the given coordinate array is two-dimensional (with <var>z</var>
     *         component), or {@code false} if two-dimensional.
     * @param  coordinates The coordinates to transform, packed as (<var>x</var>,<var>y</var>)
     *         or (<var>x</var>,<var>y</var>,<var>z</var>) tuples.
     * @param  offset Offset of the first coordinate in the given array.
     * @param  numPts Number of points to transform.
     * @throws NullPointerException If the {@code target} or {@code coordinates} argument is null.
     * @throws IndexOutOfBoundsException if the {@code offset} or {@code numPts} arguments are invalid.
     * @throws PJException If the operation failed for an other reason (provided by Proj4).
     *
     * @see org.opengis.referencing.operation.MathTransform#transform(double[], int, double[], int, int)
     */
    public native void transform(final PJ target, boolean hasZ, double[] coordinates, int offset, int numPts)
            throws PJException;

    /**
     * Returns the string representation of the PJ structure.
     *
     * @return The string representation.
     */
    @Override
    public native String toString();

    /**
     * <strong>NEVER INVOKE THIS METHOD EXPLICITELY, NEVER OVERRIDE</strong>.
     * This method deallocates the native PJ data structure. This method can be invoked
     * only by the garbage collector, and must be invoked exactly once (no more, no less).
     */
    @Override
    protected final native void finalize();
}
