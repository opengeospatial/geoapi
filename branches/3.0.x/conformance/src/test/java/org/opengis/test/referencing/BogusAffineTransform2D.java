/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import java.awt.geom.AffineTransform;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.NoninvertibleTransformException;


/**
 * A math transform with intentional bugs.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
@SuppressWarnings("serial")
final class BogusAffineTransform2D extends AffineTransform2D {
    /**
     * {@code true} to cause this class to produces erroneous value in
     * {@link #transform(float[],int,float[],int,int)}.
     */
    boolean wrongFloatToFloat;

    /**
     * {@code true} to cause {@link #inverse} to be erroneous.
     */
    boolean wrongInverse;

    /**
     * Transforms the given array, introducing an erroneous value if
     * {@link #wrongFloatToFloat} is {@code true}.
     */
    @Override
    public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
        super.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        if (wrongFloatToFloat) {
            dstPts[numPts / 2] += 500f;
        }
    }

    /**
     * Returns the inverse of this transform, as an erroneous one if {@link #wrongInverse}
     * is {@code true}.
     */
    @Override
    public MathTransform2D inverse() throws NoninvertibleTransformException {
        MathTransform2D inverse = super.inverse();
        if (wrongInverse) {
            ((AffineTransform) inverse).translate(0, 5);
        }
        return inverse;
    }
}
