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

import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.SphericalCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 3D coordinate reference system with the origin at the approximate centre of mass of the earth.
 * A geocentric CRS deals with the earth's curvature by taking a 3D spatial view, which obviates
 * the need to model the earth's curvature.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.CartesianCS Cartesian},
 *   {@link org.opengis.referencing.cs.SphericalCS Spherical}
 * </TD></TR></TABLE>
 *
 * @departure
 *   This interface is conform to the specification published in 2003. Later revision of ISO 19111
 *   removed the <code>GeographicCRS</code> and <code>GeocentricCRS</code> types, which are both
 *   handled by the {@link GeodeticCRS} parent type. GeoAPI keeps them since the distinction between
 *   those two types is in wide use.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 */
@UML(identifier="SC_GeocentricCRS", specification=ISO_19111)
public interface GeocentricCRS extends GeodeticCRS {
    /**
     * Returns the coordinate system, which must be {@linkplain CartesianCS cartesian}
     * or {@linkplain SphericalCS spherical}.
     */
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystem getCoordinateSystem();
}
