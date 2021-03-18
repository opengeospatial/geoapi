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
package org.opengis.metadata.maintenance;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Frequency with which modifications and deletions are made to the data after it is first produced.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_MaintenanceFrequencyCode", specification=ISO_19115)
public final class MaintenanceFrequency extends CodeList<MaintenanceFrequency> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6034786030982260550L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<MaintenanceFrequency> VALUES = new ArrayList<>(15);

    /**
     * Data is repeatedly and frequently updated.
     */
    @UML(identifier="continual", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency CONTINUAL = new MaintenanceFrequency("CONTINUAL");

    /**
     * Data is updated each day.
     */
    @UML(identifier="daily", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency DAILY = new MaintenanceFrequency("DAILY");

    /**
     * Data is updated on a weekly basis.
     */
    @UML(identifier="weekly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency WEEKLY = new MaintenanceFrequency("WEEKLY");

    /**
     * Data is updated every two weeks.
     */
    @UML(identifier="fortnightly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency FORTNIGHTLY = new MaintenanceFrequency("FORTNIGHTLY");

    /**
     * Data is updated each month.
     */
    @UML(identifier="monthly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency MONTHLY = new MaintenanceFrequency("MONTHLY");

    /**
     * Data is updated every three months.
     */
    @UML(identifier="quarterly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency QUARTERLY = new MaintenanceFrequency("QUARTERLY");

    /**
     * Data is updated twice each year.
     */
    @UML(identifier="biannually", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency BIANNUALLY = new MaintenanceFrequency("BIANNUALLY");

    /**
     * Data is updated every year.
     */
    @UML(identifier="annually", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency ANNUALLY = new MaintenanceFrequency("ANNUALLY");

    /**
     * Data is updated as deemed necessary.
     */
    @UML(identifier="asNeeded", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency AS_NEEDED = new MaintenanceFrequency("AS_NEEDED");

    /**
     * Data is updated in intervals that are uneven in duration.
     */
    @UML(identifier="irregular", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency IRREGULAR = new MaintenanceFrequency("IRREGULAR");

    /**
     * There are no plans to update the data.
     */
    @UML(identifier="notPlanned", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency NOT_PLANNED = new MaintenanceFrequency("NOT_PLANNED");

    /**
     * Frequency of maintenance for the data is not known.
     */
    @UML(identifier="unknown", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency UNKNOWN = new MaintenanceFrequency("UNKNOWN");

    /**
     * Resource is updated at regular intervals.
     *
     * @since 3.1
     */
    @UML(identifier="periodic", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency PERIODIC = new MaintenanceFrequency("PERIODIC");

    /**
     * Resource updated twice a monthly.
     *
     * @since 3.1
     */
    @UML(identifier="semimonthly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency SEMIMONTHLY = new MaintenanceFrequency("SEMIMONTHLY");

    /**
     * Resource is updated every 2 years.
     *
     * @since 3.1
     */
    @UML(identifier="biennially", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency BIENNIALLY = new MaintenanceFrequency("BIENNIALLY");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private MaintenanceFrequency(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code MaintenanceFrequency}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static MaintenanceFrequency[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new MaintenanceFrequency[VALUES.size()]);
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
    public MaintenanceFrequency[] family() {
        return values();
    }

    /**
     * Returns the maintenance frequency that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static MaintenanceFrequency valueOf(String code) {
        return valueOf(MaintenanceFrequency.class, code);
    }
}
