/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.extent;

// J2SE dextensions
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.referencing.datum.VerticalDatum;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Vertical domain of dataset.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="EX_VerticalExtent")
public interface VerticalExtent {
    /**
     * Returns the lowest vertical extent contained in the dataset.
     */
/// @UML (identifier="minimumValue", obligation=MANDATORY)
    double getMinimumValue();

    /**
     * Returns the highest vertical extent contained in the dataset.
     */
/// @UML (identifier="maximumValue", obligation=MANDATORY)
    double getMaximumValue();

    /**
     * Returns the vertical units used for vertical extent information.
     * Examples: metres, feet, millimetres, hectopascals.
     */
/// @UML (identifier="unitOfMeasure", obligation=MANDATORY)
    Unit getUnit();

    /**
     * Provides information about the origin from which the
     * maximum and minimum elevation values are measured.
     */
/// @UML (identifier="verticalDatum", obligation=MANDATORY)
    VerticalDatum getVerticalDatum();
}
