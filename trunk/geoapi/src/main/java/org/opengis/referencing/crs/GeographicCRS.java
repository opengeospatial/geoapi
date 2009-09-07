/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A coordinate reference system based on an ellipsoidal approximation of the geoid; this provides
 * an accurate representation of the geometry of geographic features for a large portion of the
 * earth's surface.
 * <P>
 * A Geographic CRS is not suitable for mapmaking on a planar surface, because it describes geometry
 * on a curved surface. It is impossible to represent such geometry in a Euclidean plane without
 * introducing distortions. The need to control these distortions has given rise to the development
 * of the science of {@linkplain org.opengis.referencing.operation.Projection map projections}.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.EllipsoidalCS Ellipsoidal}
 * </TD></TR></TABLE>
 *
 * @departure historic
 *   This interface is kept conformant with the specification published in 2003. 
 *   The 2007 revision of ISO 19111 removed the <code>GeographicCRS</code> and 
 *   <code>GeocentricCRS</code> types, handling both using the <code>GeodeticCRS</code> parent type.
 *   GeoAPI keeps them since the distinction between those two types is in wide use.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 *
 * @navassoc 1 - - GeodeticDatum
 * @navassoc 1 - - EllipsoidalCS
 */
@UML(identifier="SC_GeographicCRS", specification=ISO_19111)
public interface GeographicCRS extends GeodeticCRS {
    /**
     * Returns the coordinate system, which must be ellipsoidal.
     */
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    EllipsoidalCS getCoordinateSystem();
}
