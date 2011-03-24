/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.referencing.datum;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specification of the way the image grid is associated with the image data attributes.
 * <p>
 * This code list is similar to {@link org.opengis.metadata.spatial.PixelOrientation}
 * except that the later is more clearly restricted to the two-dimensional case.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.metadata.spatial.PixelOrientation
 */
@UML(identifier="CD_PixelInCell", specification=ISO_19111)
public final class PixelInCell extends CodeList<PixelInCell> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2857889370030758462L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PixelInCell> VALUES = new ArrayList<PixelInCell>(2);

    /**
     * The origin of the image coordinate system is the centre of a grid cell or image pixel.
     *
     * @see org.opengis.metadata.spatial.PixelOrientation#CENTER
     */
    @UML(identifier="cell center", obligation=CONDITIONAL, specification=ISO_19111)
    public static final PixelInCell CELL_CENTER = new PixelInCell("CELL_CENTER");

    /**
     * The origin of the image coordinate system is the corner of a grid cell, or half-way
     * between the centres of adjacent image pixels.
     *
     * @see org.opengis.metadata.spatial.PixelOrientation#LOWER_LEFT
     */
    @UML(identifier="cell corner", obligation=CONDITIONAL, specification=ISO_19111)
    public static final PixelInCell CELL_CORNER = new PixelInCell("CELL_CORNER");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private PixelInCell(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code PixelInCell}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static PixelInCell[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new PixelInCell[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public PixelInCell[] family() {
        return values();
    }

    /**
     * Returns the pixel in cell that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static PixelInCell valueOf(String code) {
        return valueOf(PixelInCell.class, code);
    }
}
