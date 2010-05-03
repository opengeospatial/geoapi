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
package org.opengis.metadata.lineage;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Distance between consistent parts of (centre, left side, right side) adjacent pixels.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="LE_NominalResolution", specification=ISO_19115_2)
public interface NominalResolution {
    /**
     * Distance between consistent parts of (centre, left side, right side) adjacent pixels
     * in the scan plane.
     *
     * @return Distance between consistent parts of adjacent pixels in the scan plane.
     *
     * @unitof Distance
     */
    @UML(identifier="scanningResolution", obligation=MANDATORY, specification=ISO_19115_2)
    Double getScanningResolution();

    /**
     * Distance between consistent parts of (centre, left side, right side) adjacent pixels
     * in the object space.
     *
     * @return Distance between consistent parts of adjacent pixels in the object space.
     *
     * @unitof Distance
     */
    @UML(identifier="groundResolution", obligation=MANDATORY, specification=ISO_19115_2)
    Double getGroundResolution();
}
