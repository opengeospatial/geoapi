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
package org.opengis.metadata.spatial;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Axis properties.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @author Cory Horner (Refractions Research)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_Dimension", specification=ISO_19115)
public interface Dimension {
    /**
     * Name of the axis.
     */
    @UML(identifier="dimensionName", obligation=MANDATORY, specification=ISO_19115)
    DimensionNameType getDimensionName();

    /**
     * Number of elements along the axis.
     * 
     * @return Integer mandatory for valid content, may be null for an invalid document.
     */
    @UML(identifier="dimensionSize", obligation=MANDATORY, specification=ISO_19115)
    Integer getDimensionSize();

    /**
     * Degree of detail in the grid dataset.
     *
     * @unitof Measure
     */
    @UML(identifier="resolution", obligation=OPTIONAL, specification=ISO_19115)
    Double getResolution();
}
