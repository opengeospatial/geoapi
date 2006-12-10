/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/spatial/Dimension.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

// Annotations
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;


/**
 * Axis properties.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_Dimension", specification=ISO_19115)
public interface Dimension extends MetadataEntity{
    /**
     * Name of the axis.
     */
    @UML(identifier="dimensionName", obligation=MANDATORY, specification=ISO_19115)
    DimensionNameType getDimensionName();

    /**
     * Number of elements along the axis.
     */
    @UML(identifier="dimensionSize", obligation=MANDATORY, specification=ISO_19115)
    int getDimensionSize();

    /**
     * Degree of detail in the grid dataset.
     *
     * @unitof Measure
     */
    @UML(identifier="resolution", obligation=OPTIONAL, specification=ISO_19115)
    double getResolution();
}
