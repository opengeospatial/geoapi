/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.Citation;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Value uniquely identifying an object within a namespace.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
@UML (identifier="MD_Identifier", specification=ISO_19115)
public interface Identifier {
    /**
     * Alphanumeric value identifying an instance in the namespace.
     */
    @UML (identifier="code", obligation=MANDATORY, specification=ISO_19115)
    String getCode();

    /**
     * Identifier of the version of the associated code, as specified by the code authority.
     * This version is included only when the {@linkplain #getCode code} uses versions. When
     * appropriate, the edition is identified by the effective date, coded using ISO 8601 date
     * format.
     */
    @Extension
    String getVersion();

    /**
     * Organization or party responsible for definition and maintenance of the
     * {@linkplain #getCode code}.
     */
    @UML (identifier="authority", obligation=OPTIONAL, specification=ISO_19115)
    Citation getAuthority();
}
