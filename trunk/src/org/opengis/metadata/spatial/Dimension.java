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

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Axis properties.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_Dimension")
public interface Dimension {
    /**
     * Name of the axis.
     */
/// @UML (identifier="dimensionName", obligation=MANDATORY)
    DimensionNameType getDimensionName();

    /**
     * Number of elements along the axis.
     */
/// @UML (identifier="dimensionSize", obligation=MANDATORY)
    int getDimensionSize();

    /**
     * Degree of detail in the grid dataset.
     *
     * @unitof Measure
     */
/// @UML (identifier="resolution", obligation=OPTIONAL)
    double getResolution();
}
