/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.spatial;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Point in a pixel corresponding to the Earth location of the pixel.
 *
 * <p>This code list is restricted to the two-dimensional case. A similar code
 * list, {@link org.opengis.referencing.datum.PixelInCell}, can be used for
 * <var>n</var>-dimensional grid cell.</p>
 *
 * <div class="warning"><b>Upcoming API change — enumeration</b><br>
 * According ISO 19115, {@code PixelOrientation} shall be an enumeration, not a code list.
 * This class may be changed to a Java {@code enum} in GeoAPI 4.0.
 * See <a href="http://jira.codehaus.org/browse/GEO-199">GEO-199</a> for more information.
 * </div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 *
 * @see org.opengis.referencing.datum.PixelInCell
 */
@UML(identifier="MD_PixelOrientationCode", specification=ISO_19115)
public final class PixelOrientation extends CodeList<PixelOrientation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7885677198357949308L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PixelOrientation> VALUES = new ArrayList<PixelOrientation>(5);

    /**
     * Point in a pixel corresponding to the Earth location of the pixel.
     *
     * @see org.opengis.referencing.datum.PixelInCell#CELL_CENTER
     */
    @UML(identifier="center", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation CENTER = new PixelOrientation("CENTER");

    /**
     * The corner in the pixel closest to the origin of the SRS.
     * If two are at the same distance from the origin, the one with the smallest <var>x</var>-value.
     *
     * @todo The sentence <cite>"closest to the origin of the SRS</cite> probably applies to
     *       positive coordinates only. For the general case including both positive and negative
     *       coordinates, we should probably read "in the direction of negative infinity". This
     *       interpretation should be clarified with ISO.
     *
     * @see org.opengis.referencing.datum.PixelInCell#CELL_CORNER
     */
    @UML(identifier="lowerLeft", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation LOWER_LEFT = new PixelOrientation("LOWER_LEFT");

    /**
     * Next corner counterclockwise from the {@linkplain #LOWER_LEFT lower left}.
     */
    @UML(identifier="lowerRight", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation LOWER_RIGHT = new PixelOrientation("LOWER_RIGHT");

    /**
     * Next corner counterclockwise from the {@linkplain #LOWER_RIGHT lower right}.
     */
    @UML(identifier="upperRight", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation UPPER_RIGHT = new PixelOrientation("UPPER_RIGHT");

    /**
     * Next corner counterclockwise from the {@linkplain #UPPER_RIGHT upper right}.
     */
    @UML(identifier="upperLeft", obligation=CONDITIONAL, specification=ISO_19115)
    public static final PixelOrientation UPPER_LEFT = new PixelOrientation("UPPER_LEFT");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by an other element of this type.
     */
    private PixelOrientation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code PixelOrientation}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static PixelOrientation[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new PixelOrientation[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public PixelOrientation[] family() {
        return values();
    }

    /**
     * Returns the pixel orientation that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static PixelOrientation valueOf(String code) {
        return valueOf(PixelOrientation.class, code);
    }
}
