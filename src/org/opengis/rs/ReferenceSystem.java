/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.rs;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.ex.Extent;


/**
 * Description of a spatial and temporal reference system used by a dataset.
 *  
 * @UML abstract RS_ReferenceSystem
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see org.opengis.sc.CoordinateReferenceSystem
 */
public interface ReferenceSystem {
    /**
     * The name by which this reference system is uniquely identified.
     *
     * @return The reference system name.
     * @UML mandatory srsName
     *
     * @rename  Omitted the "<code>srs</code>" prefix,
     *          which stands as an abbreviation for this enclosing class.
     *
     * @revisit Should we ask for a (possibly null) {@link java.util.Locale} argument?
     */
    public String getName();

    /**
     * Set of alternative identifications of this reference system. The first identifier,
     * if any, is normally the primary identification code, and any others are aliases.
     *
     * @return Coordinate reference system identifiers, or an empty array if there is none.
     * @UML optional srsID
     *
     * @rename  Omitted the "<code>srs</code>" prefix,
     *          which stands as an abbreviation for this enclosing class.
     *          Replaced "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

    /**
     * Area for which the (coordinate) reference system is valid.
     *
     * @return Coordinate reference system valid area, or <code>null</code> if not available.
     * @UML optional validArea
     *
     * @revisit The method name <code>getValidExtent()</code> would work better with time
     *          reference systems since their validity holds across a non-spatial extent.
     */
    public Extent getValidArea();

    /**
     * Description of domain of usage, or limitations of usage, for which this
     * (coordinate) reference system object is valid.
     *
     * @param  locale The desired locale for the scope to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return Coordinate reference system scope, or <code>null</code> if not available.
     * @UML optional scope
     */
    public String getScope(Locale locale);

    /**
     * Comments on or information about this (coordinate) reference system,
     * including data source information.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return Coordinate reference system remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    public String getRemarks(Locale locale);
}
