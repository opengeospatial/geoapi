/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;

// J2SE direct dependencies
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Type of a vertical datum. 
 *
 * @UML codelist CD_VerticalDatumType
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public final class VerticalDatumType extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8161084528823937553L;

    /**
     * The zero value of the associated vertical coordinate system axis is defined to approximate
     * a constant potential surface, usually the geoid. Such a reference surface is usually
     * determined by a national or scientific authority, and is then a well-known, named datum.
     *
     * @UML conditional geoidal
     */
    public static final VerticalDatumType GEOIDAL = new VerticalDatumType(0, "geoidal");

    /**
     * The zero point of the vertical axis is defined by a surface that has meaning for the
     * purpose which the associated vertical measurements are used for. For hydrographic charts,
     * this is often a predicted nominal sea surface (i.e., without waves or other wind and current
     * effects) that occurs at low tide.
     *
     * @UML conditional depth
     */
    public static final VerticalDatumType DEPTH = new VerticalDatumType(1, "depth");

    /**
     * Atmospheric pressure is the basis for the definition of the origin of the
     * associated vertical coordinate system axis.
     *
     * @UML conditional barometric
     */
    public static final VerticalDatumType BAROMETRIC = new VerticalDatumType(2, "barometric");

    /**
     * In some cases, e.g. oil exploration and production, a geological feature, such as the top
     * or bottom of a geologically identifiable and meaningful subsurface layer, is used as a
     * vertical datum. Other variations to the above three vertical datum types may exist
     * and are all included in this type.
     *
     * @UML conditional other&nbsp;surface
     */
    public static final VerticalDatumType OTHER_SURFACE = new VerticalDatumType(3, "other surface");

    /**
     * List of all enumeration of this type.
     */
    private static final List VALUES = Collections.unmodifiableList(Arrays.asList(new VerticalDatumType[] {
            GEOIDAL, DEPTH, BAROMETRIC, OTHER_SURFACE }));

    /**
     * Constructs an enum with the given name.
     */
    private VerticalDatumType(int ordinal, String name) {
        super(ordinal, name);
    }

    /**
     * Returns the list of <code>VerticalDatumType</code>s.
     */
    public static List values() {
        return VALUES;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public List family() {
        return VALUES;
    }
}
