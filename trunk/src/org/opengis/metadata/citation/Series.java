/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE direct dependencies
import java.util.Locale;


/**
 * Information about the series, or aggregate dataset, to which a dataset belongs.
 *
 * @UML datatype CI_Series
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Series {
    /**
     * Name of the series, or aggregate dataset, of which the dataset is a part.
     * Returns <code>null</code> if none.
     *
     * @param  locale The desired locale for the name to be returned, or <code>null</code>
     *         for a name in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The name in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML optional name
     */
    String getName(Locale locale);

    /**
     * Information identifying the issue of the series.
     *
     * @UML optional issueIdentification
     */
    String getIssueIdentification();

    /**
     * Details on which pages of the publication the article was published.
     *
     * @UML optional page
     */
    String getPage();
}
