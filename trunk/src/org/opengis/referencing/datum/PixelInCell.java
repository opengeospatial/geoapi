/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.datum;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Specification of the way the image grid is associated with the image data attributes.
 *
 * @UML codelist CD_PixelInCell
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @revisit Should we rename <code>PixelInCell</code>?
 */
public final class PixelInCell extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2857889370030758462L;

    /**
     * The origin of the image coordinate system is the centre of a grid cell or image pixel.
     *
     * @UML conditional cell&nbsp;center
     */
    public static final PixelInCell CELL_CENTER = new PixelInCell("CELL_CENTER", 0);

    /**
     * The origin of the image coordinate system is the corner of a grid cell, or half-way
     * between the centres of adjacent image pixels.
     *
     * @UML conditional cell&nbsp;corner
     */
    public static final PixelInCell CELL_CORNER = new PixelInCell("CELL_CORNER", 1);

    /**
     * List of all enumerations of this type.
     */
    private static final PixelInCell[] VALUES = new PixelInCell[] {
            CELL_CENTER, CELL_CORNER };

    /**
     * Constructs an enum with the given name.
     */
    private PixelInCell(final String name, final int ordinal) {
        super(name, ordinal);
    }

    /**
     * Returns the list of <code>PixelInCell</code>s.
     */
    public static PixelInCell[] values() {
        return (PixelInCell[]) VALUES.clone();
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{PixelInCell}*/ CodeList[] family() {
        return values();
    }
}
