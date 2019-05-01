/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.annotation.Draft;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;


/**
 * Labels coordinate systems as being right or left handed as commonly defined in Mathematics.
 * This really only has meaning if the coordinate system is spatial. This is needed since most
 * mathematical algorithms need to know or potentially modify the handedness of a coordinate
 * system to maintain the proper representation of space.
 *
 * <div class="note"><b>Note:</b>
 * The default for mathematics is right-handed systems (classic <var>x</var>-<var>y</var>, or
 * <var>x</var>-<var>y</var>-<var>z</var> order in Cartesian coordinates), while many geographic
 * coordinate reference systems are left-handed. This mathematical choice is seen in several
 * decisions; such as the inside of a surface is to the left of its boundary, the inside of a
 * solid is below its boundary, and the very concepts of oriented primitives and algebraic topology.
 * This bias toward right-handed systems is also seen in computer graphics and in engineering.
 * </div>
 *
 * @author  Axel Francois (LSIS/Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Draft
@UML(identifier="GM_Handed", specification=ISO_19107)
public class Handed extends CodeList<Handed> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2422369847874945334L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Handed> VALUES=new ArrayList<Handed>(2);

    /**
     * The right-handed systems (classic <var>x</var>-<var>y</var>-<var>z</var>).
     */
    @Draft
    @UML(identifier="right", obligation=CONDITIONAL, specification=ISO_19107)
    public static final Handed RIGHT=new Handed("RIGHT");

    /**
     * The left-handed systems (<var>y</var>-<var>x</var>-<var>z</var>),
     * for example <var>Latitude</var>/<var>Longitude</var>.
     */
    @Draft
    @UML(identifier="left", obligation=CONDITIONAL, specification=ISO_19107)
    public static final Handed LEFT=new Handed("LEFT");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private Handed(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Handed}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Handed[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new Handed[VALUES.size()]);
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
    public Handed[] family() {
        return values();
    }

    /**
     * Returns the Handed orientation form that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Handed valueOf(String code) {
        return valueOf(Handed.class, code);
    }
}
