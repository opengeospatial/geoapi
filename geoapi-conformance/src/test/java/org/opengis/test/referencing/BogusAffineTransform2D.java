/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
