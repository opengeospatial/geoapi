/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.spatial;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Grid with cells irregularly spaced in any given geographic/projected coordinate reference system.
 * Individual cells can be geolocated using geolocation information supplied with the data but cannot
 * be geolocated from the grid properties alone.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_Georeferenceable", specification=ISO_19115)
public interface Georeferenceable extends GridSpatialRepresentation {
    /**
     * Indication of whether or not control point(s) exists.
     *
     * @return whether or not control point(s) exists.
     */
    @UML(identifier="controlPointAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isControlPointAvailable();

    /**
     * Indication of whether or not orientation parameters are available.
     *
     * @return whether or not orientation parameters are available.
     */
    @UML(identifier="orientationParameterAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isOrientationParameterAvailable();

    /**
     * Description of parameters used to describe sensor orientation.
     *
     * @return description of parameters used to describe sensor orientation, or {@code null}.
     */
    @UML(identifier="orientationParameterDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getOrientationParameterDescription();

    /**
     * Terms which support grid data georeferencing.
     *
     * @return terms which support grid data georeferencing.
     *
     * @since 2.1
     */
    @UML(identifier="georeferencedParameters", obligation=MANDATORY, specification=ISO_19115)
    Record getGeoreferencedParameters();

    /**
     * Reference providing description of the parameters.
     *
     * @return reference providing description of the parameters.
     */
    @UML(identifier="parameterCitation", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getParameterCitations();

    /**
     * Information that can be used to geolocate the data.
     *
     * @return a geolocalisation of the data.
     *
     * @since 2.3
     */
    @UML(identifier="geolocationInformation", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends GeolocationInformation> getGeolocationInformation();
}
