/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
import java.util.Collections;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basic information required to uniquely identify a resource or resources.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_GridSpatialRepresentation", specification=ISO_19115)
public interface GridSpatialRepresentation extends SpatialRepresentation {
    /**
     * Number of independent spatial-temporal axes.
     *
     * @return number of independent spatial-temporal axes.
     */
    @UML(identifier="numberOfDimensions", obligation=MANDATORY, specification=ISO_19115)
    Integer getNumberOfDimensions();

    /**
     * Information about spatial-temporal axis properties.
     *
     * @return information about spatial-temporal axis properties.
     */
    @UML(identifier="axisDimensionProperties", obligation=OPTIONAL, specification=ISO_19115)
    default List<? extends Dimension> getAxisDimensionProperties() {
        return Collections.emptyList();
    }

    /**
     * Identification of grid data as point or cell.
     *
     * @return identification of grid data as point or cell.
     */
    @UML(identifier="cellGeometry", obligation=MANDATORY, specification=ISO_19115)
    CellGeometry getCellGeometry();

    /**
     * Indication of whether or not parameters for transformation exists.
     *
     * @return whether or not parameters for transformation exists.
     */
    @UML(identifier="transformationParameterAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isTransformationParameterAvailable();
}
