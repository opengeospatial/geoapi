/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

// OpenGIS direct dependencies
import org.opengis.cs.CoordinateSystem;
import org.opengis.cd.EngineeringDatum;


/**
 * A contextually local coordinate reference system. It can be divided into two broad categories:
 * <ul>
 *   <li>earth-fixed systems applied to engineering activities on or near the surface of the
 *       earth;</li>
 *   <li>CRSs on moving platforms such as road vehicles, vessels, aircraft, or spacecraft.</li>
 * </ul>
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.cs.CartesianCS        Cartesian},
 *   {@link org.opengis.cs.ObliqueCartesianCS ObliqueCartesian},
 *   {@link org.opengis.cs.EllipsoidalCS      Ellipsoidal},
 *   {@link org.opengis.cs.SphericalCS        Spherical},
 *   {@link org.opengis.cs.CylindricalCS      Cylindrical},
 *   {@link org.opengis.cs.PolarCS            Polar},
 *   {@link org.opengis.cs.VerticalCS         Vertical},
 *   {@link org.opengis.cs.LinearCS           Linear}
 * </TD></TR></TABLE>
 *
 * @UML abstract SC_EngineeringCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface EngineeringCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system.
     *
     * @return The coordinate system.
     * @UML association usesCS
     *
     * @revisit This method was already defined in {@link CoordinateReferenceSystem}.
     *          Why is it defined again here?
     */
    public CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum, which must be an engineering one.
     *
     * @return The datum.
     * @UML association usesDatum
     */
    public EngineeringDatum getDatum();
}

