/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2014 Open Geospatial Consortium, Inc.
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
package org.opengis.observation.sampling;

import org.opengis.geometry.Geometry;
import org.opengis.metadata.extent.GeographicDescription;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * Observations may be associated with a geospatial location. The primary location of
 * interest is usually associated with the ultimate feature-of-interest, so this is a principle
 * classifier of an observation and its result, used in indexing and discovery.
 *
 * However, the location may not be trivially available. For example: in remote sensing
 * applications, a complex processing chain is required to geolocate the scene or swath; in
 * feature-detection applications the initial observation may be made on a scene, but the
 * detected entity, which is the ultimate feature of interest, occupies some location within it.
 * The distinction between the proximate and ultimate feature of interest is a key
 * consideration in these cases (see sub-clauses 6.3.1 and O&amp;M-Part 2).
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="Location", specification=OGC_07022)
public interface Location {

    /**
     * @return Geometry : location geometry
     */
    @UML(identifier="geometryLocation", obligation=MANDATORY, specification=OGC_07022)
    Geometry getGeometryLocation();

    /**
     * @return GeographicDescription : named identified geographic area
     */
    @UML(identifier="nameLocation", obligation=MANDATORY, specification=OGC_07022)
    GeographicDescription getNameLocation();

}
