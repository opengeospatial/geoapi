/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * Geometric figure that can be used to describe the approximate shape of the earth.
 * In mathematical terms, it is a surface formed by the rotation of an ellipse about
 * its minor axis.
 *
 * @UML abstract CD_Ellipsoid
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface Ellipsoid {
    /**
     * The name by which this ellipsoid is identified.
     *
     * @return The ellipsoid name.
     * @UML mandatory ellipsoidName
     *
     * @rename  Omitted the "<code>ellipsoid</code>" prefix.
     */
    public String getName();

    /**
     * Set of alternative identifications of this ellipsoid. The first identifier, if
     * any, is normally the primary identification code, and any others are aliases.
     *
     * @return  The ellipsoid identifiers, or an empty array if there is none.
     * @UML optional ellipsoidID
     *
     * @rename  Omitted the "<code>ellipsoid</code>" prefix.
     *          Replaced "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

    /**
     * Length of the semi-major axis of the ellipsoid.
     *
     * @return Length of semi-major axis.
     * @UML mandatory semiMajorAxis
     *
     * @revisit In UML, the return type for this method is <code>Length</code>.
     */
    public double getSemiMajorAxis();

    /**
     * Definition of the second parameter which describes the shape of this ellipsoid.
     *
     * @return Second defining parameter.
     * @UML mandatory secondDefiningParameter
     *
     * @revisit In UML, the return type for this method is <code>SecondDefiningParameter</code>,
     *          which is an union of <code>Scale</code> and <code>Length</code> values. Why not
     *          adopt the "Coordinate Transformation Services implementation specification" API
     *          instead?
     */
    public double getSecondDefiningParameter();

    /**
     * Comments on or information about this ellipsoid, including source information.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return Ellipsoid remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    public String getRemarks(Locale locale);
}
