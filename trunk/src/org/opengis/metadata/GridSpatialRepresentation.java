/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;


/**
 * Basic information required to uniquely identify a resource or resources.
 *
 * @UML datatype MD_GridSpatialRepresentation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface GridSpatialRepresentation extends SpatialRepresentation {
    /**
     * Number of independent spatial-temporal axes.
     *
     * @UML mandatory numberOfDimensions
     */
    int getNumberOfDimensions();

    /**
     * Information about spatial-temporal axis properties.
     *
     * @UML mandatory axisDimensionsProperties
     */
//    Dimension[] getAxisDimensionsProperties();

    /**
     * Identification of grid data as point or cell.
     *
     * @UML mandatory cellGeometry
     */
    CellGeometry getCellGeometry();

    /**
     * Indication of whether or not parameters for transformation exists.
     *
     * @UML mandatory transformationParameterAvailability
     */
    boolean isTransformationParameterAvailable();
}
