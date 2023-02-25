/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.spatial;

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basic information required to uniquely identify a resource or resources.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - Dimension
 * @navassoc 1 - - CellGeometry
 */
@UML(identifier="MD_GridSpatialRepresentation", specification=ISO_19115)
public interface GridSpatialRepresentation extends SpatialRepresentation {
    /**
     * Number of independent spatial-temporal axes.
     *
     * @return Number of independent spatial-temporal axes.
     */
    @UML(identifier="numberOfDimensions", obligation=MANDATORY, specification=ISO_19115)
    Integer getNumberOfDimensions();

    /**
     * Information about spatial-temporal axis properties.
     *
     * @return Information about spatial-temporal axis properties.
     */
    @UML(identifier="axisDimensionProperties", obligation=MANDATORY, specification=ISO_19115)
    List<? extends Dimension> getAxisDimensionProperties();

    /**
     * Identification of grid data as point or cell.
     *
     * @return Identification of grid data as point or cell.
     */
    @UML(identifier="cellGeometry", obligation=MANDATORY, specification=ISO_19115)
    CellGeometry getCellGeometry();

    /**
     * Indication of whether or not parameters for transformation exists.
     *
     * @return Whether or not parameters for transformation exists.
     */
    @UML(identifier="transformationParameterAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isTransformationParameterAvailable();
}
