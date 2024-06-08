/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.observation.sampling;

import org.opengis.observation.Measure;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.referencing.datum.Datum;
import org.opengis.temporal.TemporalPrimitive;
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
    TemporalPrimitive getSurveyTime();

}
