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
package org.opengis.metadata.identification;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Status of the dataset or progress of a review.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_ProgressCode", specification=ISO_19115)
public final class Progress extends CodeList<Progress> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7521085150853319219L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Progress> VALUES = new ArrayList<Progress>(7);

    /**
     * Production of the data has been completed.
     */
    @UML(identifier="completed", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Progress COMPLETED = new Progress("COMPLETED");

    /**
     * Data has been stored in an offline storage facility
     */
    @UML(identifier="historicalArchive", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Progress HISTORICAL_ARCHIVE = new Progress("HISTORICAL_ARCHIVE");

    /**
     * Data is no longer relevant.
     */
    @UML(identifier="obsolete", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Progress OBSOLETE = new Progress("OBSOLETE");

    /**
     * Data is continually being updated.
     */
    @UML(identifier="onGoing", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Progress ON_GOING = new Progress("ON_GOING");

    /**
     * Fixed date has been established upon or by which the data will be created or updated.
     */
    @UML(identifier="planned", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Progress PLANNED = new Progress("PLANNED");

    /**
     * Data needs to be generated or updated.
     */
    @UML(identifier="required", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Progress REQUIRED = new Progress("REQUIRED");

    /**
     * Data is currently in the process of being created.
     */
    @UML(identifier="underDevelopment", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Progress UNDER_DEVELOPMENT = new Progress("UNDER_DEVELOPMENT");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private Progress(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Progress}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static Progress[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new Progress[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public Progress[] family() {
        return values();
    }

    /**
     * Returns the progress that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static Progress valueOf(String code) {
        return valueOf(Progress.class, code);
    }
}
