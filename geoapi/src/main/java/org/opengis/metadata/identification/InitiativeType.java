/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Type of aggregation activity in which datasets are related
 *
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.0
 * @since   2.1
 */
@UML(identifier="DS_InitiativeTypeCode", specification=ISO_19115)
public final class InitiativeType extends CodeList<InitiativeType>{
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6875282680499638030L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<InitiativeType> VALUES = new ArrayList<>(15);

    /**
     * Series of organized planned actions.
     */
    @UML(identifier="campaign", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType CAMPAIGN = new InitiativeType("CAMPAIGN");

    /**
     * Accumulation of datasets assembled for a specific purpose.
     */
    @UML(identifier="collection", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType COLLECTION = new InitiativeType("COLLECTION");

    /**
     * Specific performance of a function or group of functions.
     */
    @UML(identifier="exercise", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType EXERCISE = new InitiativeType("EXERCISE");

    /**
     * Process designed to find if something is effective or valid.
     */
    @UML(identifier="experiment", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType EXPERIMENT = new InitiativeType("EXPERIMENT");

    /**
     * Search or systematic inquiry.
     */
    @UML(identifier="investigation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType INVESTIGATION = new InitiativeType("INVESTIGATION");

    /**
     * Specific operation of a data collection system.
     */
    @UML(identifier="mission", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType MISSION = new InitiativeType("MISSION");

    /**
     * Device or piece of equipment which detects or records.
     */
    @UML(identifier="sensor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType SENSOR = new InitiativeType("SENSOR");

    /**
     * Action that is part of a series of actions.
     */
    @UML(identifier="operation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType OPERATION = new InitiativeType("OPERATION");

    /**
     * Vehicle or other support base that holds a sensor.
     */
    @UML(identifier="platform", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType PLATFORM = new InitiativeType("PLATFORM");

    /**
     * Method of doing something involving a number of steps.
     */
    @UML(identifier="process", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType PROCESS = new InitiativeType("PROCESS");

    /**
     * Specific planned activity.
     */
    @UML(identifier="program", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType PROGRAM = new InitiativeType("PROGRAM");

    /**
     * Organized undertaking, research, or development.
     */
    @UML(identifier="project", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType PROJECT = new InitiativeType("PROJECT");

    /**
     * Examination or investigation.
     */
    @UML(identifier="study", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType STUDY = new InitiativeType("STUDY");

    /**
     * Piece of work.
     */
    @UML(identifier="task", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType TASK = new InitiativeType("TASK");

    /**
     * Process of testing to discover or demonstrate something.
     */
    @UML(identifier="trial", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType TRIAL = new InitiativeType("TRIAL");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private InitiativeType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code InitiativeType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static InitiativeType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(InitiativeType[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public InitiativeType[] family() {
        return values();
    }

    /**
     * Returns the initiative type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static InitiativeType valueOf(String code) {
        return valueOf(InitiativeType.class, code);
    }
}
