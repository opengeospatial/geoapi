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

// J2SE direct dependencies
import java.util.Locale;


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
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional userNote
     */
    String getUserNote(Locale locale);

    /**
     * Name of the classification system.
     *
     * @param  locale The desired locale for the name to be returned, or <code>null</code>
     *         for a name in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The name in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML optional classificationSystem
     */
    String getClassificationSystem(Locale locale);

    /**
     * Additional information about the restrictions on handling the resource.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional handlingDescription
     */
    String getHandlingDescription(Locale locale);
}
