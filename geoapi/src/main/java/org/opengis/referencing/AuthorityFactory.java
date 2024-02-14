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
package org.opengis.referencing;

import java.util.Set;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * Base interface for all authority factories. An <dfn>authority</dfn> is an organization
 * that maintains definitions of authority codes. An <dfn>authority code</dfn> is a compact
 * string defined by an authority to reference a particular spatial reference object.
 *
 * <p>For example, the <a href="http://www.epsg.org">European Petroleum Survey Group (EPSG)</a> maintains
 * a database of coordinate systems, and other spatial referencing objects, where each object has a code
 * number ID. For example, the EPSG code for a WGS84 Lat/Lon coordinate system is “4326”.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
@UML(identifier="CS_CoordinateSystemAuthorityFactory", specification=OGC_01009)
public interface AuthorityFactory extends Factory {
    /**
     * Returns the organization or party responsible for definition and maintenance of the database.
     *
     * @return the organization responsible for definition of the database.
     */
    @UML(identifier="getAuthority", specification=OGC_01009)
    Citation getAuthority();

    /**
     * Returns the set of authority codes for objects of the given type.
     * The {@code type} argument specifies the base type of identified objects.
     *
     * <p><b>Example:</b> if this factory is an instance of {@link org.opengis.referencing.crs.CRSAuthorityFactory},
     * then:</p>
     *
     * <ul>
     *   <li><p><code>{@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem}.class</code><br>
     *       asks for all authority codes accepted by one of
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createGeographicCRS createGeographicCRS},
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS createProjectedCRS},
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createVerticalCRS createVerticalCRS},
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createTemporalCRS createTemporalCRS}
     *       and any other method returning a sub-type of {@code CoordinateReferenceSystem}.</p></li>
     *   <li><p><code>{@linkplain org.opengis.referencing.crs.ProjectedCRS}.class</code><br>
     *       asks only for authority codes accepted by
     *       {@link org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS createProjectedCRS}.</p></li>
     * </ul>
     *
     * @param  type  the spatial reference objects type.
     * @return the set of authority codes for spatial reference objects of the given type.
     *         If this factory does not contain any object of the given type, then this method
     *         returns an {@linkplain java.util.Collections#emptySet() empty set}.
     * @throws FactoryException if access to the underlying database failed.
     *
     * @departure extension
     *   This method is not part of the OGC specification but has been added as a way to publish
     *   the capabilities of a factory.
     */
    Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException;

    /**
     * Returns a description of the object corresponding to a code.
     * The description may be used in graphical user interfaces.
     *
     * @param  code  value allocated by authority.
     * @return a description of the object, or {@code null} if the object
     *         corresponding to the specified {@code code} has no description.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the query failed for some other reason.
     *
     * @deprecated This method is ambiguous because the EPSG geodetic registry may allocate
     *             the same code to different kinds of object.
     *
     * @todo Provide an alternative, maybe with a {@code Class} argument.
     */
    @Deprecated(since = "3.1", forRemoval = true)
    @UML(identifier="descriptionText", specification=OGC_01009)
    default InternationalString getDescriptionText(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, InternationalString.class, "description");
    }

    /**
     * Returns an arbitrary object from a code. The returned object will typically be an
     * instance of {@link org.opengis.referencing.datum.Datum}, {@link org.opengis.referencing.cs.CoordinateSystem},
     * {@link org.opengis.referencing.ReferenceSystem} or {@link org.opengis.referencing.operation.CoordinateOperation}.
     *
     * <p>If the object type is known at compile time, then it is recommended to invoke the
     * most precise method instead of this one. For example, it is usually better to invoke
     * <code>{@linkplain org.opengis.referencing.crs.CRSAuthorityFactory#createCoordinateReferenceSystem
     * createCoordinateReferenceSystem}(code)</code> instead of {@code createObject(code)}
     * if the requested object is known to be a {@code CoordinateReferenceSystem} instance.</p>
     *
     * @param  code  value allocated by authority.
     * @return the object for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     *
     * @departure generalization
     *   This method is not part of the OGC specification. It has been added to leverage the
     *   capability of factories that can automatically determine the type of the requested
     *   object at runtime.
     *
     * @see org.opengis.referencing.datum.DatumAuthorityFactory#createDatum(String)
     * @see org.opengis.referencing.crs.CRSAuthorityFactory#createCoordinateReferenceSystem(String)
     *
     * @deprecated This method is ambiguous because the EPSG geodetic registry may allocate the same code
     *             to different kinds of object. A more specialized method such as {@code createDatum(…)},
     *             {@code createCoordinateSystem(…)} or {@code createCoordinateReferenceSystem(…)} should
     *             be invoked instead.
     */
    @Deprecated(since = "3.1", forRemoval = true)
    default IdentifiedObject createObject(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, IdentifiedObject.class);
    }
}
