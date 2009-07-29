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
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about a control point collection.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_GCPCollection", specification=ISO_19115_2)
public interface GCPCollection extends GeolocationInformation {
    /**
     * Identifier of the GCP collection.
     *
     * @return The identifier.
     */
    @UML(identifier="collectionIdentification", obligation=MANDATORY, specification=ISO_19115_2)
    Integer getCollectionIdentification();

    /**
     * Name of the GCP collection.
     *
     * @return Name of the GCP collection.
     */
    @UML(identifier="collectionName", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getCollectionName();

    /**
     * Coordinate system in which the ground control points are defined.
     *
     * @return Coordinate system in which the ground control points are defined.
     */
    @UML(identifier="coordinateReferenceSystem", obligation=MANDATORY, specification=ISO_19115_2)
    ReferenceSystem getCoordinateReferenceSystem();

    /**
     * Ground control point(s) used in the collection.
     *
     * @return Ground control point(s).
     */
    @UML(identifier="gcp", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends GCP> getGCPs();
}
