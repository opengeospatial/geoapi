/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/citation/OnLineResource.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE direct dependencies
import java.net.URI;

// OpenGIS direct dependencies
import org.opengis.metadata.MetadataEntity;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about on-line sources from which the dataset, specification, or
 * community profile name and extended metadata elements can be obtained.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@Profile (level=CORE)
@UML(identifier="CI_OnlineResource", specification=ISO_19115)
public interface OnLineResource extends MetadataEntity{
    /**
     * Location (address) for on-line access using a Uniform Resource Locator address or
     * similar addressing scheme such as http://www.statkart.no/isotc211.
     */
    @UML(identifier="linkage", obligation=MANDATORY, specification=ISO_19115)
    URI getLinkage();
        

    /**
     * Connection protocol to be used. Returns {@code null} if none.
     */
    @UML(identifier="protocol", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getProtocol();
        

    /**
     * Name of an application profile that can be used with the online resource.
     * Returns {@code null} if none.
     */
    @UML(identifier="applicationProfile", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getApplicationProfile();

    /**
     * The name of the online resource
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    public InternationalString getName();
    
    /**
     * Detailed text description of what the online resource is/does.
     * Returns {@code null} if none.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Code for function performed by the online resource.
     * Returns {@code null} if unspecified.
     */
    @UML(identifier="function", obligation=OPTIONAL, specification=ISO_19115)
    OnLineFunction getFunction();
    
    
}
