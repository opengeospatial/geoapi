/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/spatial/GridSpatialRepresentation.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

// J2SE direct dependencies
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.List;

import org.opengis.annotation.UML;


/**
 * Basic information required to uniquely identify a resource or resources.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_GridSpatialRepresentation", specification=ISO_19115)
public interface GridSpatialRepresentation extends SpatialRepresentation {
    /**
     * Number of independent spatial-temporal axes.
     */
    @UML(identifier="numberOfDimensions", obligation=MANDATORY, specification=ISO_19115)
    int getNumberOfDimensions();

    /**
     * Information about spatial-temporal axis properties.
     */
    @UML(identifier="axisDimensionsProperties", obligation=MANDATORY, specification=ISO_19115)
    List<Dimension> getAxisDimensionsProperties();

    /**
     * Identification of grid data as point or cell.
     */
    @UML(identifier="cellGeometry", obligation=MANDATORY, specification=ISO_19115)
    CellGeometry getCellGeometry();

    /**
     * Indication of whether or not parameters for transformation exists.
     */
    @UML(identifier="transformationParameterAvailability", obligation=MANDATORY, specification=ISO_19115)
    boolean isTransformationParameterAvailable();
}
