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
package org.opengis.metadata.identification;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Methods used to group similar keywords.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_KeywordTypeCode", specification=ISO_19115)
public final class KeywordType extends CodeList<KeywordType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4726629268565235927L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<KeywordType> VALUES = new ArrayList<>(15);

    /**
     * Keyword identifies a branch of instruction or specialized learning.
     */
    @UML(identifier="discipline", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType DISCIPLINE = new KeywordType("DISCIPLINE");

    /**
     * Keyword identifies a location.
     */
    @UML(identifier="place", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType PLACE = new KeywordType("PLACE");

    /**
     * Keyword identifies the layer(s) of any deposited substance.
     */
    @UML(identifier="stratum", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType STRATUM = new KeywordType("STRATUM");

    /**
     * Keyword identifies a time period related to the dataset.
     */
    @UML(identifier="temporal", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType TEMPORAL = new KeywordType("TEMPORAL");

    /**
     * Keyword identifies a particular subject or topic.
     */
    @UML(identifier="theme", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType THEME = new KeywordType("THEME");

    /**
     * Keyword identifies a repository or archive that manages and distributes data.
     *
     * @since 3.1
     */
    @UML(identifier="dataCentre", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType DATA_CENTRE = new KeywordType("DATA_CENTRE");

    /**
     * Keyword identifies a resource containing or about a collection of
     * feature instance with common characteristics.
     *
     * @since 3.1
     */
    @UML(identifier="featureType", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType FEATURE_TYPE = new KeywordType("FEATURE_TYPE");

    /**
     * Keyword identifies a device used to measure or compare physical properties.
     *
     * @since 3.1
     */
    @UML(identifier="instrument", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType INSTRUMENT = new KeywordType("INSTRUMENT");

    /**
     * Keyword identifies a structure upon which an instrument is mounted.
     *
     * @since 3.1
     */
    @UML(identifier="platform", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType PLATFORM = new KeywordType("PLATFORM");

    /**
     * Keyword identifies a series of actions or natural occurrences.
     *
     * @since 3.1
     */
    @UML(identifier="process", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType PROCESS = new KeywordType("PROCESS");


    /**
     * Keyword identify an endeavour undertaken to create or modify a product or service.
     *
     * @since 3.1
     */
    @UML(identifier="project", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType PROJECT = new KeywordType("PROJECT");

    /**
     * Keyword identifies an activity carried out by one party for the benefit of another.
     *
     * @since 3.1
     */
    @UML(identifier="service", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType SERVICE = new KeywordType("SERVICE");

    /**
     * Keyword identifies a type of product.
     *
     * @since 3.1
     */
    @UML(identifier="product", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType PRODUCT = new KeywordType("PRODUCT");

    /**
     * Refinement of a topic category for the purpose of geographic data classification.
     *
     * @since 3.1
     */
    @UML(identifier="subTopicCategory", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType SUB_TOPIC_CATEGORY = new KeywordType("SUB_TOPIC_CATEGORY");

    /**
     * Keyword identifies a taxonomy of the resource.
     *
     * @since 3.1
     */
    @UML(identifier="taxon", obligation=CONDITIONAL, specification=ISO_19115)
    public static final KeywordType TAXON = new KeywordType("TAXON");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private KeywordType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code KeywordType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static KeywordType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(KeywordType[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public KeywordType[] family() {
        return values();
    }

    /**
     * Returns the KeywordType that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static KeywordType valueOf(String code) {
        return valueOf(KeywordType.class, code);
    }
}
