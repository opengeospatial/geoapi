package org.opengis.spatialschema.geometry;

/**
 * Specifies the precision model of the DirectPoistions in a Geometry.
 * <p>
 * A PrevisionModel defines a grid allowable points, toPrecise allows you
 * to round 
 * Describes the collapsing behavior of a direct position.
 * <p>
 * PrecisionModel instances can be sorted by their maximum number of
 * significant digits.
 * </p>
 * 
 * @author Jody
 */
public interface PrecisionModel extends Comparable {
    /**
     * Returns the maximum number of significant digits provided by this precision model.
     * @return number of significant digits
     */
    int getMaximumSignificantDigits();
    
    /**
     * Multiplying factor used to obtain a precise ordinate
     * @return multiplying factor
     */
    double getScale();
    /**
     * Type of precision model.
     * @return type of prevision model
     */
    PrecisionModelType getType();
    
    /**
     * True if precision model supports floating point
     * @return true if precision model supports floating point
     */
    boolean isFloating();
    
    /**
     * Rounds a DirectPosition to the PrecisionModel.
     * <p>
     * This method will return a copy of the provided position with
     * the values rounded according to this PrecisionModel.
     * @param position
     * @return rounded DirectPosition
     */
    DirectPosition toRounded( DirectPosition position );  
    
    /** Rounds a DirectPostion to this percision model in place. */
    void round( DirectPosition position );
}
