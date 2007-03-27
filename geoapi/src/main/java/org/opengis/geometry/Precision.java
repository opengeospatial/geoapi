/*$************************************************************************************************
 **
 ** $Id: PrecisionModel.java 960 2007-03-16 00:44:53Z jive $
 **
 ** $URL: https://svn.sourceforge.net/svnroot/geoapi/trunk/geoapi/src/main/java/org/opengis/spatialschema/geometry/PrecisionModel.java $
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.geometry;


/**
 * Specifies the precision model of the {@linkplain DirectPosition direct positions}
 * in a {@linkplain Geometry geometry}.
 * <p>
 * A precision model defines a grid of allowable points. The {@link #round} method
 * allows to round a direct position to the nearest allowed point. The {@link #getType}
 * method describes the collapsing behavior of a direct position.
 * <p>
 * {@code PrecisionModel} instances can be sorted by their {@linkplain #getScale scale}.
 * </p>
 *
 * @author Jody Garnett
 * @since GeoAPI 2.1
 *
 * @todo Define a {@code compareTo} method for documentation purpose.
 */
public interface Precision extends Comparable<Precision> {
    /**
     * Sort PrecisionModel according to number of significant digits.
     * <p>
     * Implemented as:<pre><code>
     * return other.getMaximumSignificantDigits() - getMaximumSignificantDigits(); 
     * </code></pre>
     * 
     * @param other Other PrecisionModel to compare against
     * @return a negative integer, zero, or a positive integer as this object
     *      is less than, equal to, or greater than precision then other
     */
    public int compareTo(Precision other);
    
    /**
     * Returns the maximum number of significant digits provided by this precision model..
     * <p>
     * Apparently this is usually used for output, note GML generation usually has its own concept
     * of significant digits. You may be able to capture this in terms of the getScale().
     * </p>
     * 
     * @return number of significant digits
     * @see getScale()
     */
    int getMaximumSignificantDigits();

    /**
     * Multiplying factor used to obtain a precise ordinate.
     * <p>
     * Multiply by this value and then divide by this value to round correctly:
     * 
     * <blockquote><pre>
     * return Math.round(value * pm.getScale()) / pm.getScale();
     * </pre></blockquote>
     *
     * So to round to {@code 3} significant digits we would have a scale of {@code 1000}.
     * 
     * @return Multiplying factor used before rounding.
     */
    double getScale();

    /**
     * Returns the type of this precision model.
     */
    PrecisionType getType();

    /**
     * Rounds a direct position to this precision model in place.
     * <p>
     * It is likely that a PrecisionModel instance will keep different rounding rules for different
     * axis (example <var>x</var> & <var>y</var> ordinates may be handled differently then height),
     * by always rounding a direct position as a whole we will enable this functionality.
     */
    void round(DirectPosition position);
}
