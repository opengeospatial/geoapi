/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cs;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * The set of coordinate system axes that spans a given coordinate space. A coordinate system (CS)
 * is derived from a set of (mathematical) rules for specifying how coordinates in a given space
 * are to be assigned to points. The coordinate values in a coordinate tuple shall be recorded in
 * the order in which the coordinate system axes associations are recorded, whenever those
 * coordinates use a coordinate reference system that uses this coordinate system.
 *
 * @UML abstract CS_CoordinateSystem
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see org.opengis.cs.CoordinateSystemAxis
 * @see org.opengis.cs.UnitOfMeasure
 * @see org.opengis.cd.Datum
 * @see org.opengis.sc.CoordinateReferenceSystem
 */
public interface CoordinateSystem {
    /**
     * The name by which this coordinate system is identified. 
     *
     * @return The coordinate system name.
     * @UML mandatory csName
     *
     * @rename  Omitted the "<code>srs</code>" prefix,
     *          which stands as an abbreviation for this enclosing class.
     *
     * @revisit Should we ask for a (possibly null) {@link java.util.Locale} argument?
     */
    public String getName();

    /**
     * Set of alternative identifications of the coordinate system. The first identifier,
     * if any, is normally the primary identification code, and any others are aliases.
     *
     * @return Coordinate system identifiers, or an empty array if there is none.
     * @UML optional csID
     *
     * @rename  Omitted the "<code>srs</code>" prefix,
     *          which stands as an abbreviation for this enclosing class.
     *          Replaced "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

    /**
     * Returns the ordered set of axis for this coordinate system. Each coordinate system
     * must have at least one axis.
     *
     * @return The ordered set of axis.
     * @UML association usesAxis
     */
    public CoordinateSystemAxis[] getAxis();

    /**
     * Comments on or information about the coordinate system, including data source information.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return The coordinate system remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    public String getRemarks(Locale locale);
}
