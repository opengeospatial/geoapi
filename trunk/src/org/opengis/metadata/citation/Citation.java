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
import java.util.Date;
import java.util.Locale;


/**
 * Standardized resource reference.
 *
 * @UML datatype CI_Citation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Citation {
    /**
     * Name by which the cited resource is known.
     *
     * @param  locale The desired locale for the title to be returned, or <code>null</code>
     *         for a title in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The citation title in the given locale.
     *         If no title is available in the given locale, then some default locale is used.
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
     *         If no title is available in the given locale, then some default locale is used.
     * @UML optional alternateTitle
     */
    String[] getAlternateTitles(Locale locale);

    /**
     * Reference date for the cited resource.
     *
     * @UML mandatory date
     *
     * @revisit ISO 19115 use <code>CI_Date</code> here.
     */
    Date[] getDate();

    /**
     * Version of the cited resource.
     *
     * @param  locale The desired locale for the edition to be returned, or <code>null</code>
     *         for an edition in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The edition in the given locale.
     *         If no edition is available in the given locale, then some default locale is used.
     *
     * @UML optional edition
     */
    String getEdition(Locale locale);

    /**
     * Date of the edition, or <code>null</code> if none.
     *
     * @UML optional editionDate
     */
    Date getEditionDate();

    /**
     * Unique identifier for the resource. Example: Universal Product Code (UPC),
     * National Stock Number (NSN).
     *
     * @UML optional identifier
     *
     * @revisit Should we merge this method with {@link #getIdentifierTypes} and returns a
     *          {@link java.util.Map} instead?
     */
    String[] getIdentifiers();

    /**
     * Reference form of the unique identifier (ID). Example: Universal Product Code (UPC),
     * National Stock Number (NSN).
     *
     * @UML optional identifierType
     *
     * @revisit Should we merge this method with {@link #getIdentifiers} and returns a
     *          {@link java.util.Map} instead?
     */
    String[] getIdentifierTypes();

    /**
     * Name and position information for an individual or organization that is responsible
     * for the resource. Returns an empty string if there is none.
     *
     * @UML optional citedResponsibleParty
     */
    ResponsibleParty[] getCitedResponsibleParties();

    /**
     * Mode in which the resource is represented, or an empty string if none.
     *
     * @UML optional presentationForm
     */
    // PresentationFormCode[] getPresentationForm();

    /**
     * Information about the series, or aggregate dataset, of which the dataset is a part.
     * Returns <code>null</code> if none.
     *
     * @param  locale The desired locale for the series to be returned, or <code>null</code>
     *         for a series in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The series in the given locale.
     *         If no series is available in the given locale, then some default locale is used.
     * @UML optional series
     */
    Series getSeries(Locale locale);

    /**
     * Other information required to complete the citation that is not recorded elsewhere.
     * Returns <code>null</code> if none.
     *
     * @param  locale The desired locale for the details to be returned, or <code>null</code>
     *         for details in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The details in the given locale.
     *         If no details is available in the given locale, then some default locale is used.
     * @UML optional otherCitationDetails
     */
    String getOtherCitationDetails(Locale locale);

    /**
     * Common title with holdings note. Note: title identifies elements of a series
     * collectively, combined with information about what volumes are available at the
     * source cited. Returns <code>null</code> if there is no title.
     *
     * @param  locale The desired locale for the title to be returned, or <code>null</code>
     *         for a title in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The title in the given locale.
     *         If no title is available in the given locale, then some default locale is used.
     * @UML optional collectiveTitle
     */
    String getCollectiveTitle(Locale locale);

    /**
     * International Standard Book Number, or <code>null</code> if none.
     *
     * @UML optional ISBN
     */
    String getISBN();

    /**
     * International Standard Serial Number, or <code>null</code> if none.
     *
     * @UML optional ISSN
     */
    String getISSN();
}
