/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

// OpenGIS direct dependencies
import org.opengis.cs.TemporalCS;
import org.opengis.cd.TemporalDatum;


/**
 * A 1D coordinate reference system used for the recording of time.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.cs.TemporalCS Temporal}
 * </TD></TR></TABLE>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface TemporalCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system, which must be temporal.
     *
     * @return The coordinate system.
     * @association usesCS
     *
     * @rename Expanded the "CS" abbreviation into "CoordinateSystem".
     *
     * @revisit Change the return type from <code>CoordinateSystem</code> to
     *          {@link TemporalCS} when the J2SE 1.5 compiler will be available.
     */
    public /*TemporalCS*/ org.opengis.cs.CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum, which must be temporal.
     *
     * @return The datum.
     * @association usesDatum
     *
     * @revisit Change the return type from <code>Datum</code> to
     *          {@link TemporalDatum} when the J2SE 1.5 compiler will be available.
     */
    public /*TemporalDatum*/ org.opengis.cd.Datum getDatum();
}
