/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

// OpenGIS direct dependencies
import org.opengis.cs.CartesianCS;
import org.opengis.cs.ObliqueCartesianCS;
import org.opengis.cd.ImageDatum;


/**
 * An engineering coordinate reference system applied to locations in images. Image coordinate
 * reference systems are treated as a separate sub-type because a separate user community exists
 * for images with its own terms of reference.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.cs.CartesianCS        Cartesian},
 *   {@link org.opengis.cs.ObliqueCartesianCS ObliqueCartesian}
 * </TD></TR></TABLE>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit <ul>
 *            <li>If this is an engineering CRS, why it doesn't extends {@link EngineeringCRS}?</li>
 *            <li>This interface defines three methods for querying the coordinate system:
 *                {@link #getCoordinateSystem() getCoordinateSystem()}, {@link #getCartesianCS()}
 *                and {@link #getObliqueCartesianCS()}. What is the relationship between those three
 *                methods? Should only one of them be valid at a given time? Why not just stick with
 *                <code>getCoordinateSystem()</code> and lets the user cast the return type?</li>
 *          </ul>
 */
public interface ImageCRS extends CoordinateReferenceSystem {
    /**
     * Returns the cartesian coordinate system.
     *
     * @return The cartesian coordinate system, or <code>null</code> if none.
     * @association usesCartesianCS
     */
    public CartesianCS getCartesianCS();

    /**
     * Returns the oblique cartesian coordinate system.
     *
     * @return The oblique cartesian coordinate system, or <code>null</code> if none.
     * @association usesObliqueCartesianCS
     */
    public ObliqueCartesianCS getObliqueCartesianCS();

    /**
     * Returns the datum, which must be an image one.
     *
     * @return The datum.
     * @association usesDatum
     *
     * @revisit Change the return type from <code>Datum</code> to
     *          {@link ImageDatum} when the J2SE 1.5 compiler will be available.
     */
    public /*ImageDatum*/ org.opengis.cd.Datum getDatum();
}
