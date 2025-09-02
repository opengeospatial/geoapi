/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import java.util.Map;
import java.util.Locale;
import java.util.Collection;
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
        return Map.of();
    }

    /**
     * Language(s) used within the resource.
     *
     * @return language(s) used.
     *
     * @deprecated Replaced by {@code getLocalesAndCharsets().keySet()}.
     */
    @Deprecated(since="3.1")
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
    @Deprecated(since="3.1")
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
