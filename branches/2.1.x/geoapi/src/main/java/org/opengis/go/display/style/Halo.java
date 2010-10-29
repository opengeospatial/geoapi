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
 * Encapsulates the halo radius that can be applied to any text Graphic.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface Halo {
    /**
     * Halo radius attribute name.
     */
    String RADIUS = "HALO_RADIUS";

    /**
     * Returns the halo radius value.
     * @return the value of the halo radius.
     */
    float getRadius();

    /**
     * Returns whether the halo radius value has been set.
     * @return true if the halo radius value has been set, false otherwise.
     */
    boolean isRadiusSet();

    /**
     * Sets the halo radius value.
     * @param haloRadius the value of the halo radius.
     */
    void setRadius(float haloRadius);

    /**
     * Sets the fact that the halo radius value has been set.
     * @param flag true if the halo radius value has been set, false otherwise.
     */
    void setRadiusSet(boolean flag);
}