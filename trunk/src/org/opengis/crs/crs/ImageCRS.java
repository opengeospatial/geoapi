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

// OpenGIS direct dependencies
import org.opengis.crs.cs.CartesianCS;
import org.opengis.crs.cs.ObliqueCartesianCS;
import org.opengis.crs.datum.ImageDatum;


/**
 * An engineering coordinate reference system applied to locations in images. Image coordinate
 * reference systems are treated as a separate sub-type because a separate user community exists
 * for images with its own terms of reference.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.crs.cs.CartesianCS        Cartesian},
 *   {@link org.opengis.crs.cs.ObliqueCartesianCS ObliqueCartesian}
 * </TD></TR></TABLE>
 *
 * @UML abstract SC_ImageCRS
 * @author ISO 19111
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
     * @UML association usesCartesianCS
     */
    public CartesianCS getCartesianCS();

    /**
     * Returns the oblique cartesian coordinate system.
     *
     * @return The oblique cartesian coordinate system, or <code>null</code> if none.
     * @UML association usesObliqueCartesianCS
     */
    public ObliqueCartesianCS getObliqueCartesianCS();

    /**
     * Returns the datum, which must be an image one.
     *
     * @return The datum.
     * @UML association usesDatum
     */
/// public ImageDatum getDatum();
}
