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
package org.opengis.metadata.content;

import java.util.ArrayList;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.nio.charset.Charset;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.GenericName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information identifying the feature catalogue or the conceptual schema.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_FeatureCatalogueDescription", specification=ISO_19115)
public interface FeatureCatalogueDescription extends ContentInformation {
    /**
     * Indication of whether or not the cited feature catalogue complies with ISO 19110.
     * This value is optional, and therefore may be null.
     *
     * @return whether or not the cited feature catalogue complies with ISO 19110, or {@code null}.
     */
    @UML(identifier="complianceCode", obligation=OPTIONAL, specification=ISO_19115)
    default Boolean isCompliant() {
        return null;
    }

    /**
     * Language(s) and character set(s) used within the catalogue.
     * Note that contrarily to the {@code PT_Locale} object defined by ISO 19115:2014, the {@code java.util.Locale}
     * object does not contain character encoding information. The character set information is stored in separated
     * objects, associated to locales through entries in the map.
     *
     * <h4>XML representation</h4>
     * XML documents shall format languages using the ISO 639-2 language code as returned by {@link Locale#getISO3Language()}.
     * Character sets shall be referenced by name from the IANA Character Set register.
     *
     * @departure integration
     *   GeoAPI replaces ISO 19115:2014 {@code LanguageCode}, {@code CountryCode} and {@code MD_CharacterSetCode}
     *   code lists by equivalent objects from the standard Java library.
     *   See {@link org.opengis.metadata.Metadata#getLocalesAndCharsets()} for more information.
     *
     * @return language(s) and character set(s) used within the catalogue.
     *
     * @see org.opengis.metadata.identification.DataIdentification#getLocalesAndCharsets()
     * @see org.opengis.metadata.Metadata#getLocalesAndCharsets()
     *
     * @since 3.1
     */
    @UML(identifier="locale", obligation=OPTIONAL, specification=ISO_19115)
    default Map<Locale,Charset> getLocalesAndCharsets() {
        return Collections.emptyMap();
    }

    /**
     * Language(s) used within the catalogue.
     *
     * @return language(s) used within the catalogue.
     *
     * @deprecated Replaced by {@code getLocalesAndCharsets().keySet()}.
     */
    @Deprecated
    @UML(identifier="language", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<Locale> getLanguages() {
        return getLocalesAndCharsets().keySet();
    }

    /**
     * Indication of whether or not the feature catalogue is included with the resource.
     *
     * @return whether or not the feature catalogue is included with the resource.
     */
    @UML(identifier="includedWithDataset", obligation=OPTIONAL, specification=ISO_19115)
    default boolean isIncludedWithDataset() {
        return false;
    }

    /**
     * Subset of feature types from cited feature catalogue occurring in resource
     * and count of feature instances.
     *
     * @return subset of feature types occurring in resource.
     *
     * @departure rename
     *   Renamed from "{@code featureTypes}" to "{@code featureTypeInfo}" for the following reasons:
     *   <ol>
     *     <li>Avoid name collision with the ISO 19115:2003 definition of "{@code featureTypes}".</li>
     *     <li>Avoid confusion between {@code FeatureTypeInfo} and {@code org.opengis.feature.FeatureType}.
     *         A {@code getFeatureTypes()} method name would suggest that the collection contains the later.</li>
     *   </ol>
     *
     * @since 3.1
     */
    @UML(identifier="featureTypes", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends FeatureTypeInfo> getFeatureTypeInfo() {
        return Collections.emptyList();
    }

    /**
     * Names of the {@linkplain #getFeatureTypes() feature types}.
     *
     * @return names of the feature types.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getFeatureTypeInfo()}.
     */
    @Deprecated
    default Collection<? extends GenericName> getFeatureTypes() {
        final ArrayList<GenericName> names = new ArrayList<>();
        getFeatureTypeInfo().forEach((info) -> names.add(info.getFeatureTypeName()));
        return names;
    }

    /**
     * Complete bibliographic reference to one or more external feature catalogues.
     *
     * @condition Mandatory if the feature catalogue is not included with resource
     * and {@code FeatureCatalogue} is not provided.
     *
     * @return bibliographic reference to one or more external feature catalogues.
     */
    @UML(identifier="featureCatalogueCitation", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Citation> getFeatureCatalogueCitations();
}
