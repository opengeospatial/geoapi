/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;


/**
 * Grid with cells irregularly spaced in any given geographic/map projection coordinate
 * system, whose individual cells can be geolocated using geolocation information
 * supplied with the data but cannot be geolocated from the grid properties alone.
 *
 * @UML datatype MD_Georeferenceable
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Georeferenceable extends GridSpatialRepresentation {
    /**
     * Indication of whether or not control point(s) exists.
     *
     * @UML mandatory controlPointAvailability
     */
    boolean isControlPointAvailable();

    /**
     * Indication of whether or not orientation parameters are available.
     *
     * @UML mandatory orientationParameterAvailability
     */
    boolean isOrientationParameterAvailable();

    /**
     * Description of parameters used to describe sensor orientation.
     *
     * @UML optional orientationParameterDescription
     */
    InternationalString getOrientationParameterDescription();

    /**
     * Terms which support grid data georeferencing.
     *
     * @UML optional parameters
     *
     * @revisit Return type in UML is <code>Record</code>.
     */
    Object getParameters();

    /**
     * Reference providing description of the parameters.
     *
     * @UML optional parameterCitation
     */
    Set/*<Citation>*/ getParameterCitation();
}
