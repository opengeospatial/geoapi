/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Supplementary identification and remarks information for a CRS or CRS-related object.
 * When {@link org.opengis.referencing.crs.CRSAuthorityFactory} is used to create an object,
 * the {@linkplain ReferenceIdentifier#getAuthority authority} and
 * {@linkplain ReferenceIdentifier#getCode authority code} values shall be set to the
 * authority name of the factory object, and the authority code supplied by the client,
 * respectively. The other values may or may not be set. If the authority is EPSG, the
 * implementer may consider using the corresponding metadata values in the EPSG tables.
 *
 * @departure harmonization
 *   ISO 19111 defines an <code>IdentifiedObjectBase</code> interface. The later is omitted in
 *   GeoAPI because the split between <code>IdentifiedObject</code> and <code>IdentifiedObjectBase</code>
 *   in the ISO/OGC specification was a workaround for introducing <code>IdentifiedObject</code>
 *   in ISO 19111 without changing the <code>ReferenceSystem</code> definition in ISO 19115 but
 *   GeoAPI does not need this workaround.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - GenericName
 * @navassoc - - - ReferenceIdentifier
 */
@UML(identifier="IO_IdentifiedObject", specification=ISO_19111)
public interface IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} <code>createFoo(&hellip;)</code> methods.
     * This is used for setting the value to be returned by {@link #getName()}.
     *
     * @see #getName()
     */
    String NAME_KEY = "name";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} <code>createFoo(&hellip;)</code> methods.
     * This is used for setting the value to be returned by {@link #getAlias()}.
     *
     * @see #getAlias()
     */
    String ALIAS_KEY = "alias";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} <code>createFoo(&hellip;)</code> methods.
     * This is used for setting the value to be returned by {@link #getIdentifiers()}.
     *
     * @see #getIdentifiers()
     */
    String IDENTIFIERS_KEY = "identifiers";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} <code>createFoo(&hellip;)</code> methods.
     * This is used for setting the value to be returned by {@link #getRemarks()}.
     *
     * @see #getRemarks()
     */
    String REMARKS_KEY = "remarks";

    /**
     * The primary name by which this object is identified.
     *
     * @return The primary name.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19111)
    ReferenceIdentifier getName();

    /**
     * An alternative name by which this object is identified.
     *
     * @return The aliases, or an empty collection if there is none.
     */
    @UML(identifier="alias", obligation=OPTIONAL, specification=ISO_19111)
    Collection<GenericName> getAlias();

    /**
     * An identifier which references elsewhere the object's defining information.
     * Alternatively an identifier by which this object can be referenced.
     *
     * @return This object identifiers, or an empty set if there is none.
     */
    @UML(identifier="identifier", obligation=OPTIONAL, specification=ISO_19111)
    Set<ReferenceIdentifier> getIdentifiers();

    /**
     * Comments on or information about this object, including data source information.
     *
     * @return The remarks, or {@code null} if none.
     */
    @UML(identifier="remarks", obligation=OPTIONAL, specification=ISO_19111)
    InternationalString getRemarks();

    /**
     * Returns a <A HREF="doc-files/WKT.html"><cite>Well Known Text</cite> (WKT)</A> for this object.
     * This operation may fails if an object is too complex for the WKT format capability (for
     * example an {@linkplain org.opengis.referencing.crs.EngineeringCRS engineering CRS} with
     * different unit for each axis).
     *
     * @return The Well Know Text for this object.
     * @throws UnsupportedOperationException If this object cannot be formatted as WKT.
     *
     * @departure extension
     *   This method is not part of the OGC specification. It has been added in order to provide
     *   the converse of the <code>CRSFactory.createFromWKT(String)</code> method, which is
     *   defined in OGC 01-009.
     */
    String toWKT() throws UnsupportedOperationException;
}
