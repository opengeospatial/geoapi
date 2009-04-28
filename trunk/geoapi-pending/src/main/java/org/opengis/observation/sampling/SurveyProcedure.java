/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
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
    Process getElevationMethod();

    @UML(identifier="elevationAccuracy", obligation=OPTIONAL, specification=OGC_07022)
    Measure getElevationAccuracy();

    @UML(identifier="geodeticDatum", obligation=OPTIONAL, specification=OGC_07022)
    Datum getGeodeticDatum();

    @UML(identifier="positionMethod", obligation=MANDATORY, specification=OGC_07022)
    Process getPositionMethod();

    @UML(identifier="positionAccuracy", obligation=OPTIONAL, specification=OGC_07022)
    Measure getPositionAccuracy();

    @UML(identifier="projection", obligation=OPTIONAL, specification=OGC_07022)
    GenericName getProjection() ;

    @UML(identifier="surveyTime", obligation=OPTIONAL, specification=OGC_07022)
    TemporalObject getSurveyTime();

}