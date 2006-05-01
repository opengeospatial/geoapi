/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE direct dependencies
import java.util.Locale;


/**
 * Standardized resource reference.
 *
 * @UML datatype CI_Citation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 5.0
 *
 * @revisit Methods not yet defined for this interface.
 */
public interface Citation {
    /**
     * Name by which the cited resource is known.
     *
     * @param  locale The desired locale for the title to be returned, or <code>null</code>
     *         for a title in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The citation title in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML mandatory title
     */
    String getTitle(Locale locale);

    /**
     * Short name or other language name by which the cited information is known.
     * Example: "DCW" as an alternative title for "Digital Chart of the World.
     *
     * @param  locale The desired locale for the title to be returned, or <code>null</code>
     *         for a title in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The citation title in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML optional alternateTitle
     */
    String[] getAlternateTitles(Locale locale);
}
