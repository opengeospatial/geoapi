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
import org.opengis.geometry.DirectPosition;
import org.opengis.metadata.quality.Element;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information on ground control point.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_GCP", specification=ISO_19115_2)
public interface GCP {
    /**
     * Geographic or map position of the control point, in either two or three dimensions.
     *
     * @return Geographic or map position of the control point.
     */
    @UML(identifier="geographicCoordinates", obligation=MANDATORY, specification=ISO_19115_2)
    DirectPosition getGeographicCoordinates();

    /**
     * Accuracy of a ground control point.
     *
     * @return Accuracy of a ground control point.
     */
    @UML(identifier="accuracyReport", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Element> getAccuracyReports();
}
