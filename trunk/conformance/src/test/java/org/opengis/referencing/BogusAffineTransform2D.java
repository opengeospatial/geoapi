/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;


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
}
