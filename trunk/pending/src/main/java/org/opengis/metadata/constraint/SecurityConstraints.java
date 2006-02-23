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
package org.opengis.metadata.constraint;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Handling restrictions imposed on the resource for national security or similar security concerns.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_SecurityConstraints", specification=ISO_19115)
public interface SecurityConstraints extends Constraints {
    /**
     * Name of the handling restrictions on the resource.
     */
    @UML(identifier="classification", obligation=MANDATORY, specification=ISO_19115)
    Classification getClassification();

    /**
     * Explanation of the application of the legal constraints or other restrictions and legal
     * prerequisites for obtaining and using the resource.
     */
    @UML(identifier="userNote", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getUserNote();

    /**
     * Name of the classification system.
     */
    @UML(identifier="classificationSystem", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getClassificationSystem();

    /**
     * Additional information about the restrictions on handling the resource.
     */
    @UML(identifier="handlingDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getHandlingDescription();
}
