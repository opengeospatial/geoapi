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
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Date;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;


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
     * @UML mandatory title
     */
    InternationalString getTitle();

    /**
     * Short name or other language name by which the cited information is known.
     * Example: "DCW" as an alternative title for "Digital Chart of the World.
     *
     * @UML optional alternateTitle
     */
    List/*<InternationalString>*/ getAlternateTitles();

    /**
     * Reference date for the cited resource.
     *
     * @UML mandatory date
     *
     * @revisit ISO 19115 use <code>CI_Date</code> here. Note that {@link #getEditionDate}
     *          returns a J2SE {@link Date} object. Should we remove the later method and
     *          add a <code>EDITION</code> enum in {@link DateType} instead?
     */
    Map/*<DateType,Date>*/ getDates();

    /**
     * Version of the cited resource.
     *
     * @UML optional edition
     */
    InternationalString getEdition();

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
    Set/*<String>*/ getIdentifiers();

    /**
     * Reference form of the unique identifier (ID). Example: Universal Product Code (UPC),
     * National Stock Number (NSN).
     *
     * @UML optional identifierType
     *
     * @revisit Should we merge this method with {@link #getIdentifiers} and returns a
     *          {@link java.util.Map} instead?
     */
    Set/*<String>*/ getIdentifierTypes();

    /**
     * Name and position information for an individual or organization that is responsible
     * for the resource. Returns an empty string if there is none.
     *
     * @UML optional citedResponsibleParty
     */
    Set/*<ResponsibleParty>*/ getCitedResponsibleParties();

    /**
     * Mode in which the resource is represented, or an empty string if none.
     *
     * @UML optional presentationForm
     */
    Set/*<PresentationForm>*/ getPresentationForm();

    /**
     * Information about the series, or aggregate dataset, of which the dataset is a part.
     * Returns <code>null</code> if none.
     *
     * @UML optional series
     */
    Series getSeries();

    /**
     * Other information required to complete the citation that is not recorded elsewhere.
     * Returns <code>null</code> if none.
     *
     * @UML optional otherCitationDetails
     */
    InternationalString getOtherCitationDetails();

    /**
     * Common title with holdings note. Note: title identifies elements of a series
     * collectively, combined with information about what volumes are available at the
     * source cited. Returns <code>null</code> if there is no title.
     *
     * @UML optional collectiveTitle
     */
    InternationalString getCollectiveTitle();

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
