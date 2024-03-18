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
 *   <li>A {@linkplain #getName() name} (e.g. <q>North American Datum of 1983</q>).</li>
 *   <li>Alternative names or {@linkplain #getAlias() aliases} (e.g. <q>NAD83</q> abbreviation).</li>
 *   <li>{@linkplain #getIdentifiers() Identifiers} allocated by authorities
 *       (e.g. a register of geodetic codes and parameters might give the NAD83 datum a unique code of <q>6269</q>).</li>
 *   <li>{@linkplain #getRemarks() Remarks} about this object, including data source information.</li>
 * </ul>
 *
 * Some typical {@code IdentifiedObject} sub-types are:
 *
 * <ul>
 *   <li>{@linkplain org.opengis.referencing.datum.GeodeticDatum Geodetic Datum} (e.g. <cite>World Geodetic System 1984</cite>),</li>
 *   <li>{@linkplain org.opengis.referencing.operation.OperationMethod Operation Method} (e.g. <cite>Mercator (variant A)</cite>),</li>
 *   <li>{@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem Coordinate Reference System} (e.g. <cite>WGS 84 / World Mercator</cite>).</li>
 * </ul>
 *
 * When {@link org.opengis.referencing.crs.CRSAuthorityFactory} is used to create an object,
 * the {@linkplain Identifier#getAuthority() authority} and {@linkplain Identifier#getCode()
 * authority code} values shall be set to the authority name of the factory object,
 * and the authority code supplied by the client, respectively.
 * The other values may or may not be set.
 * If the authority is EPSG, the implementer may consider using the corresponding metadata values in the EPSG tables.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="IdentifiedObject", specification=ISO_19111)
public interface IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code ObjectFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getName()}.
     *
     * @see ObjectFactory
     * @see #getName()
     */
    String NAME_KEY = "name";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code ObjectFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getAlias()}.
     *
     * @see ObjectFactory
     * @see #getAlias()
     */
    String ALIAS_KEY = "alias";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code ObjectFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getIdentifiers()}.
     *
     * @see ObjectFactory
     * @see #getIdentifiers()
     */
    String IDENTIFIERS_KEY = "identifiers";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code ObjectFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getDomains()}.
     *
     * @see ObjectFactory
     * @see #getDomains()
     *
     * @since 3.1
     */
    String DOMAINS_KEY = "domains";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code ObjectFactory.createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getRemarks()}.
     *
     * @see ObjectFactory
     * @see #getRemarks()
     */
    String REMARKS_KEY = "remarks";

    /**
     * The primary name by which this object is identified.
     *
     * @return the primary name.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19111)
    Identifier getName();

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
     * @return this object identifiers, or an empty collection if there is none.
     */
    @UML(identifier="identifier", obligation=OPTIONAL, specification=ISO_19111)
    default Set<Identifier> getIdentifiers() {
        return Collections.emptySet();
    }

    /**
     * Usage of this CRS-related object.
     * The domain includes a scope (description of the primary purpose of this object) together
     * with a domain of validity (spatial and temporal extent in which the object can be used).
     * Those properties are paired together for facilitating descriptions of usage such as
     * "Purpose 1 in area A, purpose 2 in area B".
     *
     * @return scopes and domains of validity of this object.
     *
     * @departure generalization
     *   ISO 19111 defines this property in an {@code ObjectUsage} subtype so that only the
     *   {@link org.opengis.referencing.datum.Datum},
     *   {@link org.opengis.referencing.crs.CoordinateReferenceSystem} and
     *   {@link org.opengis.referencing.operation.CoordinateOperation} subtypes inherit this property.
     *   GeoAPI relaxes this restriction for two reasons:
     *   <ul>
     *     <li>A need to specify a scope can also occur in other subtypes.
     *         For example a Minkowski {@link org.opengis.referencing.cs.CoordinateSystem}
     *         may want to specify "For objects moving at relativistic speed" scope.</li>
     *     <li>The {@code ObjectUsage} type name is at odd with
     *         the semantics of subclassing as an “is type of” hierarchy.
     *         Qualifying a CRS as “a type of object usage” is restrictive.
     *         Instead, CRS <em>contains</em> a description of object usage.
     *         Omitting the {@code ObjectUsage} subtype avoids this semantic oddity.</li>
     *   </ul>
     *
     * @since 3.1
     */
    @UML(identifier="ObjectUsage.domain", obligation=OPTIONAL, specification=ISO_19111)
    default Collection<ObjectDomain> getDomains() {
        return Collections.emptyList();
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
     * Returns a <i>Well-Known Text</i> (WKT) for this object.
     * Well-Known Texts (WKT) may come in two formats:
     *
     * <ul>
     *   <li>The current standard, WKT 2, is defined by {@linkplain org.opengis.annotation.Specification#ISO_19162 ISO 19162}.</li>
     *   <li>The legacy format, WKT 1, was defined by {@linkplain org.opengis.annotation.Specification#OGC_01009 OGC 01-009}.</li>
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
