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


/**
 * Vertical domain of dataset.
 *
 * @UML abstract EX_VerticalExtent
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface VerticalExtent {
    /**
     * Returns the lowest vertical extent contained in the dataset.
     *
     * @UML mandatory minimumValue
     */
    double getMinimumValue();

    /**
     * Returns the highest vertical extent contained in the dataset.
     *
     * @UML mandatory maximumValue
     */
    double getMaximumValue();

    /**
     * Returns the vertical units used for vertical extent information.
     * Examples: metres, feet, millimetres, hectopascals.
     *
     * @UML mandatory unitOfMeasure
     */
    Unit getUnit();

    /**
     * Provides information about the origin from which the
     * maximum and minimum elevation values are measured.
     *
     * @UML mandatory verticalDatum
     */
    VerticalDatum getVerticalDatum();
}
