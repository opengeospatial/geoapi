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
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Value uniquely identifying an object within a namespace.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_Identifier")
public interface Identifier {
    /**
     * Alphanumeric value identifying an instance in the namespace.
     * Optionally from a controlled list or pattern defined by a {@linkplain #getCodeSpace code space}.
     *
     * @return The code.
     */
/// @UML (identifier="code", obligation=MANDATORY)
    String getCode();

    /**
     * Identifier of a code space within which one or more codes are defined. This code space
     * is optional but is normally included. This code space is often defined by some authority
     * organization, where one organization may define multiple code spaces. The range and format
     * of each Code Space identifier is defined by that code space authority.
     *
     * @return The code space, or <code>null</code> if not available.
     *
     * @deprecated This method is not defined in ISO 19115. It was defined in ISO 19111,
     *             but was abandonned in latest revision in favor of ISO 19115 interface.
     */
    String getCodeSpace();

    /**
     * Identifier of the version of the associated code space or code, as specified
     * by the code space or code authority. This version is included only when the
     * {@linkplain #getCode code} or {@linkplain #getCodeSpace code space} uses versions.
     * When appropriate, the edition is identified by the effective date, coded using
     * ISO 8601 date format.
     *
     * @return The version, or <code>null</code> if not available.
     */
    String getVersion();

    /**
     * Organization or party responsible for definition and maintenance of the
     * {@linkplain #getCodeSpace code space} or {@linkplain #getCode code}.
     *
     * @return The authority, or <code>null</code> if not available.
     */
/// @UML (identifier="authority", obligation=OPTIONAL)
    Citation getAuthority();
}
