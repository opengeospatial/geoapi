/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.annotation;

import static org.opengis.annotation.Specification.*;


/**
 * Obligation of the element or entity. The enum values declared here are an exact copy of
 * the code list elements declared in the {@link org.opengis.metadata.Obligation} code list
 * from the metadata package.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.0
 */
@UML(identifier="MD_ObligationCode", specification=ISO_19115)
public enum Obligation {
    /**
     * Element is required when a specific condition is met.
     */
    ///@UML(identifier="conditional", obligation=CONDITIONAL, specification=ISO_19115)
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
     *
     * @departure constraint
     *   ISO specifications sometime override a parent method with a comment saying that the method 
     *   is not allowed for a particular class. Since there is no construct in Java for expressing this
     *   constraint in the method signature, GeoAPI defines a <code>FORBIDDEN</code> obligation
     *   (not in original ISO specifications) to be used with the <code>@UML</code> annotation and
     *   which adds a flag in the Java documentation.
     */
    FORBIDDEN
}
