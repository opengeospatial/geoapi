/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.util.InternationalString;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.opengis.referencing.datum.DatumEnsemble;
import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.metadata.citation.Citation;
import org.opengis.geoapi.internal.Produces;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Services supported by the referencing packages.
 * The services include finding a <abbr>CRS</abbr> from an authority code,
 * or finding a set of coordinate operations between two <abbr>CRS</abbr>s.
 * Those operations generally require the use of a geodetic registry.
 *
 * <h2>Relationship with factory interfaces</h2>
 * This interface is often the only one needed by users for extracting
 * <abbr>CRS</abbr> and coordinate operation instances from authority codes.
 * For extracting more specific types of <abbr>CRS</abbr>s,
 * or for extracting <abbr>CRS</abbr> components such as coordinate systems or datum,
 * various {@link AuthorityFactory} services may be used.
 * The latter services are often the underlying implementation of a {@code RegisterOperations}
 * and can be made available by the {@link #getFactory(Class)} method.
 * More low-level constructors provided by {@link ObjectFactory} services can also be available.
 *
 * <h2>Notes for implementers</h2>
 * Implementations shall override all abstract methods, and <em>should</em> override the default implementation of
 * {@link #getFactory(Class)} with {@link CRSAuthorityFactory} and/or {@link CoordinateOperationAuthorityFactory}
 * support. Alternatively, the above-cited authority factories do not need to be supported if, instead, at least
 * the {@link #findCoordinateReferenceSystem(String)} and/or {@link #findCoordinateOperation(String)} methods
 * are overridden.
 *
 * <p>Implementers are encouraged to declare their implementations in their {@code module-info} file.
 * Then, users can discover an implementation at runtime using {@link java.util.ServiceLoader} like below:</p>
 *
 * {@snippet lang="java" :
 * RegisterOperations services = ServiceLoader.load(RegisterOperations.class).findFirst().orElseThrow();
 * }
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="RegisterOperations", specification=ISO_19111)
public interface RegisterOperations extends AuthorityFactory {
    /**
     * Returns the vendor responsible for creating this implementation.
     *
     * <h4>Default implementation</h4>
     * The default implementation {@linkplain #getFactory(Class) delegates} to {@link CRSAuthorityFactory}
     * if present, or to {@link CoordinateOperationAuthorityFactory} otherwise, or returns {@code null} if
     * none of these factories is present.
     *
     * @return the vendor for this factory implementation.
     */
    @Override
    default Citation getVendor() {
        final AuthorityFactory factory = factory();
        return (factory != null) ? factory.getVendor() : null;
    }

    /**
     * Returns the organization or party responsible for definition and maintenance of the register.
     *
     * <h4>Default implementation</h4>
     * The default implementation {@linkplain #getFactory(Class) delegates} to {@link CRSAuthorityFactory}
     * if present, or to {@link CoordinateOperationAuthorityFactory} otherwise, or returns {@code null} if
     * none of these factories is present.
     *
     * @return the organization responsible for definition of the register.
     */
    @Override
    default Citation getAuthority() throws FactoryException {
        final AuthorityFactory factory = factory();
        return (factory != null) ? factory.getAuthority() : null;
    }

    /**
     * The factory to use for the default implementation of {@link #getVendor()} and {@link #getAuthority()}.
     *
     * @return the <abbr>CRS</abbr> factory (preferred), or operation factory (fallback), or {@code null}.
     */
    private AuthorityFactory factory() {
        final Optional<AuthorityFactory> factory = getFactory(CRSAuthorityFactory.class);
        return factory.orElseGet(() -> getFactory(CoordinateOperationAuthorityFactory.class).orElse(null));
    }

    /**
     * Returns the set of authority codes for objects of the given type.
     * The {@code type} argument specifies the base type of identified objects.
     * For example, {@code CoordinateReferenceSystem.class} is for requesting the <abbr>CRS</abbr> codes.
     * See the {@linkplain AuthorityFactory#getAuthorityCodes parent interface} for more details.
     *
     * <h4>Default implementation</h4>
     * The default implementation {@linkplain #getFactory(Class) delegates to the factory}
     * for objects of the given type. For example, it delegates to {@link CSAuthorityFactory}
     * if {@code type} is assignable to {@link org.opengis.referencing.cs.CoordinateSystem}.
     * If no factory is available for the given type, then an empty set is returned.
     *
     * @param  type  the type of referencing object for which to get authority codes.
     * @return the set of authority codes for referencing objects of the given type.
     * @throws FactoryException if access to the underlying database failed.
     */
    @Override
    default Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
        final Optional<? extends AuthorityFactory> factory = getFactory(toFactoryType(type));
        return factory.isPresent() ? factory.get().getAuthorityCodes(type) : Set.of();
    }

    /**
     * Returns a textual description of the object corresponding to a code.
     * The returned optional may be empty if the given type is not supported,
     * or if the object corresponding to the specified code has no description.
     *
     * <h4>Default implementation</h4>
     * The default implementation {@linkplain #getFactory(Class) delegates to the factory}
     * for objects of the given type. For example, it delegates to {@link CSAuthorityFactory}
     * if {@code type} is assignable to {@link org.opengis.referencing.cs.CoordinateSystem}.
     * If no factory is available for the given type, then an empty optional is returned.
     *
     * @param  type  the type of object for which to get a description.
     * @param  code  value allocated by the authority for an object of the given type.
     * @return a description of the object, or empty if the object has no description.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the query failed for some other reason.
     */
    @Override
    default Optional<InternationalString> getDescriptionText(Class<? extends IdentifiedObject> type, String code)
            throws FactoryException
    {
        final Optional<? extends AuthorityFactory> factory = getFactory(toFactoryType(type));
        return factory.isPresent() ? factory.get().getDescriptionText(type, code) : Optional.empty();
    }

    /**
     * Returns the type of authority factory that may be able to create objects of the given type.
     *
     * @param  object  the type of object for which to get a factory.
     * @return the type of factory that may be able to create instances of the given object.
     * @throws IllegalArgumentException if the given type is unrecognized or ambiguous.
     */
    private static Class<? extends AuthorityFactory> toFactoryType(final Class<? extends IdentifiedObject> object) {
        Class<? extends AuthorityFactory> found = null;
loop:   for (int i=0; ; i++) {
            final Class<? extends AuthorityFactory> factory;
            switch (i) {
                case 0:  factory = CRSAuthorityFactory.class; break;
                case 1:  factory = CoordinateOperationAuthorityFactory.class; break;
                case 2:  factory = CSAuthorityFactory.class; break;
                case 3:  factory = DatumAuthorityFactory.class; break;
                default: if (found != null) return found; else break loop;
            }
            for (final Class<?> produces : factory.getAnnotation(Produces.class).value()) {
                if (produces.isAssignableFrom(object)) {
                    if (found != null) break loop;
                    found = factory;
                }
            }
        }
        throw new IllegalArgumentException((found != null ? "Ambiguous" : "Unrecognized")
                + " object type: " + object.getCanonicalName());
    }

    /**
     * Extracts <abbr>CRS</abbr> details from the registry.
     *
     * <h4>Default implementation</h4>
     * The default implementation {@linkplain #getFactory(Class) delegates} to {@link CRSAuthorityFactory},
     * or throws {@link UnimplementedServiceException} if there is no such factory.
     *
     * @param  code  <abbr>CRS</abbr> identifier allocated by the authority.
     * @return the <abbr>CRS</abbr> for the given authority code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the search failed for some other reason.
     *
     * @see CRSAuthorityFactory#createCoordinateReferenceSystem(String)
     */
    @UML(identifier="findCoordinateReferenceSystem", specification=ISO_19111)
    default CoordinateReferenceSystem findCoordinateReferenceSystem(final String code) throws FactoryException {
        return getFactory(CRSAuthorityFactory.class)
                .orElseThrow(() -> new UnimplementedServiceException(this, CoordinateReferenceSystem.class))
                .createCoordinateReferenceSystem(code);
    }

    /**
     * Extracts coordinate operation details from the registry.
     *
     * <h4>Default implementation</h4>
     * The default implementation {@linkplain #getFactory(Class) delegates} to {@link CoordinateOperationAuthorityFactory},
     * or throws {@link UnimplementedServiceException} if there is no such factory.
     *
     * @param  code  operation identifier allocated by the authority.
     * @return the operation for the given authority code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the search failed for some other reason.
     *
     * @see CoordinateOperationAuthorityFactory#createCoordinateOperation(String)
     */
    @UML(identifier="findCoordinateOperation", specification=ISO_19111)
    default CoordinateOperation findCoordinateOperation(String code) throws FactoryException {
        return getFactory(CoordinateOperationAuthorityFactory.class)
                .orElseThrow(() -> new UnimplementedServiceException(this, CoordinateOperation.class))
                .createCoordinateOperation(code);
    }

    /**
     * Finds or infers any coordinate operations for which the given <abbr>CRS</abbr>s are the source and target,
     * in that order.
     *
     * <h4>Implementation considerations</h4>
     * Coordinate transformation services should be able to automatically derive coordinate operations
     * that are not stored explicitly in any permanent data store, in other words determine their own
     * concatenated or inverse operations. The reason is that it is practically impossible to store
     * all possible pairs of coordinate reference systems in explicitly defined coordinate operations.
     *
     * <p>Coordinate transformation services should also be able to derive or infer the inverse of any
     * coordinate operation (from <var>B</var> to <var>A</var>) from its complementary forward operation
     * (<var>A</var> to <var>B</var>). Geodetic datasets may record
     * only one of the two operations that may exist between any two coordinate reference systems.
     * The inverse operation is then inferred by the application software logic from the stored operation.
     * In some cases, the algorithm for the inverse operation is the same as the forward algorithm,
     * and only the signs of the parameter values reversed.
     * In some other cases, the forward and inverse methods imply two algorithms, but the parameters values are the same.
     * The latter situation generally applies to map projections.</p>
     *
     * <p>The implementation should apply meaningful constraints and validations to this process.
     * For example, it may be mathematically possible to derive a concatenated coordinate operation
     * that will transform North American Datum 1983 coordinates to Australian National Datum,
     * but in a practical sense that operation would be meaningless.
     * The operation can be determined as invalid with a comparison of the two areas of validity
     * and the conclusion that there is no overlap between these.</p>
     *
     * @param  source  the source <abbr>CRS</abbr>.
     * @param  target  the target <abbr>CRS</abbr>.
     * @return coordinate operations found or inferred between the given pair <abbr>CRS</abbr>s. May be an empty set.
     * @throws FactoryException if an error occurred while searching for coordinate operations.
     */
    @UML(identifier="findCoordinateOperations", specification=ISO_19111)
    Set<CoordinateOperation> findCoordinateOperations(CoordinateReferenceSystem source, CoordinateReferenceSystem target)
            throws FactoryException;

    /**
     * Determines whether two <abbr>CRS</abbr>s are members of one ensemble.
     * If this method returns {@code true}, then for low accuracy purposes coordinate sets referenced
     * to these <abbr>CRS</abbr>s may be merged without coordinate transformation.
     * The attribute {@link DatumEnsemble#getEnsembleAccuracy()} gives some indication
     * of the inaccuracy introduced through such merger.
     *
     * @param  source  the source <abbr>CRS</abbr>.
     * @param  target  the target <abbr>CRS</abbr>.
     * @return whether the two <abbr>CRS</abbr>s are members of one ensemble.
     * @throws FactoryException if an error occurred while searching for ensemble information in the registry.
     */
    @UML(identifier="areMembersOfSameEnsemble", specification=ISO_19111)
    boolean areMembersOfSameEnsemble(CoordinateReferenceSystem source, CoordinateReferenceSystem target)
            throws FactoryException;

    /**
     * Returns a factory used for building components of <abbr>CRS</abbr> or coordinate operations.
     * The factories returned by this method provide accesses to the low-level services used by this
     * {@code RegisterOperations} instance for implementing its high-level services.
     * The desired factory is identified by the {@code type} argument,
     * which can be one of the following values:
     *
     * <ul>
     *   <li>{@link AuthorityFactory} for extracting low-level components from authority codes <em>usually</em>
     *       defined by the same {@linkplain #getAuthority() authority} than this {@code RegisterOperations}:
     *     <ul>
     *       <li>{@link org.opengis.referencing.cs.CSAuthorityFactory} for coordinate systems and axes.</li>
     *       <li>{@link org.opengis.referencing.datum.DatumAuthorityFactory} for datum and reference frames.</li>
     *       <li>{@link org.opengis.referencing.crs.CRSAuthorityFactory} for coordinate reference systems.</li>
     *       <li>{@link org.opengis.referencing.operation.CoordinateOperationAuthorityFactory} for coordinate operations.</li>
     *     </ul>
     *   </li><li>{@link ObjectFactory} for instantiating the components defined by above authority factories:
     *     <ul>
     *       <li>{@link org.opengis.referencing.cs.CSFactory} for coordinate systems and axes.</li>
     *       <li>{@link org.opengis.referencing.datum.DatumFactory} for datum and reference frames.</li>
     *       <li>{@link org.opengis.referencing.crs.CRSFactory} for coordinate reference systems.</li>
     *       <li>{@link org.opengis.referencing.operation.CoordinateOperationFactory} for coordinate operations.</li>
     *     </ul>
     *   </li><li>Others factories potentially used or made available by this {@code RegisterOperations}:
     *     <ul>
     *       <li>{@link org.opengis.referencing.operation.MathTransformFactory} for math transforms.</li>
     *       <li>Any other types documented as an extension by the {@code RegisterOperations} implementation.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param  <T>   compile-time value of the {@code type} argument.
     * @param  type  the desired type of factory.
     * @return factory of the specified type.
     * @throws NullPointerException if the specified type is null.
     * @throws IllegalArgumentException if the specified type is not one of the above-cited values.
     *
     * @departure integration
     *   Added for making possible to use the {@code RegisterOperations} as the single entry point
     *   where to fetch all other services.
     */
    default <T extends Factory> Optional<T> getFactory(Class<? extends T> type) {
        if (type.isInterface() && type != AuthorityFactory.class && type != RegisterOperations.class
                && type.getName().startsWith("org.opengis.referencing."))
        {
            return Optional.empty();
        }
        throw new IllegalArgumentException("Illegal factory type: " + type.getCanonicalName());
    }
}
