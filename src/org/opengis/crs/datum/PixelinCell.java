/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.datum;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Specification of the way the image grid is associated with the image data attributes.
 *
 * @UML codelist CD_PixelinCell
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public final class PixelinCell extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2857889370030758462L;

    /**
     * The origin of the image coordinate system is the centre of a grid cell or image pixel.
     *
     * @UML conditional cell&nbsp;center
     */
    public static final PixelinCell CELL_CENTER = new PixelinCell("CELL_CENTER", 0);

    /**
     * The origin of the image coordinate system is the corner of a grid cell, or half-way
     * between the centres of adjacent image pixels.
     *
     * @UML conditional cell&nbsp;corner
     */
    public static final PixelinCell CELL_CORNER = new PixelinCell("CELL_CORNER", 1);

    /**
     * List of all enumerations of this type.
     */
    private static final PixelinCell[] VALUES = new PixelinCell[] {
            CELL_CENTER, CELL_CORNER };

    /**
     * Constructs an enum with the given name.
     */
    private PixelinCell(final String name, final int ordinal) {
        super(name, ordinal);
    }

    /**
     * Returns the list of <code>PixelinCell</code>s.
     */
    public static PixelinCell[] values() {
        return (PixelinCell[]) VALUES.clone();
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{PixelinCell}*/ CodeList[] family() {
        return values();
    }
}
