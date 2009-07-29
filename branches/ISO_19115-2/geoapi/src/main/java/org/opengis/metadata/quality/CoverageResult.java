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
 * @author Cédric Briançon (Geomatys)
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
