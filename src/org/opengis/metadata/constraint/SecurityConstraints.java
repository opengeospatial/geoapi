/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.constraint;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;


/**
 * Handling restrictions imposed on the resource for national security or similar security concerns.
 *
 * @UML datatype MD_SecurityConstraints
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface SecurityConstraints extends Constraints {
    /**
     * Name of the handling restrictions on the resource.
     *
     * @UML mandatory classification
     */
    Classification getClassification();

    /**
     * Explanation of the application of the legal constraints or other restrictions and legal
     * prerequisites for obtaining and using the resource.
     *
     * @UML optional userNote
     */
    InternationalString getUserNote();

    /**
     * Name of the classification system.
     *
     * @UML optional classificationSystem
     */
    InternationalString getClassificationSystem();

    /**
     * Additional information about the restrictions on handling the resource.
     *
     * @UML optional handlingDescription
     */
    InternationalString getHandlingDescription();
}
