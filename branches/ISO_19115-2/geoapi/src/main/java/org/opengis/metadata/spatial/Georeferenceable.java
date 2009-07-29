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
import org.opengis.util.InternationalString;
import org.opengis.util.Record;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Grid with cells irregularly spaced in any given geographic/map projection coordinate
 * system, whose individual cells can be geolocated using geolocation information
 * supplied with the data but cannot be geolocated from the grid properties alone.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @since   GeoAPI 2.0
 */
@UML(identifier="MD_Georeferenceable", specification=ISO_19115)
public interface Georeferenceable extends GridSpatialRepresentation {
    /**
     * Indication of whether or not control point(s) exists.
     *
     * @return Whether or not control point(s) exists.
     */
    @UML(identifier="controlPointAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isControlPointAvailable();

    /**
     * Indication of whether or not orientation parameters are available.
     *
     * @return Whether or not orientation parameters are available.
     */
    @UML(identifier="orientationParameterAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isOrientationParameterAvailable();

    /**
     * Description of parameters used to describe sensor orientation.
     *
     * @return Description of parameters used to describe sensor orientation, or {@code null}.
     */
    @UML(identifier="orientationParameterDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getOrientationParameterDescription();

    /**
     * Terms which support grid data georeferencing.
     *
     * @return Terms which support grid data georeferencing.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="georeferencedParameters", obligation=MANDATORY, specification=ISO_19115)
    Record getGeoreferencedParameters();

    /**
     * Reference providing description of the parameters.
     *
     * @return Reference providing description of the parameters.
     */
    @UML(identifier="parameterCitation", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getParameterCitations();

    /**
     * Information that can be used to geolocate the data.
     *
     * @return A geolocalisation of the data.
     *
     * @since GeoAPI 2.3
     */
    @UML(identifier="geolocationInformation", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends GeolocationInformation> getGeolocationInformation();
}
