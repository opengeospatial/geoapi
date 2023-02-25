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

import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of a spatial and temporal reference system used by a dataset.
 *
 * @departure historic
 *   This interface was initially derived from an ISO 19111 specification published in 2003. Later
 *   revisions (in 2005) rely on an interface defined in ISO 19115 instead. The annotations were
 *   updated accordingly, but this interface is still defined in the referencing package instead
 *   of the metadata package for this historical reason.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.referencing.crs.CoordinateReferenceSystem
 *
 * @navassoc 1 - - Extent
 */
@UML(identifier="RS_ReferenceSystem", specification=ISO_19115)
public interface ReferenceSystem extends IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} <code>createFoo(&hellip;)</code> methods.
     * This is used for setting the value to be returned by {@link #getDomainOfValidity()}.
     *
     * @see #getDomainOfValidity()
     *
     * @since 2.1
     */
    String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain ObjectFactory object factory} <code>createFoo(&hellip;)</code> methods.
     * This is used for setting the value to be returned by {@link #getScope()}.
     *
     * @see #getScope()
     */
    String SCOPE_KEY = "scope";

    /**
     * Area or region or timeframe in which this (coordinate) reference system is valid.
     *
     * @return The reference system valid domain, or {@code null} if not available.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003.
     *   Later revisions changed the multiplicity, so the return type should now be a 
     *   collection. The singleton has been preserved in GeoAPI for historical reasons, 
     *   and also because the <code>Extent</code> attributes already allow collections.
     *
     * @since 2.1
     */
    @UML(identifier="domainOfValidity", obligation=OPTIONAL, specification=ISO_19111)
    Extent getDomainOfValidity();

    /**
     * Description of domain of usage, or limitations of usage, for which this
     * Reference System object is valid.
     *
     * @return The domain of usage, or {@code null} if none.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003. 
     *   A later revision moved this attribute to subclasses, but GeoAPI keeps this method 
     *   here for historical reasons. The obligation is still optional, as opposed to ISO 19111:2007
     *   which makes this attribute mandatory while mandating the text "<cite>not known</cite>" if
     *   the scope is unknown. In addition, the return value of this method is still a singleton as in
     *   the 2003 version, as opposed to the 2007 version which mandates a collection. The proposed
     *   change is still under review.
     */
    @UML(identifier="SC_CRS.scope", obligation=OPTIONAL, specification=ISO_19111)
    InternationalString getScope();
}
