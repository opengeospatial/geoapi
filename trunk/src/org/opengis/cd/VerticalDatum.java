/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;


/**
 * A textual description and/or a set of parameters identifying a particular reference level
 * surface used as a zero-height surface. The description includes its position with respect
 * to the Earth for any of the height types recognized by this standard. There are several
 * types of Vertical Datums, and each may place constraints on the
 * {@linkplain org.opengis.cs.CoordinateSystemAxis Coordinate Axis} with which
 * it is combined to create a {@linkplain org.opengis.sc.VerticalCRS Vertical CRS}.
 *
 * @UML abstract CD_VerticalDatum
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface VerticalDatum extends Datum {
    /**
     * The type of this vertical datum. Default is “geoidal”.
     *
     * @return The type of this vertical datum.
     * @UML mandatory vertDatumType
     */
    public VerticalDatumType getVerticalDatumType();
}
