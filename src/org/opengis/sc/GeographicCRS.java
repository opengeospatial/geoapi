/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

// OpenGIS direct dependencies
import org.opengis.cs.EllipsoidalCS;
import org.opengis.cd.GeodeticDatum;


/**
 * A coordinate reference system based on an ellipsoidal approximation of the geoid; this provides
 * an accurate representation of the geometry of geographic features for a large portion of the
 * earth's surface.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.cs.EllipsoidalCS Ellipsoidal}
 * </TD></TR></TABLE>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface GeographicCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system, which must be ellipsoidal.
     *
     * @return The coordinate system.
     * @association usesCS
     *
     * @rename Expanded the "CS" abbreviation into "CoordinateSystem".
     *
     * @revisit Change the return type from <code>CoordinateSystem</code> to
     *          {@link EllipsoidalCS} when the J2SE 1.5 compiler will be available.
     */
    public /*EllipsoidalCS*/ org.opengis.cs.CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum, which must be geodetic.
     *
     * @return The datum.
     * @association usesDatum
     *
     * @revisit Change the return type from <code>Datum</code> to
     *          {@link GeodeticDatum} when the J2SE 1.5 compiler will be available.
     */
    public /*GeodeticDatum*/ org.opengis.cd.Datum getDatum();
}
