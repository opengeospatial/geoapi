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


/**
 * Axis properties.
 *
 * @UML datatype MD_Dimension
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Dimension {
    /**
     * Name of the axis.
     *
     * @UML mandatory dimensionName
     */
    DimensionNameType getDimensionName();

    /**
     * Number of elements along the axis.
     *
     * @UML mandatory dimensionSize
     */
    int getDimensionSize();

    /**
     * Degree of detail in the grid dataset.
     *
     * @UML optional resolution
     * @unitof Measure
     */
    double getResolution();
}
