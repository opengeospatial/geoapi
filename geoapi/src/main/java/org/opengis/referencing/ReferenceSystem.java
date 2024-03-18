/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.geoapi.internal.Legacy;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base interface of reference systems by coordinates or by identifiers.
 * A reference system contains the metadata required to interpret spatial location information unambiguously.
 * Two methods to describe spatial location are distinguished:
 *
 * <ul>
 *   <li>Spatial referencing by geographic identifier.
 *       Geographic identifiers are location descriptors such as addresses and grid indexes.</li>
 *   <li>Spatial referencing by coordinates. This specialized case is handled by the
 *       {@link org.opengis.referencing.crs.CoordinateReferenceSystem} subtype.</li>
 * </ul>
 *
 * Reference systems contain the following properties
 * (including those inherited from the {@link IdentifiedObject} parent interface):
 *
 * <ul>
 *   <li>A {@linkplain #getName() name} (e.g. <q>WGS 84 / World Mercator</q>).</li>
 *   <li>Alternative names or {@linkplain #getAlias() aliases}, sometimes used for abbreviations.</li>
 *   <li>{@linkplain #getIdentifiers() Identifiers} allocated by authorities (e.g. “EPSG:3395”).</li>
 *   <li>The {@linkplain ObjectDomain#getDomainOfValidity() domain of validity} in which this reference system is valid
 *       (e.g. <q>World - between 80°S and 84°N</q>).</li>
 *   <li>The {@linkplain ObjectDomain#getScope() scope} or intended usage for this reference system
 *       (e.g. <q>Very small scale mapping</q>).</li>
 *   <li>{@linkplain #getRemarks() Remarks} about this object, including data source information
 *       (e.g. <q>Euro-centric view of world excluding polar areas</q>).</li>
 * </ul>
 *
 * @departure harmonization
 *    The type defined in ISO 19115 has no relationship with ISO 19111.
 *    GeoAPI redefines this type as a subtype of {@link IdentifiedObject}
 *    and the common parent for
 *    {@link org.opengis.referencing.crs.CoordinateReferenceSystem} and
 *    {@link org.opengis.referencing.gazetteer.ReferenceSystemUsingIdentifiers}.
 *    This change makes this interface closer to the legacy
 *    ISO 19115:2003 {@code RS_ReferenceSystem} than to
 *    ISO 19115:2015 {@code MD_ReferenceSystem}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.crs.CoordinateReferenceSystem
 */
@UML(identifier="RS_ReferenceSystem", specification=ISO_19115, version=2003)
public interface ReferenceSystem extends IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code ObjectFactory.createFoo(Map, ...)} methods.
     * This property is kept for compatibility with ISO 19111:2007.
     * However as of ISO 19111:2019, {@link #DOMAINS_KEY} is preferred.
     *
     * @see ObjectFactory
     * @see ObjectDomain#getDomainOfValidity()
     */
    String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@code ObjectFactory.createFoo(Map, ...)} methods.
     * This property is kept for compatibility with ISO 19111:2007.
     * However as of ISO 19111:2019, {@link #DOMAINS_KEY} is preferred.
     *
     * @see ObjectFactory
     * @see ObjectDomain#getScope()
     */
    String SCOPE_KEY = "scope";

    /**
     * Area or region or timeframe in which this (coordinate) reference system is valid.
     *
     * @return the reference system valid domain, or {@code null} if not available.
     *
     * @deprecated Replaced by {@link #getDomains()} as of ISO 19111:2019.
     */
    @Deprecated(since="3.1", forRemoval=true)
    @UML(identifier="domainOfValidity", obligation=OPTIONAL, specification=ISO_19111, version=2007)
    default Extent getDomainOfValidity() {
        return Legacy.getDomainOfValidity(getDomains());
    }

    /**
     * Description of domain of usage, or limitations of usage, for which this
     * Reference System object is valid.
     *
     * @return the domain of usage, or {@code null} if none.
     *
     * @deprecated Replaced by {@link #getDomains()} as of ISO 19111:2019.
     */
    @Deprecated(since="3.1", forRemoval=true)
    @UML(identifier="SC_CRS.scope", obligation=OPTIONAL, specification=ISO_19111, version=2007)
    default InternationalString getScope() {
        return Legacy.getScope(getDomains());
    }
}
