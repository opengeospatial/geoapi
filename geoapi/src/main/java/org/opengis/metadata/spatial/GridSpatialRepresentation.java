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
