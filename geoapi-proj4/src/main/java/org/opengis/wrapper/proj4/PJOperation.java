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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.SingleOperation;
import org.opengis.referencing.operation.TransformException;
import org.proj4.PJ;


/**
 * A math transform which delegate its work to the Proj4 native library.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJOperation extends PJObject implements SingleOperation, MathTransform {
    /**
     * The source and target CRS.
     */
    private final PJCRS source, target;

    /**
     * The number of dimensions, which must be the same for both CRS.
     */
    private final int dimension;

    /**
     * The inverse transform, created only when first needed.
     */
    private transient PJOperation inverse;

    /**
     * Creates a new operation for the given source and target CRS.
     */
    PJOperation(final ReferenceIdentifier name, final PJCRS source, final PJCRS target) {
        super(name);
        this.source    = source;
        this.target    = target;
        this.dimension = source.dimension;
        if (dimension != target.dimension) {
            throw new IllegalArgumentException("Source and target CRS must have the same number of dimensions.");
        }
    }

    /**
     * Returns the operation method.
     *
     * @todo Not yet implemented.
     */
    @Override
    public OperationMethod getMethod() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the parameter values defining this operation.
     *
     * @todo Not yet implemented.
     */
    @Override
    public ParameterValueGroup getParameterValues() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Trivial methods.
     */
    @Override public CoordinateReferenceSystem getSourceCRS() {return source;}
    @Override public CoordinateReferenceSystem getTargetCRS() {return target;}
    @Override public final int     getSourceDimensions()      {return dimension;}
    @Override public final int     getTargetDimensions()      {return dimension;}
    @Override public MathTransform getMathTransform()         {return this;}
    @Override public boolean       isIdentity()               {return source.pj.equals(target.pj);}
    @Override public String        getOperationVersion()      {return PJ.getVersion();}
    @Override public Collection<PositionalAccuracy> getCoordinateOperationAccuracy() {
        return Collections.emptySet();
    }

    /**
     * Transforms a single coordinate point.
     *
     * @todo Not yet implemented.
     */
    @Override
    public DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst) throws MismatchedDimensionException, TransformException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Transforms an array of coordinate tuples.
     */
    @Override
    public void transform(final double[] srcPts, final int srcOff,
                          final double[] dstPts, final int dstOff,
                          final int numPts) throws TransformException
    {
        if (srcPts != dstPts || srcOff != dstOff) {
            final int length = dimension * numPts;
            System.arraycopy(srcPts, srcOff, dstPts, dstOff, length);
        }
        transform(dstPts, dstOff, numPts);
    }

    /**
     * Transforms an array of coordinate tuples.
     */
    @Override
    public void transform(final float[] srcPts, final int srcOff,
                          final float[] dstPts, final int dstOff,
                          int numPts) throws TransformException
    {
        int length = dimension * numPts;
        final double[] copy = new double[length];
        for (int i=0; i<length; i++) {
            copy[i] = srcPts[srcOff + i];
        }
        transform(copy, 0, numPts);
        for (int i=0; i<length; i++) {
            dstPts[dstOff + i] = (float) copy[i];
        }
    }

    /**
     * Transforms an array of coordinate tuples.
     */
    @Override
    public void transform(final float[]  srcPts, final int srcOff,
                          final double[] dstPts, final int dstOff,
                          final int numPts) throws TransformException
    {
        int length = dimension * numPts;
        for (int i=0; i<length; i++) {
            dstPts[dstOff + i] = srcPts[srcOff + i];
        }
        transform(dstPts, dstOff, numPts);
    }

    /**
     * Transforms an array of coordinate tuples.
     */
    @Override
    public void transform(final double[] srcPts, final int srcOff,
                          final float[]  dstPts, final int dstOff,
                          final int numPts) throws TransformException
    {
        int length = dimension * numPts;
        final double[] copy = Arrays.copyOfRange(srcPts, srcOff, srcOff + length);
        transform(copy, 0, numPts);
        for (int i=0; i<length; i++) {
            dstPts[dstOff + i] = (float) copy[i];
        }
    }

    /**
     * Performs the actual coordinate transformation. This method delegates
     * to the native Proj4 code.
     */
    private void transform(final double[] data, final int offset, final int numPts) throws TransformException {
        source.pj.transform(target.pj, dimension, data, offset, numPts);
    }

    /**
     * The Proj4 library does not provide derivative functions.
     */
    @Override
    public Matrix derivative(DirectPosition point) throws TransformException {
        throw new TransformException("Not supported yet.");
    }

    /**
     * Returns the inverse transform.
     */
    @Override
    public synchronized MathTransform inverse() {
        if (inverse == null) {
            inverse = new PJOperation(name, target, source);
            inverse.inverse = this;
        }
        return inverse;
    }
}
