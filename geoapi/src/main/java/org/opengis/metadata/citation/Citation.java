/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.citation;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.identification.BrowseGraphic;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Standardized resource reference.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="CI_Citation", specification=ISO_19115)
public interface Citation {
    /**
     * Name by which the cited resource is known.
     *
     * @return the cited resource name.
     */
    @UML(identifier="title", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getTitle();

    /**
     * Short names or other language names by which the cited information is known.
     * Returns an empty collection if there is none.
     *
     * <div class="note"><b>Example:</b>
     * "DCW" as an alternative title for "Digital Chart of the World".
     * </div>
     *
     * @return other names for the resource, or an empty collection if none.
     */
    @UML(identifier="alternateTitle", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getAlternateTitles() {
        return Collections.emptyList();
    }

    /**
     * Reference dates for the cited resource.
     *
     * @return reference dates for the cited resource.
     */
    @UML(identifier="date", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends CitationDate> getDates() {
        return Collections.emptyList();
    }

    /**
     * Version of the cited resource.
     *
     * @return the version, or {@code null} if none.
     */
    @UML(identifier="edition", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getEdition() {
        return null;
    }

    /**
     * Date of the edition, or {@code null} if none.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The return type of this method may change in GeoAPI 4.0 release. It may be replaced by a
     * type matching more closely either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * </div>
     *
     * @return the edition date, or {@code null} if none.
     */
    @UML(identifier="editionDate", obligation=OPTIONAL, specification=ISO_19115)
    default Date getEditionDate() {
        return null;
    }

    /**
     * Unique identifier for the resource.
     * Returns an empty collection if there is none.
     *
     * <div class="note"><b>Example:</b>
     * Universal Product Code (UPC), National Stock Number (NSN).
     * </div>
     *
     * @return the identifiers, or an empty collection if none.
     */
    @UML(identifier="identifier", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Identifier> getIdentifiers() {
        return Collections.emptyList();
    }

    /**
     * Role, name, contact and position information for individuals or organisations
     * that are responsible for the resource.
     * Returns an empty collection if there is none.
     *
     * @return the information for individuals or organisations that are responsible for the resource,
     *         or an empty collection if none.
     */
    @UML(identifier="citedResponsibleParty", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Responsibility> getCitedResponsibleParties() {
        return Collections.emptyList();
    }

    /**
     * Mode in which the resource is represented.
     * Returns an empty collection if there is none.
     *
     * @return the presentation mode, or an empty collection if none.
     */
    @UML(identifier="presentationForm", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<PresentationForm> getPresentationForms() {
        return Collections.emptySet();                          // Use Set instead of List for hash-safe final classes.
    }

    /**
     * Information about the series, or aggregate dataset, of which the dataset is a part.
     * Returns {@code null} if there is none.
     *
     * @return the series or aggregate dataset of which the dataset is a part, or {@code null} if none.
     */
    @UML(identifier="series", obligation=OPTIONAL, specification=ISO_19115)
    default Series getSeries() {
        return null;
    }

    /**
     * Other information required to complete the citation that is not recorded elsewhere.
     * Returns an empty collection if there is none.
     *
     * @return other details, or an empty collection if none.
     */
    @UML(identifier="otherCitationDetails", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getOtherCitationDetails() {
        return Collections.emptyList();
    }

    /**
     * Common title with holdings note. Title identifies elements of a series collectively,
     * combined with information about what volumes are available at the source cited.
     *
     * @return the common title, or {@code null} if none.
     *
     * @deprecated Removed as of ISO 19115:2014.
     */
    @Deprecated
    @UML(identifier="collectiveTitle", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default InternationalString getCollectiveTitle() {
        return null;
    }

    /**
     * International Standard Book Number.
     * Returns {@code null} if there is none.
     *
     * @return the International Standard Book Number, or {@code null} if none.
     */
    @UML(identifier="ISBN", obligation=OPTIONAL, specification=ISO_19115)
    default String getISBN() {
        return null;
    }

    /**
     * International Standard Serial Number.
     * Returns {@code null} if there is none.
     *
     * @return the International Standard Serial Number, or {@code null} if none.
     */
    @UML(identifier="ISSN", obligation=OPTIONAL, specification=ISO_19115)
    default String getISSN() {
        return null;
    }

    /**
     * Online references to the cited resource.
     * Returns an empty collection if there is none.
     *
     * @return online references to the cited resource, or an empty collection if none.
     *
     * @since 3.1
     */
    @UML(identifier="onlineResource", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends OnlineResource> getOnlineResources() {
        return Collections.emptyList();
    }

    /**
     * Citation graphics or logo for cited party.
     * Returns an empty collection if there is none.
     *
     * @return graphics or logo for cited party, or an empty collection if none.
     *
     * @since 3.1
     */
    @UML(identifier="graphic", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends BrowseGraphic> getGraphics() {
        return Collections.emptyList();
    }
}
