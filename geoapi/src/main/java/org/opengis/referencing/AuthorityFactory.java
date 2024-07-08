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
import java.util.Optional;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base interface for all authority factories.
 * An <dfn>authority</dfn> is an organization that maintains definitions of authority codes.
 * An <dfn>authority code</dfn> is a compact string defined by an authority to reference a particular referencing object.
 *
 * <p>For example, the <a href="https://epsg.org">EPSG geodetic registry</a> is a database of coordinate
 * reference systems, and other spatial referencing objects, where each object has a code number ID.
 * For example, the EPSG code for a <q>WGS84 Lat/Lon</q> <abbr>CRS</abbr> is <q>4326</q>.</p>
 *
 * @author  OGC 01-009 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
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
     * <h4>Example</h4>
     * Assuming that this factory is an instance of {@link org.opengis.referencing.crs.CRSAuthorityFactory},
     * if the {@code type} argument value is set to
     * <code>{@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem}.class</code>,
     * then this method should return all authority codes accepted by methods such as
     * {@link org.opengis.referencing.crs.CRSAuthorityFactory#createGeographicCRS createGeographicCRS(…)},
     * {@link org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS createProjectedCRS(…)},
     * {@link org.opengis.referencing.crs.CRSAuthorityFactory#createVerticalCRS createVerticalCRS(…)},
     * {@link org.opengis.referencing.crs.CRSAuthorityFactory#createTemporalCRS createTemporalCRS(…)}
     * and any other method returning a sub-type of {@code CoordinateReferenceSystem}.
     * By contrast, if the {@code type} argument value is set to
     * <code>{@linkplain org.opengis.referencing.crs.ProjectedCRS}.class</code>,
     * then this method should return only the authority codes accepted by
     * {@link org.opengis.referencing.crs.CRSAuthorityFactory#createProjectedCRS createProjectedCRS(…)}.
     *
     * @param  type  the type of referencing object for which to get authority codes.
     * @return the set of authority codes for referencing objects of the given type.
     * @throws FactoryException if access to the underlying database failed.
     *
     * @departure extension
     *   This method is not part of the OGC specification but has been added as a way to publish
     *   the capabilities of a factory.
     */
    Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException;

    /**
     * Returns a textual description of the object corresponding to a code.
     * The description may be used in graphical user interfaces.
     *
     * <p>The {@code type} argument can be used for resolving ambiguities,
     * for example, if a <abbr>CRS</abbr> and a datum have the same code.
     * If no object is found for the (<var>type</var>, <var>code</var>) tuple, then this
     * method may return an empty optional or throw {@link NoSuchAuthorityCodeException},
     * at implementation choice. If an object is found but have no textual description,
     * then this method shall return an empty optional.</p>
     *
     * @param  type  the type of object for which to get a description.
     * @param  code  value allocated by the authority for an object of the given type.
     * @return a description of the object, or empty if the object has no description.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the query failed for some other reason.
     *
     * @since 3.1
     */
    @UML(identifier="descriptionText", obligation=OPTIONAL, specification=OGC_01009)
    default Optional<InternationalString> getDescriptionText(Class<? extends IdentifiedObject> type, String code)
            throws FactoryException
    {
        return Optional.empty();
    }

    /**
     * Returns a description of the object corresponding to a code.
     *
     * @param  code  value allocated by authority.
     * @return a description of the object, or {@code null} if the object
     *         corresponding to the specified {@code code} has no description.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the query failed for some other reason.
     *
     * @deprecated This method is ambiguous because the EPSG geodetic registry may allocate
     *             the same code to different kinds of object.
     */
    @Deprecated(since="3.1", forRemoval=true)
    default InternationalString getDescriptionText(String code) throws FactoryException {
        return getDescriptionText(IdentifiedObject.class, code).orElse(null);
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
    @Deprecated(since="3.1", forRemoval=true)
    default IdentifiedObject createObject(String code) throws FactoryException {
        throw new UnimplementedServiceException(this, IdentifiedObject.class);
    }
}
