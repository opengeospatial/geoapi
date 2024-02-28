/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
    @Deprecated(since="3.1")
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
