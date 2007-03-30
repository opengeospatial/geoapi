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
 *  class MyRepresentedFraction implements Number, RepresentedFraction {
 *  ...
 *  public double doubleValue() {
 *      return 1.0 / (double) denominator;
 *  }
 *  public float floatValue() {
 *      return 1.0f / (float) denominator;
 *  }
 *  public long longValue() {
 *      return 0;
 *  }
 *  public int intValue() {
 *      return 0;
 *  }
 *  ...
 *  }
 * </code></pre>
 * 
 * </p>
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
     * RepresentativeFraction is a data object - equals is defined acoording to getDenominator;
     * <p>
     * Implementations should exactly match the following:
     * 
     * <pre><code>
     * public boolean equals( final Object object ) {
     *     if (object != null &amp;&amp; object instanceof RepresentativeFraction) {
     *         final RepresentativeFraction that = (RepresentativeFraction) object;
     *         return denominator == that.getDenominator();
     *     }
     *     return false;
     * }
     * </code></pre>
     * 
     * @param other
     * @return ture of other is a RepresentedFraction with the same getDenominator value
     */
    public boolean equals( Object other );
    /**
     * RepresentedFraction is a data object - hashcode defined according to getDenominator.
     * <p>
     * Implementations should exactly match the following:
     * 
     * <pre><code>
     * public int hashCode() {
     *     return getDenominator();
     * }
     * </code></pre>
     * 
     * @return hashcode for RepresentedFraction
     */
    public int hashCode();
}
