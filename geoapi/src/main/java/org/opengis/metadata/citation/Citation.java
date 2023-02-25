/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.metadata.citation;

import java.util.Collection;
import java.util.Date;
import org.opengis.metadata.Identifier;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Standardized resource reference.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc - - - CitationDate
 * @navassoc - - - Identifier
 * @navassoc - - - ResponsibleParty
 * @navassoc - - - PresentationForm
 * @navassoc 1 - - Series
 */
@UML(identifier="CI_Citation", specification=ISO_19115)
public interface Citation {
    /**
     * Name by which the cited resource is known.
     *
     * @return The cited resource name.
     */
    @Profile(level=CORE)
    @UML(identifier="title", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getTitle();

    /**
     * Short name or other language name by which the cited information is known.
     * Example: "DCW" as an alternative title for "Digital Chart of the World".
     *
     * @return Other names for the resource, or an empty collection if none.
     */
    @UML(identifier="alternateTitle", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends InternationalString> getAlternateTitles();

    /**
     * Reference date for the cited resource.
     *
     * @return The reference date.
     */
    @Profile(level=CORE)
    @UML(identifier="date", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends CitationDate> getDates();

    /**
     * Version of the cited resource.
     *
     * @return The version, or {@code null} if none.
     */
    @UML(identifier="edition", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getEdition();

    /**
     * Date of the edition, or {@code null} if none.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change
     *     in GeoAPI 3.1 release. It may be replaced by a type matching more closely
     *     either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return The edition date, or {@code null} if none.
     */
    @UML(identifier="editionDate", obligation=OPTIONAL, specification=ISO_19115)
    Date getEditionDate();

    /**
     * Unique identifier for the resource. Example: Universal Product Code (UPC),
     * National Stock Number (NSN).
     *
     * @return The identifiers, or an empty collection if none.
     */
    @UML(identifier="identifier", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Identifier> getIdentifiers();

    /**
     * Name and position information for an individual or organization that is responsible
     * for the resource. Returns an empty string if there is none.
     *
     * @return The individual or organization that is responsible, or an empty collection if none.
     */
    @UML(identifier="citedResponsibleParty", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ResponsibleParty> getCitedResponsibleParties();

    /**
     * Mode in which the resource is represented, or an empty string if none.
     *
     * @return The presentation mode, or an empty collection if none.
     */
    @UML(identifier="presentationForm", obligation=OPTIONAL, specification=ISO_19115)
    Collection<PresentationForm> getPresentationForms();

    /**
     * Information about the series, or aggregate dataset, of which the dataset is a part.
     * Returns {@code null} if none.
     *
     * @return The series of which the dataset is a part, or {@code null} if none.
     */
    @UML(identifier="series", obligation=OPTIONAL, specification=ISO_19115)
    Series getSeries();

    /**
     * Other information required to complete the citation that is not recorded elsewhere.
     * Returns {@code null} if none.
     *
     * @return Other details, or {@code null} if none.
     */
    @UML(identifier="otherCitationDetails", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getOtherCitationDetails();

    /**
     * Common title with holdings note. Note: title identifies elements of a series
     * collectively, combined with information about what volumes are available at the
     * source cited. Returns {@code null} if there is no title.
     *
     * @return The common title, or {@code null} if none.
     */
    @UML(identifier="collectiveTitle", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getCollectiveTitle();

    /**
     * International Standard Book Number, or {@code null} if none.
     *
     * @return The ISBN, or {@code null} if none.
     */
    @UML(identifier="ISBN", obligation=OPTIONAL, specification=ISO_19115)
    String getISBN();

    /**
     * International Standard Serial Number, or {@code null} if none.
     *
     * @return The ISSN, or {@code null} if none.
     */
    @UML(identifier="ISSN", obligation=OPTIONAL, specification=ISO_19115)
    String getISSN();
}
