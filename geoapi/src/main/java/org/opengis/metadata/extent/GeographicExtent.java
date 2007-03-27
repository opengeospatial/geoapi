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
package org.opengis.metadata.extent;

// Annotations
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.UML;


/**
 * Base interface for geographic area of the dataset.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="EX_GeographicExtent", specification=ISO_19115)
public interface GeographicExtent {
    /**
     * Indication of whether the bounding polygon encompasses an area covered by the data
     * (<cite>inclusion</cite>) or an area where data is not present (<cite>exclusion</cite>).
     *
     * @return {@code true} for inclusion, or {@code false} for exclusion.
     */
    @UML(identifier="extentTypeCode", obligation=OPTIONAL, specification=ISO_19115)
    boolean getInclusion();
}
