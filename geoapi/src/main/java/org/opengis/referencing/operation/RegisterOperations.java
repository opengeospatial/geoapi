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
package org.opengis.referencing.operation;

import java.util.Set;
import org.opengis.util.FactoryException;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.DatumEnsemble;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Services supported by the coordinate operation packages.
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
     *
     * @param  code  <abbr>CRS</abbr> identifier allocated by the authority.
     * @return the <abbr>CRS</abbr> for the given authority code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="findCoordinateReferenceSystem", specification=ISO_19111)
    CoordinateReferenceSystem findCoordinateReferenceSystem(String code) throws FactoryException;

    /**
     * Extracts coordinate operation details from the registry.
     *
     * @param  code  operation identifier allocated by the authority.
     * @return the operation for the given authority code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="findCoordinateOperation", specification=ISO_19111)
    CoordinateOperation findCoordinateOperation(String code) throws FactoryException;

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
     * Determine whether two <abbr>CRS</abbr>s are members of one ensemble.
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
}
