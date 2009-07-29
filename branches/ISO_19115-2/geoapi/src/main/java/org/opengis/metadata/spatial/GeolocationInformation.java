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
package org.opengis.metadata.spatial;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.metadata.quality.DataQuality;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information used to determine geographic location corresponding to image location.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_GeolocationInformation", specification=ISO_19115_2)
public interface GeolocationInformation {
    /**
     * Provides an overall assessment of quality of geolocation information.
     *
     * @return An overall assessment of quality of geolocation information.
     */
    @UML(identifier="qualityInfo", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends DataQuality> getQualityInfo();
}
