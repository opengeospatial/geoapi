/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.metadata.maintenance;

import java.util.List;
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
     * Data is repeatedly and frequently updated.
     */
    @UML(identifier="continual", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency CONTINUAL;

    /**
     * Data is updated each day.
     */
    @UML(identifier="daily", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency DAILY;

    /**
     * Data is updated on a weekly basis.
     */
    @UML(identifier="weekly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency WEEKLY;

    /**
     * Data is updated every two weeks.
     */
    @UML(identifier="fortnightly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency FORTNIGHTLY;

    /**
     * Data is updated each month.
     */
    @UML(identifier="monthly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency MONTHLY;

    /**
     * Data is updated every three months.
     */
    @UML(identifier="quarterly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency QUARTERLY;

    /**
     * Data is updated twice each year.
     */
    @UML(identifier="biannually", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency BIANNUALLY;

    /**
     * Data is updated every year.
     */
    @UML(identifier="annually", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency ANNUALLY;

    /**
     * Data is updated as deemed necessary.
     */
    @UML(identifier="asNeeded", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency AS_NEEDED;

    /**
     * Data is updated in intervals that are uneven in duration.
     */
    @UML(identifier="irregular", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency IRREGULAR;

    /**
     * There are no plans to update the data.
     */
    @UML(identifier="notPlanned", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency NOT_PLANNED;

    /**
     * Frequency of maintenance for the data is not known.
     */
    @UML(identifier="unknown", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency UNKNOWN;

    /**
     * Resource is updated at regular intervals.
     *
     * @since 3.1
     */
    @UML(identifier="periodic", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency PERIODIC;

    /**
     * Resource updated twice a monthly.
     *
     * @since 3.1
     */
    @UML(identifier="semimonthly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency SEMIMONTHLY;

    /**
     * Resource is updated every 2 years.
     *
     * @since 3.1
     */
    @UML(identifier="biennially", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MaintenanceFrequency BIENNIALLY;

    /**
     * All code list values created in the currently running <abbr>JVM</abbr>.
     */
    private static final List<MaintenanceFrequency> VALUES = initialValues(
        // Inline assignments for getting compiler error if a field is missing or duplicated.
        CONTINUAL   = new MaintenanceFrequency("CONTINUAL"),
        DAILY       = new MaintenanceFrequency("DAILY"),
        WEEKLY      = new MaintenanceFrequency("WEEKLY"),
        FORTNIGHTLY = new MaintenanceFrequency("FORTNIGHTLY"),
        MONTHLY     = new MaintenanceFrequency("MONTHLY"),
        QUARTERLY   = new MaintenanceFrequency("QUARTERLY"),
        BIANNUALLY  = new MaintenanceFrequency("BIANNUALLY"),
        ANNUALLY    = new MaintenanceFrequency("ANNUALLY"),
        AS_NEEDED   = new MaintenanceFrequency("AS_NEEDED"),
        IRREGULAR   = new MaintenanceFrequency("IRREGULAR"),
        NOT_PLANNED = new MaintenanceFrequency("NOT_PLANNED"),
        UNKNOWN     = new MaintenanceFrequency("UNKNOWN"),
        PERIODIC    = new MaintenanceFrequency("PERIODIC"),
        SEMIMONTHLY = new MaintenanceFrequency("SEMIMONTHLY"),
        BIENNIALLY  = new MaintenanceFrequency("BIENNIALLY"));

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private MaintenanceFrequency(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code MaintenanceFrequency}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static MaintenanceFrequency[] values() {
        return VALUES.toArray(MaintenanceFrequency[]::new);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
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
     * Returns the maintenance frequency that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static MaintenanceFrequency valueOf(String code) {
        return valueOf(VALUES, code, MaintenanceFrequency::new);
    }
}
