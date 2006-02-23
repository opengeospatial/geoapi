/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.Citation;


/**
 * An identification of a CRS object. The first use of an <code>Identifier</code> for an object,
 * if any, is normally the primary identification code, and any others are aliases.
 *
 * @UML datatype RS_Identifier
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface Identifier {
    /**
     * Identifier code or name, optionally from a controlled list or pattern
     * defined by a {@linkplain #getCodeSpace code space}.
     *
     * @return The code.
     * @UML mandatory code
     */
    String getCode();

    /**
     * Identifier of a code space within which one or more codes are defined. This code space
     * is optional but is normally included. This code space is often defined by some authority
     * organization, where one organization may define multiple code spaces. The range and format
     * of each Code Space identifier is defined by that code space authority.
     *
     * @return The code space, or <code>null</code> if not available.
     * @UML optional codeSpace
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
     * @UML optional version
     */
    String getVersion();

    /**
     * Organization or party responsible for definition and maintenance of the
     * {@linkplain #getCodeSpace code space} or {@linkplain #getCode code}.
     *
     * @return The authority, or <code>null</code> if not available.
     * @UML optional authority
     */
    Citation getAuthority();

    /**
     * Comments on or information about this identifier. In the first use of an
     * <code>Identifier</code> for an {@link Info} object, these remarks are information about this
     * object, including data source information. Additional uses of a <code>Identifier</code>
     * for an object, if any, are aliases, and the remarks are then about that alias.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default locale).
     * @return The remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    String getRemarks(Locale locale);
}
