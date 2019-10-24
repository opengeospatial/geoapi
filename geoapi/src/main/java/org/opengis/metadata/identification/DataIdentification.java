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
package org.opengis.metadata.identification;

import java.util.Map;
import java.util.Locale;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.nio.charset.Charset;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information required to identify a resource.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_DataIdentification", specification=ISO_19115)
public interface DataIdentification extends Identification {
    /**
     * Language(s) and character set(s) used within the resource.
     * The first element in iteration order shall be the default language.
     * All other elements, if any, are alternate language(s) used within the resource.
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
     * @return language(s) and character set(s) used within the resource.
     *
     * @condition Mandatory if language used in resource.
     *
     * @see org.opengis.metadata.Metadata#getLocalesAndCharsets()
     * @see org.opengis.metadata.content.FeatureCatalogueDescription#getLocalesAndCharsets()
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    // Obligation note: `defaultLocale` is conditional and `otherLocale` is optional.
    @UML(identifier="defaultLocale+otherLocale", obligation=CONDITIONAL, specification=ISO_19115)
    default Map<Locale,Charset> getLocalesAndCharsets() {
        return Collections.emptyMap();
    }

    /**
     * Language(s) used within the resource.
     *
     * @return language(s) used.
     *
     * @deprecated Replaced by {@code getLocalesAndCharsets().keySet()}.
     */
    @Deprecated
    @UML(identifier="language", obligation=MANDATORY, specification=ISO_19115, version=2003)
    default Collection<Locale> getLanguages() {
        return getLocalesAndCharsets().keySet();
    }

    /**
     * The character coding standard(s) used for the dataset.
     *
     * <div class="warning"><b>Upcoming API change — JDK integration</b><br>
     * As of ISO 19115:2014, {@code CharacterSet} is replaced by a reference to the
     * <a href="http://www.iana.org/assignments/character-sets">IANA Character Set register</a>,
     * which is represented in Java by {@link java.nio.charset.Charset}.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return the character coding standard(s) used.
     *
     * @deprecated Replaced by {@code getLocalesAndCharsets().values()}.
     */
    @Deprecated
    @UML(identifier="characterSet", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    default Collection<CharacterSet> getCharacterSets() {
        LinkedHashSet<CharacterSet> codes = new LinkedHashSet<>();
        getLocalesAndCharsets().values().forEach((cs) -> {
            codes.add(CharacterSet.fromCharset(cs));
        });
        return codes;
    }

    /**
     * Description of the resource in the producer's processing environment, including items
     * such as the software, the computer operating system, file name, and the dataset size.
     *
     * @return description of the resource in the producer's processing environment, or {@code null}.
     */
    @UML(identifier="environmentDescription", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getEnvironmentDescription() {
        return null;
    }

    /**
     * Any other descriptive information about the resource.
     *
     * @return other descriptive information, or {@code null}.
     */
    @UML(identifier="supplementalInformation", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getSupplementalInformation() {
        return null;
    }
}
