/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.maintenance;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Frequency with which modifications and deletions are made to the data after it is
 * first produced.
 *
 * @UML codelist MD_MaintenanceFrequencyCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public final class MaintenanceFrequency extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6034786030982260550L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(10);

    /**
     * Data is repeatedly and frequently updated.
     *
     * @UML conditional continual
     */
    public static final MaintenanceFrequency CONTINUAL = new MaintenanceFrequency("CONTINUAL");

    /**
     * Data is updated each day.
     *
     * @UML conditional daily
     */
    public static final MaintenanceFrequency DAILY = new MaintenanceFrequency("DAILY");

    /**
     * Data is updated on a weekly basis.
     *
     * @UML conditional daily
     */
    public static final MaintenanceFrequency WEEKLY = new MaintenanceFrequency("WEEKLY");

    /**
     * Data is updated every two weeks.
     *
     * @UML conditional fortnightly
     */
    public static final MaintenanceFrequency FORTNIGHTLY = new MaintenanceFrequency("FORTNIGHTLY");

    /**
     * Data is updated each month.
     *
     * @UML conditional monthly
     */
    public static final MaintenanceFrequency MONTHLY = new MaintenanceFrequency("MONTHLY");

    /**
     * Data is updated every three months.
     *
     * @UML conditional quarterly
     */
    public static final MaintenanceFrequency QUARTERLY = new MaintenanceFrequency("QUARTERLY");

    /**
     * Data is updated twice each year.
     *
     * @UML conditional biannually
     */
    public static final MaintenanceFrequency BIANNUALLY = new MaintenanceFrequency("BIANNUALLY");

    /**
     * Data is updated every year.
     *
     * @UML conditional annually
     */
    public static final MaintenanceFrequency ANNUALLY = new MaintenanceFrequency("ANNUALLY");

    /**
     * Data is updated as deemed necessary.
     *
     * @UML conditional asNeeded
     */
    public static final MaintenanceFrequency AS_NEEDED = new MaintenanceFrequency("AS_NEEDED");

    /**
     * Data is updated in intervals that are uneven in duration.
     *
     * @UML conditional irregular
     */
    public static final MaintenanceFrequency IRREGULAR = new MaintenanceFrequency("IRREGULAR");

    /**
     * There are no plans to update the data.
     *
     * @UML conditional notPlanned
     */
    public static final MaintenanceFrequency NOT_PLANNED = new MaintenanceFrequency("NOT_PLANNED");

    /**
     * Frequency of maintenance for the data is not known
     *
     * @UML conditional unknow
     */
    public static final MaintenanceFrequency UNKNOW = new MaintenanceFrequency("UNKNOW");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public MaintenanceFrequency(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>MaintenanceFrequency</code>s.
     */
    public static MaintenanceFrequency[] values() {
        synchronized (VALUES) {
            return (MaintenanceFrequency[]) VALUES.toArray(new MaintenanceFrequency[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{MaintenanceFrequency}*/ CodeList[] family() {
        return values();
    }
}
