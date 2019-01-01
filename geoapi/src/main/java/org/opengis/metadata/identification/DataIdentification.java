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

import java.util.Collection;
import java.util.List;
import java.util.Locale;
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
     * Language(s) used within the resource.
     * The first element in iteration order shall be the default language.
     * All other elements, if any, are alternate language(s) used within the resource.
     *
     * <p>XML documents shall format languages using the ISO 639-2 language code
     * as returned by {@link Locale#getISO3Language()}.</p>
     *
     * @return language(s) used.
     *
     * @departure historic
     *   ISO 19115:2014 defines {@code defaultLocale} and {@code otherLocale(s)} attributes, who's data
     *   type ({@code PT_Locale}) combines the language and character encoding information into a single class.
     *   However this design does not fit well with the Java model.
     *   For example the character encoding information is irrelevant to {@code InternationalString}
     *   since the Java language fixes the encoding of all {@code String} instances to UTF-16.
     *   Consequently GeoAPI keeps the {@code language(s)} and {@code characterSet(s)} attributes
     *   as separated entities, as defined in ISO 19115:2003.
     *   GeoAPI also keeps default and other locales in a single collection for compatibility with standard Java
     *   methods like {@code Locale.lookup(List<Locale.LanguageRange>, Collection<Locale>)},
     *   which provides elaborated mechanism for choosing the best suited locale for a user.
     *
     * @see #getCharacterSets()
     * @see org.opengis.metadata.Metadata#getLanguage()
     * @see Locale#getISO3Language()
     * @see Locale#lookup(List, Collection)
     */
    @Profile(level=CORE)
    @UML(identifier="language", obligation=MANDATORY, specification=ISO_19115, version=2003)
    Collection<Locale> getLanguages();

    /**
     * The character coding standard(s) used for the dataset.
     * Instances can be obtained by a call to {@link Charset#forName(String)}.
     *
     * <div class="note"><b>Examples:</b>
     * {@code UCS-2}, {@code UCS-4}, {@code UTF-7}, {@code UTF-8}, {@code UTF-16},
     * {@code ISO-8859-1} (a.k.a. {@code ISO-LATIN-1}), {@code ISO-8859-2}, {@code ISO-8859-3}, {@code ISO-8859-4},
     * {@code ISO-8859-5}, {@code ISO-8859-6}, {@code ISO-8859-7}, {@code ISO-8859-8}, {@code ISO-8859-9},
     * {@code ISO-8859-10}, {@code ISO-8859-11}, {@code ISO-8859-12}, {@code ISO-8859-13}, {@code ISO-8859-14},
     * {@code ISO-8859-15}, {@code ISO-8859-16},
     * {@code JIS_X0201}, {@code Shift_JIS}, {@code EUC-JP}, {@code US-ASCII}, {@code EBCDIC}, {@code EUC-KR},
     * {@code Big5}, {@code GB2312}.
     * </div>
     *
     * @return the character coding standard(s) used.
     *
     * @departure historic
     *   GeoAPI has kept the {@code language} and {@code characterSet} properties as defined in ISO 19115:2003.
     *   See {@code getLanguages()} for more information.
     *
     * @see #getLanguages()
     * @see org.opengis.metadata.Metadata#getCharacterSets()
     * @see Charset#forName(String)
     */
    @Profile(level=CORE)
    @UML(identifier="characterSet", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    Collection<Charset> getCharacterSets();

    /**
     * Description of the resource in the producer's processing environment, including items
     * such as the software, the computer operating system, file name, and the dataset size.
     *
     * @return description of the resource in the producer's processing environment, or {@code null}.
     */
    @UML(identifier="environmentDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getEnvironmentDescription();

    /**
     * Any other descriptive information about the resource.
     *
     * @return other descriptive information, or {@code null}.
     */
    @UML(identifier="supplementalInformation", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getSupplementalInformation();
}
