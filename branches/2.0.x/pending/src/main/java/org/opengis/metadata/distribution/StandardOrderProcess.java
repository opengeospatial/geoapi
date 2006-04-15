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
package org.opengis.metadata.distribution;

// J2SE direct dependencies
import java.util.Date;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Common ways in which the resource may be obtained or received, and related instructions
 * and fee information.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_StandardOrderProcess", specification=ISO_19115)
public interface StandardOrderProcess {
    /**
     * Fees and terms for retrieving the resource.
     * Include monetary units (as specified in ISO 4217).
     */
    @UML(identifier="fees", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getFees();

    /**
     * Date and time when the dataset will be available.
     */
    @UML(identifier="plannedAvailableDateTime", obligation=OPTIONAL, specification=ISO_19115)
    Date getPlannedAvailableDateTime();

    /**
     * General instructions, terms and services provided by the distributor.
     */
    @UML(identifier="orderingInstructions", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getOrderingInstructions();

    /**
     * Typical turnaround time for the filling of an order.
     */
    @UML(identifier="turnaround", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getTurnaround();
}
