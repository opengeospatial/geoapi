/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

// J2SE direct dependencies
import java.util.Date;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Common ways in which the resource may be obtained or received, and related instructions
 * and fee information.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_StandardOrderProcess")
public interface StandardOrderProcess {
    /**
     * Fees and terms for retrieving the resource.
     * Include monetary units (as specified in ISO 4217).
     */
/// @UML (identifier="fees", obligation=OPTIONAL)
    InternationalString getFees();

    /**
     * Date and time when the dataset will be available.
     */
/// @UML (identifier="plannedAvailableDateTime", obligation=OPTIONAL)
    Date getPlannedAvailableDateTime();

    /**
     * General instructions, terms and services provided by the distributor.
     */
/// @UML (identifier="orderingInstructions", obligation=OPTIONAL)
    InternationalString getOrderingInstructions();

    /**
     * Typical turnaround time for the filling of an order.
     */
/// @UML (identifier="turnaround", obligation=OPTIONAL)
    InternationalString getTurnaround();
}
