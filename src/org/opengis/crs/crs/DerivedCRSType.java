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

//OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * The type of the {@linkplain DerivedCRS derived CRS}, according to the classification of
 * principal CRS types. This specifies the allowable types of {@link CoordinateReferencSystem}s
 * that a {@link DerivedCRS} can be derived from.
 *
 * @UML codelist SC_DerivedCRSType
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public final class DerivedCRSType extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5189026148279410852L;

    /**
     * A coordinate reference system based on an ellipsoidal approximation of the geoid.
     * Provides an accurate representation of the geometry of geographic features for a
     * large portion of the earth's surface.
     *
     * @UML conditional geographic
     */
    public static final DerivedCRSType GEOGRAPHIC = new DerivedCRSType("GEOGRAPHIC", 0);

    /**
     * A coordinate reference system used for recording of heights or depths. Vertical
     * CRSs make use of the direction of gravity to define the concept of height or depth,
     * but the relationship with gravity may not be straightforward
     *
     * @UML conditional vertical
     */
    public static final DerivedCRSType VERTICAL = new DerivedCRSType("VERTICAL", 1);

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
    public static final DerivedCRSType ENGINEERING = new DerivedCRSType("ENGINEERING", 2);

    /**
     * An engineering coordinate reference system applied to locations in images.
     *
     * @UML conditional image
     */
    public static final DerivedCRSType IMAGE = new DerivedCRSType("IMAGE", 3);

    /**
     * A coordinate reference system used for the recording of time.
     *
     * @UML conditional temporal
     */
    public static final DerivedCRSType TEMPORAL = new DerivedCRSType("TEMPORAL", 4);

    /**
     * List of all enumeration of this type.
     */
    private static final DerivedCRSType[] VALUES = new DerivedCRSType[] {
                GEOGRAPHIC, VERTICAL, ENGINEERING, IMAGE, TEMPORAL };

    /**
     * Constructs an enum with the given name.
     */
    private DerivedCRSType(final String name, final int ordinal) {
        super(name, ordinal);
    }

    /**
     * Returns the list of <code>DerivedCRSType</code>s.
     */
    public static DerivedCRSType[] values() {
        return (DerivedCRSType[]) VALUES.clone();
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{DerivedCRSType}*/ CodeList[] family() {
        return values();
    }
}
