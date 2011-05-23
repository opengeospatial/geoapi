/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.acquisition;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Mechanism of activation.
 *
 * @author  Cédric Briançon (Geomatys)
 * @since   2.3
 * @version 3.0
 */
@UML(identifier="MI_TriggerCode", specification=ISO_19115_2)
public final class Trigger extends CodeList<Trigger> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 649620597963351153L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Trigger> VALUES = new ArrayList<Trigger>(3);

    /**
     * Event due to external stimuli.
     */
    @UML(identifier="automatic", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Trigger AUTOMATIC = new Trigger("AUTOMATIC");

    /**
     * Event manually instigated.
     */
    @UML(identifier="manual", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Trigger MANUAL = new Trigger("MANUAL");

    /**
     * Event instigated by planned internal stimuli.
     */
    @UML(identifier="preProgrammed", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Trigger PRE_PROGRAMMED = new Trigger("PRE_PROGRAMMED");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private Trigger(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Trigger}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static Trigger[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new Trigger[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public Trigger[] family() {
        return values();
    }

    /**
     * Returns the trigger that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static Trigger valueOf(String code) {
        return valueOf(Trigger.class, code);
    }
}
