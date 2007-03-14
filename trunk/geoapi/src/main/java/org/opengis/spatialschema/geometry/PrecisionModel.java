package org.opengis.spatialschema.geometry;

/**
 * Specifies the precision model of the DirectPoistions in a Geometry.
 * <p>
 * A PrevisionModel defines a grid allowable points, toPrecise allows you to round Describes the
 * collapsing behavior of a direct position.
 * <p>
 * PrecisionModel instances can be sorted by their maximum number of significant digits.
 * </p>
 * 
 * @author Jody
 */
public interface PrecisionModel extends Comparable {
    /**
     * Returns the maximum number of significant digits provided by this precision model..
     * <p>
     * Apparently this is usually used for output, note GML generation usually has its own concept
     * of significant digits. You may be able to capture this in terms of the getScale().
     * </p>
     * 
     * @return number of significant digits
     * @see getScale
     */
    int getMaximumSignificantDigits();

    /**
     * Multiplying factor used to obtain a precise ordinate.
     * <p>
     * Multiply by this value and then divide by this value to round correctly:
     * 
     * <pre><code>
     * return Math.round(value * pm.getScale()) / pm.getScale();
     * </code></pre>
     * 
     * </p>
     * <p>
     * So to round to 3<code>3 </code>ignificant digits we would have a scale of
     * <code>1000</code>
     * 
     * @return multiplying factor used before rounding
     */
    double getScale();

    /**
     * Type of precision model.
     * 
     * @return type of prevision model
     */
    PrecisionModelType getType();

    /**
     * Rounds a DirectPostion to this percision model in place.
     * <p>
     * It is likely that a PercisionModel instance will keep different rounding rules for diffrent
     * axis (example x & y ordinages may be handled differently then height), by always rounding a
     * DirectPosition as a unit we will enable this functionality.
     * </p>
     */
    void round( DirectPosition position );

}