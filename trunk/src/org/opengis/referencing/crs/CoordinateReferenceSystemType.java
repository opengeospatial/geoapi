/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

//OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * The type of the classification of principal CRS types. This specifies the 
 * allowable types of {@link CoordinateReferenceSystem}s.
 *
 * @UML codelist SC_CoordinateReferenceSystemType
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit This enumeration, as well as {@link DerivedCRSType}, duplicate
 *          <code>instanceof</code> work. Do we really need those enumerations?
 */
public final class CoordinateReferenceSystemType extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2597510779962798941L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(9);

    /**
     * A coordinate reference system based on an ellipsoidal approximation of the geoid.
     * Provides an accurate representation of the geometry of geographic features for a
     * large portion of the earth's surface.
     *
     * @UML conditional geographic
     */
    public static final CoordinateReferenceSystemType GEOGRAPHIC = new CoordinateReferenceSystemType("GEOGRAPHIC");

    /**
     * A coordinate reference system used for recording of heights or depths. Vertical
     * CRSs make use of the direction of gravity to define the concept of height or depth,
     * but the relationship with gravity may not be straightforward
     *
     * @UML conditional vertical
     */
    public static final CoordinateReferenceSystemType VERTICAL = new CoordinateReferenceSystemType("VERTICAL");

    /**
     * A contextually local coordinate reference system. It can be divided into two broad
     * categories:
     * <ul>
     *   <li>earth-fixed systems applied to engineering activities on or near the
     *       surface of the earth;</li>
     *   <li>CRSs on moving platforms such as road vehicles, vessels, aircraft, or spacecraft.</li>
     * </ul>
     *
     * @UML conditional engineering
     */
    public static final CoordinateReferenceSystemType ENGINEERING = new CoordinateReferenceSystemType("ENGINEERING");

    /**
     * An engineering coordinate reference system applied to locations in images.
     *
     * @UML conditional image
     */
    public static final CoordinateReferenceSystemType IMAGE = new CoordinateReferenceSystemType("IMAGE");

    /**
     * A coordinate reference system used for the recording of time.
     *
     * @UML conditional temporal
     */
    public static final CoordinateReferenceSystemType TEMPORAL = new CoordinateReferenceSystemType("TEMPORAL");

    /**
     * A coordinate reference system used for the recording of time.
     *
     * @UML conditional temporal
     */
    public static final CoordinateReferenceSystemType GEOCENTRIC = new CoordinateReferenceSystemType("GEOCENTRIC");

    /**
     * A coordinate reference system used for the recording of time.
     *
     * @UML conditional temporal
     */
    public static final CoordinateReferenceSystemType PROJECTED = new CoordinateReferenceSystemType("PROJECTED");

    /**
     * A coordinate reference system used for the recording of time.
     *
     * @UML conditional temporal
     */
    public static final CoordinateReferenceSystemType COMPOUND = new CoordinateReferenceSystemType("COMPOUND");

    /**
     * A coordinate reference system used for the recording of time.
     *
     * @UML conditional temporal
     */
    public static final CoordinateReferenceSystemType DERIVED = new CoordinateReferenceSystemType("DERIVED");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public CoordinateReferenceSystemType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>CoordinateReferenceSystemType</code>s.
     */
    public static CoordinateReferenceSystemType[] values() {
        synchronized (VALUES) {
            return (CoordinateReferenceSystemType[]) VALUES.toArray(new CoordinateReferenceSystemType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{CoordinateReferenceSystemType}*/ CodeList[] family() {
        return values();
    }
}
