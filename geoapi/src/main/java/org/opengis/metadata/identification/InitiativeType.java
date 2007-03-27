/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2007 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
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
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @since GeoAPI 2.1
 */
@UML(identifier="DS_InitiativeTypeCode", specification=ISO_19115)
public class InitiativeType extends CodeList<InitiativeType>{

    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6875282680499638030L;
    

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<InitiativeType> VALUES = new ArrayList<InitiativeType>(15);


    /**
     * series of organized planned actions
     */
    @UML(identifier="campaign", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType CAMPAIGN = new AssociationType("CAMPAIGN");
    
    /**
     * accumulation of datasets assembled for a specific purpose
     */
    @UML(identifier="collection", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType COLLECTION = new AssociationType("COLLECTION");
    
    /**
     * specific performance of a function or group of functions
     */
    @UML(identifier="exercise", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType EXERCISE = new AssociationType("EXERCISE");
    
    /**
     * process designed to find if something is effective or valid
     */
    @UML(identifier="experiment", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType EXPERIMENT = new AssociationType("EXPERIMENT");
    
    /**
     *  search or systematic inquiry
     */
    @UML(identifier="investigation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType INVESTIGATION = new AssociationType("INVESTIGATION");
    
    /**
     * specific operation of a data collection system
     */
    @UML(identifier="mission", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType MISSION = new AssociationType("MISSION");
    
    /**
     * device or piece of equipment which detects or records
     */
    @UML(identifier="sensor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType SENSOR = new AssociationType("SENSOR");
    
    /**
     * action that is part of a series of actions
     */
    @UML(identifier="oepration", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType OPERATION = new AssociationType("OPERATION");
    
    /**
     * vehicle or other support base that holds a sensor
     */
    @UML(identifier="platform", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType PLATFORM = new AssociationType("PLATFORM");
    
    /**
     * method of doing something involving a number of steps
     */
    @UML(identifier="process", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType PROCESS = new AssociationType("PROCESS");
    
    /**
     * specific planned activity
     */
    @UML(identifier="program", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType PROGRAM = new AssociationType("PROGRAM");
    
    /**
     * organized undertaking, research, or development
     */
    @UML(identifier="project", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType PROJECT = new AssociationType("PROJECT");
    
    /**
     * examination or investigation
     */
    @UML(identifier="study", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType STUDY = new AssociationType("STUDY");
    
    /**
     * piece of work
     */
    @UML(identifier="task", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType TASK = new AssociationType("TASK");
    
    /**
     * process of testing to discover or demonstrate something
     */
    @UML(identifier="trial", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType TRIAL = new AssociationType("TRIAL");
    
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
     * Returns the list of {@code AssociationType}s.
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
