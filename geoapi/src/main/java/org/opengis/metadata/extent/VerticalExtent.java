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

import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Vertical domain of dataset.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @since   GeoAPI 1.0
 */
@UML(identifier="EX_VerticalExtent", specification=ISO_19115)
public interface VerticalExtent {
    /**
     * Returns the lowest vertical extent contained in the dataset.
     *
     * @return Double mandatory for valid content, may be null for an invalid document.
     */
    @UML(identifier="minimumValue", obligation=MANDATORY, specification=ISO_19115)
    Double getMinimumValue();

    /**
     * Returns the highest vertical extent contained in the dataset.
     *
     * @return Double mandatory for valid content, may be null for an invalid document.
     */
    @UML(identifier="maximumValue", obligation=MANDATORY, specification=ISO_19115)
    Double getMaximumValue();

    /**
     * Provides information about the vertical coordinate reference system to
     * which the maximum and minimum elevation values are measured. The CRS
     * identification includes unit of measure.
     *
     * @departure integration
     *   ISO 19115 specifies a generic <code>CoordinateReferenceSystem</code> instead than the more
     *   restrictive <code>VerticalCRS</code>. GeoAPI uses the more specific type for type-safety and
     *   consistency with <code>VerticalExtent</code> usage. However this restriction prevents usage
     *   of <cite>Height above the ellipsoid</cite> when only the constants defined in the
     *   <code>VerticalDatumType</code> code list are used. If such height is wanted, implementors
     *   need to extend the above code list with their own <code>ELLIPSOIDAL</code> constant.
     *
     * @issue http://jira.codehaus.org/browse/GEO-134
     *
     * @return The vertical CRS.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="verticalCRS", obligation=MANDATORY, specification=ISO_19115)
    VerticalCRS getVerticalCRS();
}
