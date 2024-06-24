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
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.datum.DatumEnsemble;
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
 * The latter services are often underlying the implementation of a {@code RegisterOperations}
 * and can be made available by the {@link #getFactory(Class)} method.
 * More low-level constructors provided by {@link ObjectFactory} services can also be available.
 *
 * <h2>Getting an instance</h2>
 * Implementers are encouraged to declare their implementations in their {@code module-info} file.
 * Then, users can discover an implementation at runtime using {@link java.util.ServiceLoader} like below:
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
     * Extracts <abbr>CRS</abbr> details from the registry.
     * By default, this method delegates to the {@linkplain CRSAuthorityFactory CRS authority factory}.
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
     * By default, this method delegates to the
     * {@linkplain CoordinateOperationAuthorityFactory coordinate operation authority factory}.
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
    default <T extends Factory> Optional<T> getFactory(Class<T> type) {
        if (type.isInterface() && type.getName().startsWith("org.opengis.referencing.")) {
            return Optional.empty();
        }
        throw new IllegalArgumentException("Illegal authority factory type: " + type.getCanonicalName());
    }
}
