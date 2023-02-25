/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.metadata;

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Value uniquely identifying an object within a namespace.
 *
 * <div class="note"><b>Example:</b>
 * for the WGS 84 geographic coordinate reference system,
 * {@code code}        = {@code "4326"},
 * {@code codeSpace}   = {@code "EPSG"},
 * {@code description} = {@code "WGS 84"} and
 * {@code authority}   = OGP geodetic committee.</div>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="MD_Identifier", specification=ISO_19115)
public interface Identifier {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getAuthority()}.
     *
     * @see #getAuthority()
     */
    String AUTHORITY_KEY = "authority";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getCode()}.
     *
     * @see #getCode()
     */
    String CODE_KEY = "code";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getCodeSpace()}.
     *
     * @see #getCodeSpace()
     */
    String CODESPACE_KEY = "codespace";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getVersion()}.
     *
     * @see #getVersion()
     */
    String VERSION_KEY = "version";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getDescription()}.
     *
     * @see #getDescription()
     *
     * @since 3.1
     */
    String DESCRIPTION_KEY = "description";

    /**
     * Person or party responsible for maintenance of the namespace.
     *
     * @return the person or party responsible for maintenance of the namespace, or {@code null} if none.
     */
    @UML(identifier="authority", obligation=OPTIONAL, specification=ISO_19115)
    default Citation getAuthority() {
        return null;
    }

    /**
     * Alphanumeric value identifying an instance in the namespace.
     * Should avoid characters that are not legal in URLs.
     *
     * <div class="note"><b>Example:</b> {@code "4326"}.</div>
     *
     * @return value identifying an instance in the namespace.
     */
    @UML(identifier="code", obligation=MANDATORY, specification=ISO_19115)
    String getCode();

    /**
     * Identifier or namespace in which the code is valid.
     *
     * <div class="note"><b>Example:</b> {@code "EPSG"}.</div>
     *
     * @return the identifier or namespace in which the code is valid, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="codeSpace", obligation=OPTIONAL, specification=ISO_19115)
    default String getCodeSpace() {
        return null;
    }

    /**
     * Version identifier for the namespace, as specified by the code authority.
     * When appropriate, the edition is identified by the effective date, coded
     * using ISO 8601 date format.
     *
     * <div class="note"><b>Example:</b>
     * the version of the underlying EPSG database.</div>
     *
     * @return the version identifier for the namespace, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="version", obligation=OPTIONAL, specification=ISO_19115)
    default String getVersion() {
        return null;
    }

    /**
     * Natural language description of the meaning of the code value.
     *
     * <div class="note"><b>Example:</b> for {@code codeSpace = "EPSG"} and {@code code = "4326"},
     * the description can be "WGS 84".</div>
     *
     * @return the natural language description, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getDescription() {
        return null;
    }
}
