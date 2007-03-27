/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;


/**
 * Encapsulates the inheritance attributes that can be applied to any
 * {@link org.opengis.go.display.primitive.Graphic}.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface Inheritance {
    /**
     * Default inherit style from parent value.
     */
    boolean DEFAULT_INHERIT_STYLE_FROM_PARENT = false;

    /**
     * Default override aggregated graphics value.
     */
    boolean DEFAULT_OVERRIDE_AGGREGATED_GRAPHICS = false;
    
    /**
     * Inherit style from parent attribute name.
     */
    String INHERIT_STYLE_FROM_PARENT = "INHERITANCE_INHERIT_STYLE_FROM_PARENT";

    /**
     * Override aggregated graphics attribute name.
     */
    String OVERRIDE_AGGREGATED_GRAPHICS = "INHERITANCE_OVERRIDE_AGGREGATED_GRAPHICS";
            
    /**
     * Returns the inherit style from parent value.
     *
     * @return the inherit style from parent value.
     */
    boolean isInheritingStyleFromParent();
    
    /**
     * Returns whether the inherit style from parent value has been set.
     *
     * @return <code>true</code> if the inherit style from parent value
     *         has been set, <code>false</code> otherwise.
     */    
    boolean isInheritingStyleFromParentSet();
    
    /**
     * Sets the inherit style from parent value.
     *
     * @param inheritStyleFromParent the inherit style from parent value.
     */    
    void setInheritingStyleFromParent(boolean inheritStyleFromParent);
    
    /**
     * Sets the fact that the inherit style from parent value has been set.
     *
     * @param flag <code>true</code> if the inherit style from parent value
     *             has been set, <code>false</code> otherwise.
     */    
    void setInheritingStyleFromParentSet(boolean flag);
 
    /**
     * Returns the override aggregated graphics value.
     *
     * @return the override aggregated graphics value.
     */
    boolean isOverridingAggregatedGraphics();
    
    /**
     * Returns whether the override aggregated graphics value has been set.
     *
     * @return <code>true</code> if the override aggregated graphics value
     *         has been set, <code>false</code> otherwise.
     */    
    boolean isOverridingAggregatedGraphicsSet();
    
    /**
     * Sets the override aggregated graphics value.
     *
     * @param overrideAggregatedGraphics the override aggregated graphics value.
     */    
    void setOverridingAggregatedGraphics(boolean overrideAggregatedGraphics);
    
    /**
     * Sets the fact that the override aggregated graphics value has been set.
     *
     * @param flag <code>true</code> if the override aggregated graphics value
     *             has been set, <code>false</code> otherwise.
     */    
    void setOverridingAggregatedGraphicsSet(boolean flag);
}
