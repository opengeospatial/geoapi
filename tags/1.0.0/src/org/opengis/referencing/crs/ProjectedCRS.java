/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.operation.Projection;

/**
 * A 2D coordinate reference system used to approximate the shape of the earth on a planar surface.
 * It is done in such a way that the distortion that is inherent to the approximation is carefully
 * controlled and known. Distortion correction is commonly applied to calculated bearings and
 * distances to produce values that are a close match to actual field values.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.CartesianCS Cartesian}
 * </TD></TR></TABLE>
 *
 * @UML abstract SC_ProjectedCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface ProjectedCRS extends GeneralDerivedCRS {
    /**
     * Returns the base coordinate reference system, which must be geographic.
     *
     * @return The base coordinate reference system.
     */
/// GeographicCRS getBaseCRS();

    /**
     * Returns the map projection from the {@linkplain #getBaseCRS base CRS} to this CRS.
     *
     * @return The projection to this CRS.
     */
/// Projection getConversionFromBase();

    /**
     * Returns the coordinate system, which must be cartesian.
     *
     * @return The coordinate system.
     * @UML association usesCS
     */
/// CartesianCS getCoordinateSystem();
}
