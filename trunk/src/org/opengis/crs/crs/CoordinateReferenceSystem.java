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
import org.opengis.crs.ReferenceSystem;
import org.opengis.crs.cs.CoordinateSystem;
import org.opengis.crs.datum.Datum;


/**
 * Abstract coordinate reference system, consisting of a single
 * {@linkplain CoordinateSystem Coordinate System} and a single
 * {@linkplain Datum Datum} (as opposed to {@linkplain CompoundCRS Compound CRS}).
 *
 * A coordinate reference system consists of an ordered sequence of coordinate system
 * axes that are related to the earth through a datum. A coordinate reference system
 * is defined by one datum and by one coordinate system. Most coordinate reference system
 * do not move relative to the earth, except for engineering coordinate reference systems
 * defined on moving platforms such as cars, ships, aircraft, and spacecraft.
 *
 * Coordinate reference systems are commonly divided into sub-types. The common classification
 * criterion for sub-typing of coordinate reference systems is the way in which they deal with
 * earth curvature. This has a direct effect on the portion of the earth's surface that can be
 * covered by that type of CRS with an acceptable degree of error. The exception to the rule is
 * the subtype "Temporal" which has been added by analogy.
 *
 * @UML abstract SC_CoordinateReferenceSystem
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see org.opengis.crs.cs.CoordinateSystem
 * @see org.opengis.crs.datum.Datum
 */
public interface CoordinateReferenceSystem extends ReferenceSystem {
    /**
     * Returns the coordinate system.
     *
     * @return The coordinate system.
     * @UML association usesCS
     *
     * @rename Expanded the "CS" abbreviation into "CoordinateSystem".
     */
    CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum.
     *
     * @return The datum.
     * @UML association usesDatum
     */
    Datum getDatum();
    
    /**
     * Returns true if the CoordinateReferenceSystems are the same.
     *
     * @param otherCrs the CoordinateReferenceSystem to compare this object to.
     * @return true if the two CoordinateReferenceSystems are the same.
     *
     * @deprecated This method is not really needed, since interfaces inherit
     * {@link java.lang.Object#equals} (even if interfaces are not classes, they inherit
     * all methods from {@link java.lang.Object} anyway as a special case). Consequently,
     * this declaration doesn't bring any new feature. It doesn't bring type safety neither,
     * because a call like «code»myCRS.equals(someDatum)</code> will compile successfully
     * because of the inherited <code>equals(Object)</code> method. This declaration may
     * actually be dangerous, because all implementations will be forced to override
     * <code>equals(CoordinateReferenceSystem)</code> and <code>equals(Object)</code>
     * in exactly the same way. Failing to do that may lead to unexpected behavior when
     * using this interface with the collection framework.
     */
    boolean equals(CoordinateReferenceSystem otherCrs);

    /**
     * Returns the type for this CoordinateReferenceSystem.
     *
     * @return the type for this CoordinateReferenceSystem.
     */
    CoordinateReferenceSystemType getCoordinateReferenceSystemType();

    /**
     * Returns the name of this CoordinateReferenceSystem.
     * @return the name of this CoordinateReferenceSystem.
     *
     * @deprecated This method was already declared in {@link org.opengis.crs.Info}.
     */
    String getName();

    /**
     * Returns the alias value for this CoordinateReferenceSystem.
     * @return the alias value for this CoordinateReferenceSystem.
     *
     * @deprecated This method was already declared in {@link org.opengis.crs.Info}.
     */
    String getAlias();

    /**
     * Returns the scope of this CoordinateReferenceSystem.
     * @return the scope of this CoordinateReferenceSystem.
     *
     * @deprecated This method was already declared in {@link ReferenceSystem}.
     */
    String getScope();

    /**
     * Returns the remarks of this CoordinateReferenceSystem.
     * @return the remarks of this CoordinateReferenceSystem.
     *
     * @deprecated This method was already declared in {@link org.opengis.crs.Info}.
     */
    String getRemarks();
}
