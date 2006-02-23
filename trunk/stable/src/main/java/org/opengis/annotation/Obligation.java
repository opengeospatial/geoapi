/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.annotation;

// Annotations
import static org.opengis.annotation.Specification.*;


/**
 * Obligation of the element or entity. The enum values declared here are an exact copy of
 * the code list elements declared in the {@link org.opengis.metadata.Obligation} code list
 * from the metadata package.
 * 
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_ObligationCode", specification=ISO_19115)
public enum Obligation {
    /**
     * Element is required when a specific condition is met.
     */    
	// @UML(identifier="conditional", obligation=CONDITIONAL, specification=ISO_19115)
    CONDITIONAL,

    /**
     * Element is not required.
     */
    @UML(identifier="optional", obligation=CONDITIONAL, specification=ISO_19115)
    OPTIONAL,

    /**
     * Element is always required.
     */
    @UML(identifier="mandatory", obligation=CONDITIONAL, specification=ISO_19115)
    MANDATORY,

    /**
     * The element should always be {@code null}. This obligation code is used only when
     * a subinterface overrides an association and force it to a {@code null} value.
     * An example is {@link org.opengis.referencing.datum.TemporalDatum#getAnchorPoint}.
     */
    FORBIDDEN
}
