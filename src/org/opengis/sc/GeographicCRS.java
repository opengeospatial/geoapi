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
 * @UML abstract SC_GeographicCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface GeographicCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system, which must be ellipsoidal.
     *
     * @return The coordinate system.
     * @UML association usesCS
     */
    public EllipsoidalCS getCoordinateSystem();

    /**
     * Returns the datum, which must be geodetic.
     *
     * @return The datum.
     * @UML association usesDatum
     */
    public GeodeticDatum getDatum();
}
