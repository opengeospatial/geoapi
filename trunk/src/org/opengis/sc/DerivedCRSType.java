/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

//J2SE direct dependencies
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

//OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * The type of the derived CRS, according to the classification of principal CRS types.
 *
 * @UML codelist SC_DerivedCRSType
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
    public static final DerivedCRSType GEOGRAPHIC = new DerivedCRSType(0, "geographic");

    /**
     * A coordinate reference system used for recording of heights or depths. Vertical
     * CRSs make use of the direction of gravity to define the concept of height or depth,
     * but the relationship with gravity may not be straightforward
     *
     * @UML conditional vertical
     */
    public static final DerivedCRSType VERTICAL = new DerivedCRSType(1, "vertical");

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
    public static final DerivedCRSType ENGINEERING = new DerivedCRSType(2, "engineering");

    /**
     * An engineering coordinate reference system applied to locations in images.
     *
     * @UML conditional image
     */
    public static final DerivedCRSType IMAGE = new DerivedCRSType(3, "image");

    /**
     * A coordinate reference system used for the recording of time.
     *
     * @UML conditional temporal
     */
    public static final DerivedCRSType TEMPORAL = new DerivedCRSType(4, "temporal");

    /**
     * List of all enumeration of this type.
     */
    private static final List FAMILY = Collections.unmodifiableList(Arrays.asList(new DerivedCRSType[] {
                GEOGRAPHIC, VERTICAL, ENGINEERING, IMAGE, TEMPORAL }));

    /**
     * Constructs an enum with the given name.
     */
    private DerivedCRSType(final int ordinal, final String name) {
        super(ordinal, name);
    }

    /**
     * Returns the list of <code>DerivedCRSType</code> codes.
     */
    public List family() {
        return FAMILY;
    }
}
