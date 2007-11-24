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

import javax.units.Unit;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.datum.VerticalDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Vertical domain of dataset.
 *
 * @author <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @author Cory Horner (Refractions Research)
 * @since GeoAPI 1.0
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
     * Returns the vertical units used for vertical extent information.
     * Examples: metres, feet, millimetres, hectopascals.
     *
     * @deprecated removed from ISO_19115:2003/Cor.1:2006
     */
    @UML(identifier="unitOfMeasure", obligation=MANDATORY, specification=ISO_19115)
    Unit getUnit();

    /**
     * Provides information about the origin from which the
     * maximum and minimum elevation values are measured.
     *
     * @deprecated changed to {@link #getVerticalCRS} in ISO_19115:2003/Cor.1:2006
     */
    @UML(identifier="verticalDatum", obligation=MANDATORY, specification=ISO_19115)
    VerticalDatum getVerticalDatum();

    /**
     * Provides information about the vertical coordinate reference system to
     * which the maximum and minimum elevation values are measured. The CRS
     * identification includes unit of measure.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="verticalCRS", obligation=MANDATORY, specification=ISO_19115)
    VerticalCRS getVerticalCRS();
}
