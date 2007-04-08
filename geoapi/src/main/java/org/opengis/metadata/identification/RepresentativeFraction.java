/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2007 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Derived from ISO 19103 Scale where {@linkplain #getDenominator denominator} = 1 / Scale.
 * {@code Measure} and {@code Scale.targetUnits} = {@code Scale.sourceUnits}.
 * <p>
 * Implementations are encouraged to extend {@link Number} in the following manner:
 * 
 * <pre></code>
 *  class MyRepresentedFraction extends Number implements RepresentedFraction {
 *      ...
 *      public double doubleValue() {
 *          return 1.0 / (double) denominator;
 *      }
 *      public float floatValue() {
 *          return 1.0f / (float) denominator;
 *      }
 *      public long longValue() {
 *          return 0;
 *      }
 *      public int intValue() {
 *          return 0;
 *      }
 *      ...
 *  }
 * </code></pre>
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @since GeoAPI 2.1
 */
@UML(identifier = "MD_RepresentativeFraction", specification = ISO_19115)
public interface RepresentativeFraction {
    /**
     * Returns this value in a form usable for computation.
     * 
     * @return <code>1.0 / (double) {@linkplain #getDenominator()}</code>
     */
    double toScale();

    /**
     * The number below the line in a vulgar fraction.
     * 
     * @todo Return type may need to be a {@code long}? Source interface seems to indicate such.
     */
    @UML(identifier = "denominator", obligation = MANDATORY, specification = ISO_19115)
    int getDenominator();

    /**
     * Compares this representative fraction with the specified object for equality.
     * {@code RepresentativeFraction} is a data object - {@code equals} is defined
     * acoording to {@link #getDenominator};
     * <p>
     * Implementations should exactly match the following:
     * 
     * <pre><code>
     * public boolean equals(final Object object) {
     *     if (object instanceof RepresentativeFraction) {
     *         final RepresentativeFraction that = (RepresentativeFraction) object;
     *         return denominator == that.getDenominator();
     *     }
     *     return false;
     * }
     * </code></pre>
     * 
     * @param other The object to compare with.
     * @return {@code true} if {@code other} is a {@code RepresentedFraction} with the same
     *         {@linkplain #getDenominator denominator} value.
     */
    boolean equals(Object other);

    /**
     * Returns a hash value for this representative fraction.
     * {@code RepresentativeFraction} is a data object - {@code hashcode} is defined
     * according to {@link #getDenominator}.
     * <p>
     * Implementations should exactly match the following:
     * 
     * <pre><code>
     * public int hashCode() {
     *     return getDenominator();
     * }
     * </code></pre>
     * 
     * @return A hash code value for this representative fraction.
     */
    int hashCode();
}
