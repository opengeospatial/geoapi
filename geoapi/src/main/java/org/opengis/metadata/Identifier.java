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
package org.opengis.metadata;

import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Value uniquely identifying an object within a namespace.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - Citation
 */
@UML(identifier="MD_Identifier", specification=ISO_19115)
public interface Identifier {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} <code>createFoo(&hellip;)</code>
     * methods. This is used for setting the value to be returned by {@link #getCode()}.
     *
     * @see #getCode()
     */
    String CODE_KEY = "code";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} <code>createFoo(&hellip;)</code>
     * methods. This is used for setting the value to be returned by {@link #getAuthority()}.
     *
     * @see #getAuthority()
     */
    String AUTHORITY_KEY = "authority";

    /**
     * Alphanumeric value identifying an instance in the namespace.
     *
     * @return Value identifying an instance in the namespace.
     */
    @UML(identifier="code", obligation=MANDATORY, specification=ISO_19115)
    String getCode();

    /**
     * Organization or party responsible for definition and maintenance of the
     * {@linkplain #getCode code}.
     *
     * @return Party responsible for definition and maintenance of the code.
     */
    @UML(identifier="authority", obligation=OPTIONAL, specification=ISO_19115)
    Citation getAuthority();
}
