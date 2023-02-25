/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import org.opengis.metadata.distribution.DataFile;
import org.opengis.annotation.UML;
import org.opengis.metadata.content.CoverageDescription;
import org.opengis.metadata.distribution.Format;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.metadata.spatial.SpatialRepresentationType;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Result of a data quality measure organising the measured values as a coverage.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc 1 - - SpatialRepresentationType
 * @navassoc 1 - - SpatialRepresentation
 * @navassoc 1 - - CoverageDescription
 * @navassoc 1 - - Format
 * @navassoc 1 - - DataFile
 */
@UML(identifier="QE_CoverageResult", specification=ISO_19115_2)
public interface CoverageResult extends Result {
    /**
     * Method used to spatially represent the coverage result.
     *
     * @return Spatial representation of the coverage result.
     */
    @UML(identifier="spatialRepresentationType", obligation=MANDATORY, specification=ISO_19115_2)
    SpatialRepresentationType getSpatialRepresentationType();

    /**
     * Provides the digital representation of data quality measures composing the coverage result.
     *
     * @return Digital representation of data quality measures composing the coverage result.
     */
    @UML(identifier="resultSpatialRepresentation", obligation=MANDATORY, specification=ISO_19115_2)
    SpatialRepresentation getResultSpatialRepresentation();

    /**
     * Provides the description of the content of the result coverage, i.e. semantic definition
     * of the data quality measures.
     *
     * @return Description of the content of the result coverage.
     */
    @UML(identifier="resultContentDescription", obligation=MANDATORY, specification=ISO_19115_2)
    CoverageDescription getResultContentDescription();

    /**
     * Provides information about the format of the result coverage data.
     *
     * @return Format of the result coverage data.
     */
    @UML(identifier="resultFormat", obligation=MANDATORY, specification=ISO_19115_2)
    Format getResultFormat();

    /**
     * Provides information about the data file containing the result coverage data.
     *
     * @return Data file containing the result coverage data.
     */
    @UML(identifier="resultFile", obligation=MANDATORY, specification=ISO_19139)
    DataFile getResultFile();
}
