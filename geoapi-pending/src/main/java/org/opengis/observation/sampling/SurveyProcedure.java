/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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

import org.opengis.observation.Measure;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.referencing.datum.Datum;
import org.opengis.temporal.TemporalObject;
import org.opengis.util.GenericName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * Specialized procedure related to surveying positions and locations.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="SurveyProcedure", specification=OGC_07022)
public interface SurveyProcedure {

    @UML(identifier="operator", obligation=OPTIONAL, specification=OGC_07022)
    ResponsibleParty getOperator();

    @UML(identifier="elevationDatum", obligation=OPTIONAL, specification=OGC_07022)
    Datum getElevationDatum();

    @UML(identifier="elevationMethod", obligation=OPTIONAL, specification=OGC_07022)
    org.opengis.observation.Process getElevationMethod();

    @UML(identifier="elevationAccuracy", obligation=OPTIONAL, specification=OGC_07022)
    Measure getElevationAccuracy();

    @UML(identifier="geodeticDatum", obligation=OPTIONAL, specification=OGC_07022)
    Datum getGeodeticDatum();

    @UML(identifier="positionMethod", obligation=MANDATORY, specification=OGC_07022)
    org.opengis.observation.Process getPositionMethod();

    @UML(identifier="positionAccuracy", obligation=OPTIONAL, specification=OGC_07022)
    Measure getPositionAccuracy();

    @UML(identifier="projection", obligation=OPTIONAL, specification=OGC_07022)
    GenericName getProjection() ;

    @UML(identifier="surveyTime", obligation=OPTIONAL, specification=OGC_07022)
    TemporalObject getSurveyTime();

}
