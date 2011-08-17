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
class PJOperation extends PJObject implements SingleOperation, MathTransform {
    /**
     * The source and target CRS.
     */
    private final PJCRS source, target;

    /**
     * The inverse transform, created only when first needed.
     */
    private transient PJOperation inverse;

    /**
     * Creates a new operation for the given source and target CRS.
     */
    PJOperation(final ReferenceIdentifier name, final PJCRS source, final PJCRS target) {
        super(name);
        this.source = source;
        this.target = target;
    }

    /**
     * Returns the operation method.
     *
     * @todo Not yet implemented.
     */
    @Override
    public OperationMethod getMethod() {
        return null;
    }

    /**
     * Returns the parameter values defining this operation.
     *
     * @todo Not yet implemented.
     */
    @Override
    public ParameterValueGroup getParameterValues() {
        return null;
    }

    /*
     * Trivial methods.
     */
    @Override public CoordinateReferenceSystem getSourceCRS() {return source;}
    @Override public CoordinateReferenceSystem getTargetCRS() {return target;}
    @Override public final int     getSourceDimensions()      {return source.getDimension();}
    @Override public final int     getTargetDimensions()      {return target.getDimension();}
    @Override public MathTransform getMathTransform()         {return this;}
    @Override public String        getOperationVersion()      {return PJ.getVersion();}
    @Override public Collection<PositionalAccuracy> getCoordinateOperationAccuracy() {
        return Collections.emptySet();
    }

    /**
     * Returns {@code true} if this transform is the identity transform. Note that a value of
     * {@code false} does not mean that the transform is not an identity transform, since this
     * case is a bit difficult to determine from Proj.4 API.
     */
    @Override
    public boolean isIdentity() {
        return source.pj.equals(target.pj) && source.getDimension() == target.getDimension();
    }

    /**
     * Transforms a single coordinate point.
     */
    @Override
    public DirectPosition transform(final DirectPosition ptSrc, DirectPosition ptDst)
            throws MismatchedDimensionException, TransformException
    {
        final int srcDim = source.getDimension();
        final int tgtDim = target.getDimension();
        if (ptSrc.getDimension() != srcDim) {
            throw new MismatchedDimensionException();
        }
        double[] ordinates = new double[Math.max(srcDim, tgtDim)];
        for (int i=0; i<srcDim; i++) {
            ordinates[i] = ptSrc.getOrdinate(i);
        }
        source.pj.transform(target.pj, ordinates.length, ordinates, 0, 1);
        if (ptDst != null) {
            if (ptDst.getDimension() != tgtDim) {
                throw new MismatchedDimensionException();
            }
            for (int i=0; i<tgtDim; i++) {
                ptDst.setOrdinate(i, ordinates[i]);
            }
        } else {
            if (ordinates.length != tgtDim) {
                ordinates = Arrays.copyOf(ordinates, tgtDim);
            }
            ptDst = new SimpleDirectPosition(ordinates);
        }
        return ptDst;
    }

    /**
     * Transforms an array of coordinate tuples.
     */
    @Override
    public void transform(final double[] srcPts, final int srcOff,
                          final double[] dstPts, final int dstOff,
                          final int numPts) throws TransformException
    {
        final int srcDim = source.getDimension();
        final int tgtDim = target.getDimension();
        if (srcDim == tgtDim) {
            if (srcPts != dstPts || srcOff != dstOff) {
                final int length = tgtDim * numPts;
                System.arraycopy(srcPts, srcOff, dstPts, dstOff, length);
            }
        } else {
            // TODO: need special check for overlapping arrays.
            throw new TransformException("Transformation between CRS of different dimensions not yet supported.");
        }
        source.pj.transform(target.pj, tgtDim, dstPts, dstOff, numPts);
    }

    /**
     * Transforms an array of coordinate tuples.
     */
    @Override
    public void transform(final float[] srcPts, int srcOff,
                          final float[] dstPts, int dstOff,
                          int numPts) throws TransformException
    {
        if (numPts > 0) {
            final int srcDim = source.getDimension();
            final int tgtDim = target.getDimension();
            final int dimension = Math.min(srcDim, tgtDim);
            final int length = dimension * numPts;
            final double[] copy = new double[length];
            int skip = srcDim - dimension;
            int stop = (skip == 0) ? length : dimension;
            for (int i=0;;) {
                copy[i] = srcPts[srcOff + i];
                if (++i == stop) {
                    if (i == length) break;
                    srcOff += skip;
                    stop += dimension;
                }
            }
            source.pj.transform(target.pj, dimension, copy, 0, numPts);
            skip = tgtDim - dimension;
            stop = (skip == 0) ? length : dimension;
            for (int i=0;;) {
                dstPts[dstOff + i] = (float) copy[i];
                if (++i == stop) {
                    if (i == length) break;
                    dstOff += skip;
                    stop += dimension;
                }
            }
        }
    }

    /**
     * Transforms an array of coordinate tuples.
     */
    @Override
    public void transform(final float[]  srcPts, int srcOff,
                          final double[] dstPts, int dstOff,
                          final int numPts) throws TransformException
    {
        if (numPts > 0) {
            final int srcDim = source.getDimension();
            final int tgtDim = target.getDimension();
            final int dimension = Math.min(srcDim, tgtDim);
            final int skipS  = srcDim - dimension;
            final int skipT  = tgtDim - dimension;
            final int length = dimension * numPts;
            int stop = (skipS == 0 && skipT == 0) ? length : dimension;
            if (skipT != 0) {
                Arrays.fill(dstPts, dstOff, dstOff + tgtDim * numPts, Double.NaN);
            }
            for (int i=0;;) {
                dstPts[dstOff + i] = srcPts[srcOff + i];
                if (++i == stop) {
                    if (i == length) break;
                    srcOff += skipS;
                    dstOff += skipT;
                    stop += dimension;
                }
            }
            source.pj.transform(target.pj, tgtDim, dstPts, dstOff, numPts);
        }
    }

    /**
     * Transforms an array of coordinate tuples.
     */
    @Override
    public void transform(final double[] srcPts, int srcOff,
                          final float[]  dstPts, int dstOff,
                          final int numPts) throws TransformException
    {
        if (numPts > 0) {
            final int srcDim = source.getDimension();
            final int tgtDim = target.getDimension();
            final int dimension = Math.min(srcDim, tgtDim);
            final int length = dimension * numPts;
            final double[] copy;
            if (srcDim == dimension) {
                copy = Arrays.copyOfRange(srcPts, srcOff, srcOff + length);
            } else {
                copy = new double[length];
                for (int i=0; i!=length; i+=dimension) {
                    System.arraycopy(srcPts, srcOff, copy, i, dimension);
                    srcOff += srcDim;
                }
            }
            source.pj.transform(target.pj, dimension, copy, 0, numPts);
            final int skip = tgtDim - dimension;
            int stop = (skip == 0) ? length : dimension;
            for (int i=0;;) {
                dstPts[dstOff + i] = (float) copy[i];
                if (++i == stop) {
                    if (i == length) break;
                    dstOff += skip;
                    stop += dimension;
                }
            }
        }
    }

    /**
     * The Proj4 library does not provide derivative functions.
     */
    @Override
    public Matrix derivative(DirectPosition point) throws TransformException {
        return null;
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

    /**
     * A specialization of {@link PJOperation} for map projections.
     */
    static final class Projection extends PJOperation implements org.opengis.referencing.operation.Projection {
        Projection(final ReferenceIdentifier name, final PJCRS source, final PJCRS target) {
            super(name, source, target);
        }
    }
}
