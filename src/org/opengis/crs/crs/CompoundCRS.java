/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.crs;


/**
 * A coordinate reference system describing the position of points through two or more
 * independent coordinate reference systems. Thus it is associated with two or more
 * {@linkplain org.opengis.crs.cs.CoordinateSystem Coordinate Systems} and
 * {@linkplain org.opengis.crs.datum.Datum Datums} by defining the compound CRS
 * as an ordered set of two or more instances of {@link CoordinateReferenceSystem}.
 *
 * @UML abstract SC_CompoundCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface CompoundCRS extends CoordinateReferenceSystem {
    /**
     * The ordered list of coordinate reference systems.
     *
     * @return The coordinate reference systems.
     * @UML association includesCRS
     */
    public CoordinateReferenceSystem[] getCoordinateReferenceSystem();
}
