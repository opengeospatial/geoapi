/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE direct dependencies
import java.net.URI;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import org.opengis.annotation.Profile;
///import static org.opengis.annotation.Obligation.*;
///import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information about on-line sources from which the dataset, specification, or
 * community profile name and extended metadata elements can be obtained.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@Profile (level=CORE)
///@UML (identifier="CI_OnlineResource")
public interface OnLineResource {
    /**
     * Location (address) for on-line access using a Uniform Resource Locator address or
     * similar addressing scheme such as http://www.statkart.no/isotc211.
     */
/// @UML (identifier="linkage", obligation=MANDATORY)
    URI getLinkage();

    /**
     * connection protocol to be used. Returns <code>null</code> if none.
     */
/// @UML (identifier="protocol", obligation=OPTIONAL)
    String getProtocol();

    /**
     * Name of an application profile that can be used with the online resource.
     * Returns <code>null</code> if none.
     */
/// @UML (identifier="applicationProfile", obligation=OPTIONAL)
    String getApplicationProfile();

    /**
     * Detailed text description of what the online resource is/does.
     * Returns <code>null</code> if none.
     */
/// @UML (identifier="description", obligation=OPTIONAL)
    InternationalString getDescription();

    /**
     * Code for function performed by the online resource.
     * Returns <code>null</code> if unspecified.
     */
/// @UML (identifier="function", obligation=OPTIONAL)
    OnLineFunction getFunction();
}
