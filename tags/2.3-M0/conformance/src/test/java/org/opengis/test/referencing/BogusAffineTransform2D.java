/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test.referencing;

import java.awt.geom.AffineTransform;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.NoninvertibleTransformException;


/**
 * A math transform with intentional bugs.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
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
