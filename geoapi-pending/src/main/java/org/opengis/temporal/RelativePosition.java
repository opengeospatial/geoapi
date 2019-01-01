/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;

/**
 * Values for relative temporal position based on the 13 temporal relationships identified by Allen (1993).
 * Define comparison between two {@link TemporalTopologicalPrimitive}.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (IRD)
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_RelativePosition", specification=ISO_19108)
public final class RelativePosition extends CodeList<RelativePosition> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2918422623747953495L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<RelativePosition> VALUES = new ArrayList<RelativePosition>(13);

    /**
     * This {@link TemporalTopologicalPrimitive} is earlier in the sequence than <i>other</i>
     * and is not linked to <i>other</i> in an <i>Initiation</i> or <i>Termination</i> association.
     */
    public static final RelativePosition BEFORE = new RelativePosition("BEFORE");

    /**
     * This {@link TemporalTopologicalPrimitive} is later in the sequence than <i>other</i>
     * and is not linked to <i>other</i> in an <i>Initiation</i> or <i>Termination</i> association.
     */
    public static final RelativePosition AFTER = new RelativePosition("AFTER");

    /**
     * This two {@link TemporalTopologicalPrimitive} are linked in an <i>Initiation</i> association.
     */
    public static final RelativePosition BEGINS = new RelativePosition("BEGINS");

    /**
     * This two {@link TemporalTopologicalPrimitive} are linked in an <i>Termination</i> association.
     */
    public static final RelativePosition ENDS = new RelativePosition("ENDS");

    public static final RelativePosition DURING = new RelativePosition("DURING");

    /**
     * This {@link TemporalTopologicalPrimitive} and <i>other</i> are the same.
     */
    public static final RelativePosition EQUALS = new RelativePosition("EQUALS");

    public static final RelativePosition CONTAINS = new RelativePosition("CONTAINS");
    public static final RelativePosition OVERLAPS = new RelativePosition("OVERLAPS");

    /**
     * The two {@link TemporalTopologicalPrimitive} are {@link TemporalEdge} associated
     * to the same {@link TemporalNode}, where this {@link TemporalEdge} is linked to the
     * {@link TemporalNode} as a <i>previousEdge</i> in a <i>Termination</i> association,
     * and <i>other</i> is linked to the {@link TemporalNode} as a <i>nextEdge</i> in a
     * <i>Initiation</i> association.
     */
    public static final RelativePosition MEETS = new RelativePosition("MEETS");

    public static final RelativePosition OVERLAPPED_BY = new RelativePosition("OVERLAPPED_BY");

    /**
     * The two {@link TemporalTopologicalPrimitive} are {@link TemporalEdge} associated
     * to the same {@link TemporalNode}, where this {@link TemporalEdge} is linked to the
     * {@link TemporalNode} as a <i>nextEdge</i> in a <i>Initiation</i> association,
     * and <i>other</i> is linked to the {@link TemporalNode} as a <i>previousEdge</i>
     * in a <i>Termination</i> association.
     */
    public static final RelativePosition MET_BY = new RelativePosition("MET_BY");

    /**
     * This {@link TemporalTopologicalPrimitive} is a {@link TemporalEdge},
     * <i>other</i> is a {@link TemporalNode} and these two {@link TemporalTopologicalPrimitive}
     * are linked in an <i>Initiation</i> association.
     */
    public static final RelativePosition BEGUN_BY = new RelativePosition("BEGUN_BY");

    /**
     * This {@link TemporalTopologicalPrimitive} is a {@link TemporalEdge},
     * <i>other</i> is a {@link TemporalNode} and these two {@link TemporalTopologicalPrimitive}
     * are linked in an <i>Termination</i> association.
     */
    public static final RelativePosition ENDED_BY = new RelativePosition("ENDED_BY");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private RelativePosition(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code RelativePosition}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static RelativePosition[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new RelativePosition[VALUES.size()]);
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
    public RelativePosition[] family() {
        return values();
    }

    /**
     * Returns the relative position that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static RelativePosition valueOf(String code) {
        return valueOf(RelativePosition.class, code);
    }
}
