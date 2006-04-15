/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.referencing.cs.VerticalCS; 	 
import org.opengis.referencing.datum.VerticalDatum;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


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
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author ISO/DIS 19111
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="SC_VerticalCRS", specification=ISO_19111)
public interface VerticalCRS extends SingleCRS {
    /**
     * Returns the coordinate system, which must be vertical.
     */
/// @UML(identifier="usesCS", obligation=MANDATORY, specification=ISO_19111)
/// VerticalCS getCoordinateSystem();

    /**
     * Returns the datum, which must be vertical.
     */
/// @UML(identifier="usesDatum", obligation=MANDATORY, specification=ISO_19111)
/// VerticalDatum getDatum();
}
