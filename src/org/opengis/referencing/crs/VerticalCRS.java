/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.referencing.cs.VerticalCS; 	 
import org.opengis.referencing.datum.VerticalDatum;


/**
 * A 1D coordinate reference system used for recording heights or depths. Vertical CRSs make use
 * of the direction of gravity to define the concept of height or depth, but the relationship with
 * gravity may not be straightforward. By implication, ellipsoidal heights (h) cannot be captured
 * in a vertical coordinate reference system. Ellipsoidal heights cannot exist independently, but
 * only as inseparable part of a 3D coordinate tuple defined in a geographic 3D coordinate
 * reference system.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.VerticalCS Vertical}
 * </TD></TR></TABLE>
 *
 * @UML abstract SC_VerticalCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface VerticalCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system, which must be vertical.
     *
     * @return The coordinate system.
     * @UML association usesCS
     */
/// VerticalCS getCoordinateSystem();

    /**
     * Returns the datum, which must be vertical.
     *
     * @return The datum.
     * @UML association usesDatum
     */
/// VerticalDatum getDatum();
}
