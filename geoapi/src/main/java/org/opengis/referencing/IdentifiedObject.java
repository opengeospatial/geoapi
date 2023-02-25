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
package org.opengis.referencing;

import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.metadata.Identifier;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification and remarks information for a reference system or CRS-related object.
 * Identified objects contain the following properties:
 *
 * <ul>
 *   <li>A {@linkplain #getName() name} (e.g. “<cite>North American Datum of 1983</cite>”).</li>
 *   <li>Alternative names or {@linkplain #getAlias() aliases} (e.g. “NAD83” abbreviation).</li>
 *   <li>{@linkplain #getIdentifiers() Identifiers} allocated by authorities
 *       (e.g. a register of geodetic codes and parameters might give the NAD83 datum a unique code of “6269”).</li>
 *   <li>{@linkplain #getRemarks() Remarks} about this object, including data source information.</li>
 * </ul>
 *
 * Some typical {@code IdentifiedObject} sub-types are:
 *
 * <ul>
 *   <li>{@linkplain org.opengis.referencing.datum.GeodeticDatum Geodetic Datum} (e.g. “<cite>World Geodetic System 1984</cite>”),</li>
 *   <li>{@linkplain org.opengis.referencing.operation.OperationMethod Operation Method} (e.g. “<cite>Mercator (variant A)</cite>”),</li>
 *   <li>{@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem Coordinate Reference System} (e.g. “<cite>WGS 84 / World Mercator</cite>”).</li>
 * </ul>
 *
 * When {@link org.opengis.referencing.crs.CRSAuthorityFactory} is used to create an object,
 * the {@linkplain Identifier#getAuthority() authority} and {@linkplain Identifier#getCode()
 * authority code} values shall be set to the authority name of the factory object,
 * and the authority code supplied by the client, respectively.
 * The other values may or may not be set.
 * If the authority is EPSG, the implementer may consider using the corresponding metadata values in the EPSG tables.
 *
 * @departure harmonization
 *   ISO 19111 defines two types, {@code IO_IdentifiedObjectBase} and {@code IO_IdentifiedObject}, as a
 *   workaround for introducing a base type for the {@code name}, {@code identifier}, {@code alias}
 *   and {@code remarks} properties without changing the {@code RS_ReferenceSystem} definition inherited
 *   from ISO 19115. Since GeoAPI replaces ISO 19115 CRS definitions by the ISO 19111 ones for providing a unified
 *   model, it does not need this workaround. Consequently, GeoAPI merges {@code IO_IdentifiedObjectBase} and
 *   {@code IO_IdentifiedObject} into this single interface.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="IO_IdentifiedObject", specification=ISO_19111)
public interface IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getName()}.
     *
     * @see #getName()
     */
    String NAME_KEY = "name";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getAlias()}.
     *
     * @see #getAlias()
     */
    String ALIAS_KEY = "alias";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getIdentifiers()}.
     *
     * @see #getIdentifiers()
     */
    String IDENTIFIERS_KEY = "identifiers";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getRemarks()}.
     *
     * @see #getRemarks()
     */
    String REMARKS_KEY = "remarks";

    /**
     * The primary name by which this object is identified.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ReferenceIdentifier} has been merged with its {@link Identifier} parent interface.
     * Consequently this method return type will be changed to {@code Identifier} in GeoAPI 4.0.
     * </div>
     *
     * @return the primary name.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19111)
    ReferenceIdentifier getName();

    /**
     * Alternative names by which this object is identified.
     *
     * @return alternative names and abbreviations, or an empty collection if there is none.
     */
    @UML(identifier="alias", obligation=OPTIONAL, specification=ISO_19111)
    default Collection<GenericName> getAlias() {
        return Collections.emptyList();
    }

    /**
     * An identifier which references elsewhere the object's defining information.
     * Alternatively, an identifier by which this object can be referenced.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ReferenceIdentifier} has been merged with its {@link Identifier} parent interface.
     * Consequently the element type will be changed to {@code Identifier} in GeoAPI 4.0.
     * </div>
     *
     * @return this object identifiers, or an empty collection if there is none.
     */
    @UML(identifier="identifier", obligation=OPTIONAL, specification=ISO_19111)
    default Set<ReferenceIdentifier> getIdentifiers() {
        return Collections.emptySet();
    }

    /**
     * Comments on or information about this object, including data source information.
     *
     * @return the remarks, or {@code null} if none.
     */
    @UML(identifier="remarks", obligation=OPTIONAL, specification=ISO_19111)
    default InternationalString getRemarks() {
        return null;
    }

    /**
     * Returns a <cite>Well-Known Text</cite> (WKT) for this object.
     * Well-Known Texts (WKT) may come in two formats:
     *
     * <ul>
     *   <li>The current standard, WKT 2, is defined by ISO 19162.</li>
     *   <li>The legacy format, WKT 1, was defined by {@linkplain org.opengis.annotation.Specification#OGC_01009 OGC 01-009}
     *       and is shown using Extended Backus Naur Form (EBNF) <a href="doc-files/WKT.html">here</a>.</li>
     * </ul>
     *
     * Implementations are encouraged to format according the most recent standard.
     * This operation may fail if unsupported or if this instance contains elements that do not have
     * WKT representation.
     *
     * @return the Well-Known Text (WKT) for this object.
     * @throws UnsupportedOperationException if this object cannot be formatted as WKT.
     *
     * @departure extension
     *   This method is not part of the OGC specification. It has been added in order to provide the
     *   converse of the {@code CRSFactory.createFromWKT(String)} method, which is defined in OGC 01-009.
     *
     * @see org.opengis.referencing.crs.CRSFactory#createFromWKT(String)
     */
    default String toWKT() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
