/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

// J2SE direct dependencies
import java.util.List;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Basic information required to uniquely identify a resource or resources.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_GridSpatialRepresentation")
public interface GridSpatialRepresentation extends SpatialRepresentation {
    /**
     * Number of independent spatial-temporal axes.
     */
/// @UML (identifier="numberOfDimensions", obligation=MANDATORY)
    int getNumberOfDimensions();

    /**
     * Information about spatial-temporal axis properties.
     */
/// @UML (identifier="axisDimensionsProperties", obligation=MANDATORY)
    List/*<Dimension>*/ getAxisDimensionsProperties();

    /**
     * Identification of grid data as point or cell.
     */
/// @UML (identifier="cellGeometry", obligation=MANDATORY)
    CellGeometry getCellGeometry();

    /**
     * Indication of whether or not parameters for transformation exists.
     */
/// @UML (identifier="transformationParameterAvailability", obligation=MANDATORY)
    boolean isTransformationParameterAvailable();
}
