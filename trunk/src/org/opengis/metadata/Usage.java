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
import java.util.Date;
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.ResponsibleParty;


/**
 * Brief description of ways in which the resource(s) is/are currently used.
 *
 * @UML datatype MD_Usage
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Usage {
    /**
     * Brief description of the resource and/or resource series usage.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML mandatory specificUsage
     */
    String getSpecificUsage(Locale locale);

    /**
     * Date and time of the first use or range of uses of the resource and/or resource series.
     *
     * @UML optional usageDateTime
     */
    Date getUsageDate();

    /**
     * Applications, determined by the user for which the resource and/or resource series
     * is not suitable.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional userDeterminedLimitations
     */
    String getUserDeterminedLimitations(Locale locale);

    /**
     * Identification of and means of communicating with person(s) and organization(s)
     * using the resource(s).
     *
     * @UML mandatory userContactInfo
     */
    ResponsibleParty[] getUserContactInfo();
}
