/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Type of aggregation activity in which datasets are related.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Gabriel Roldan, Axios Engineering
 * @since GeoAPI 2.1
 */
@UML(identifier="DS_InitiativeTypeCode", specification=ISO_19115)
public final class InitiativeType extends CodeList<InitiativeType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4726629268565235922L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<InitiativeType> VALUES = new ArrayList<InitiativeType>(15);

    /**
     * Series of organized planned actions.
     */
    @UML(identifier="campaign", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType CAMPAIGN = new InitiativeType("campaign");

    /**
     * Accumulation of datasets assembled for a specific purpose.
     */
    @UML(identifier="collection", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType COLLECTION = new InitiativeType("collection");

    /**
     * Specific performance of a function or group of functions.
     */
    @UML(identifier="exercise", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType EXERCISE = new InitiativeType("exercise");

    /**
     * Process designed to find if something is effective or valid.
     */
    @UML(identifier="experiment", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType EXPERIMENT = new InitiativeType("experiment");

    /**
     * Search or systematic inquiry.
     */
    @UML(identifier="investigation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType INVESTIGATION = new InitiativeType("investigation");

    /**
     * Specific operation of a data collection system.
     */
    @UML(identifier="mission", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType MISSION = new InitiativeType("mission");

    /**
     * Device or piece of equipment which detects or records.
     */
    @UML(identifier="sensor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType SENSOR = new InitiativeType("sensor");

    /**
     * Action that is part of a series of actions.
     */
    @UML(identifier="operation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType OPERATION = new InitiativeType("operation");

    /**
     * Vehicle or other support base that holds a sensor.
     */
    @UML(identifier="platform", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType PLATFORM = new InitiativeType("platform");
    
    /**
     * Method of doing something involving a number of steps.
     */
    @UML(identifier="process", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType PROCESS = new InitiativeType("process");
    
    /**
     * Specific planned activity.
     */
    @UML(identifier="program", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType PROGRAM = new InitiativeType("program");

    /**
     * Organized undertaking, research, or development.
     */
    @UML(identifier="project", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType PROJECT = new InitiativeType("project");

    /**
     * Examination or investigation.
     */
    @UML(identifier="study", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType STUDY = new InitiativeType("study");

    /**
     * Piece of work.
     */
    @UML(identifier="task", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType TASK = new InitiativeType("task");

    /**
     * Process of testing to discover or demonstrate something.
     */
    @UML(identifier="trial", obligation=CONDITIONAL, specification=ISO_19115)
    public static final InitiativeType TRIAL = new InitiativeType("trial");
    
    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public InitiativeType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code InitiativeType}s.
     */
    public static InitiativeType[] values() {
        synchronized (VALUES) {
            return (InitiativeType[]) VALUES.toArray(new InitiativeType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{InitiativeType}*/ CodeList[] family() {
        return values();
    }
}
