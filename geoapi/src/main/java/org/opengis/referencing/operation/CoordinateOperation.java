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
package org.opengis.referencing.operation;

import java.util.Optional;
import java.util.Collection;
import java.util.Collections;
import java.time.temporal.Temporal;
import org.opengis.coordinate.CoordinateSet;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.geoapi.internal.Legacy;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A mathematical operation on coordinates that transforms or converts coordinates to another <abbr>CRS</abbr> or epoch.
 * The {@linkplain #getSourceCRS() source <abbr>CRS</abbr>} and {@linkplain #getTargetCRS() target <abbr>CRS</abbr>}
 * properties indicate the <abbr>CRS</abbr> from which coordinates are changed
 * and the <abbr>CRS</abbr> to which coordinates are changed respectively.
 * They are mandatory for all subtypes of coordinate operation except {@link Conversion},
 * but GeoAPI recommends to provide a value even in the latter case.
 *
 * <h2>Inverse operation</h2>
 * Many but not all coordinate operations (from
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <var>A</var> to
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <var>B</var>)
 * also uniquely define the inverse operation (from
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <var>B</var> to
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <var>A</var>).
 * In some cases, the operation method algorithm for the inverse operation is the same as for the forward algorithm,
 * but the signs of some operation parameter values must be reversed.
 * In other cases, different algorithms are required for the forward and inverse operations,
 * but the same operation parameter values are used.
 * If (some) entirely different parameter values are needed, a different coordinate operation shall be defined.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CoordinateOperationAuthorityFactory#createCoordinateOperation(String)
 * @see CoordinateOperationAuthorityFactory#createFromCoordinateReferenceSystemCodes(String, String)
 * @see CoordinateOperationFactory#createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem)
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="CoordinateOperation", specification=ISO_19111)
public interface CoordinateOperation extends IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property.
     * This is used for setting the value to be returned by {@link #getOperationVersion()}.
     *
     * @see #getOperationVersion()
     */
    String OPERATION_VERSION_KEY = "operationVersion";

    /**
     * Key for the <code>{@value}</code> property.
     * This is used for setting the value to be returned by {@link #getCoordinateOperationAccuracy()}.
     *
     * @see #getCoordinateOperationAccuracy()
     */
    String COORDINATE_OPERATION_ACCURACY_KEY = "coordinateOperationAccuracy";

    /**
     * Key for the <code>{@value}</code> property.
     * This property is kept for compatibility with ISO 19111:2007.
     * However as of ISO 19111:2019, {@link #DOMAINS_KEY} is preferred.
     *
     * @see org.opengis.referencing.ObjectDomain#getDomainOfValidity()
     */
    String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";

    /**
     * Key for the <code>{@value}</code> property.
     * This property is kept for compatibility with ISO 19111:2007.
     * However as of ISO 19111:2019, {@link #DOMAINS_KEY} is preferred.
     *
     * @see org.opengis.referencing.ObjectDomain#getScope()
     */
    String SCOPE_KEY = "scope";

    /**
     * Returns the <abbr>CRS</abbr> from which coordinates are changed. This property may be {@code null}
     * for {@link CoordinateOperationFactory#createDefiningConversion defining conversions}, but otherwise
     * <em>should</em> be specified for other conversions and <em>shall</em> be specified for transformations.
     *
     * <h4>Obligation</h4>
     * ISO 19111 defines this property as optional for {@link Conversion} because the source <abbr>CRS</abbr>
     * can be specified by the {@link org.opengis.referencing.crs.DerivedCRS#getBaseCRS()} property instead.
     * However, GeoAPI recommends to provide a value even in the latter case.
     *
     * @return the <abbr>CRS</abbr> from which coordinates are changed, or {@code null} if not available.
     *
     * @see Conversion#getSourceCRS()
     * @see Transformation#getSourceCRS()
     */
    @UML(identifier="sourceCRS", obligation=CONDITIONAL, specification=ISO_19111)
    CoordinateReferenceSystem getSourceCRS();

    /**
     * Returns the <abbr>CRS</abbr> to which coordinates are changed. This property may be {@code null}
     * for {@link CoordinateOperationFactory#createDefiningConversion defining conversions}, but otherwise
     * <em>should</em> be specified for other conversions and <em>shall</em> be specified for transformations.
     *
     * <h4>Obligation</h4>
     * ISO 19111 defines this property as optional for {@link Conversion} because the target <abbr>CRS</abbr>
     * can be specified by the {@link org.opengis.referencing.crs.DerivedCRS} instance instead.
     * However, GeoAPI recommends to provide a value even in the latter case.
     *
     * @return the <abbr>CRS</abbr> to which coordinates are changed, or {@code null} if not available.
     *
     * @see Conversion#getTargetCRS()
     * @see Transformation#getTargetCRS()
     */
    @UML(identifier="targetCRS", obligation=CONDITIONAL, specification=ISO_19111)
    CoordinateReferenceSystem getTargetCRS();

    /**
     * Returns the <abbr>CRS</abbr> to be used for interpolations in a grid.
     * Some single coordinate operations employ methods which include interpolation within a grid to derive
     * the values of operation parameters. The <abbr>CRS</abbr> to be used for the interpolation
     * may be different from either the source <abbr>CRS</abbr> or the target <abbr>CRS</abbr>.
     *
     * <h4>Example</h4>
     * Vertical offsets between two vertical <abbr>CRS</abbr>s interpolated from a grid.
     * The source and target <abbr>CRS</abbr>s will both be vertical <abbr>CRS</abbr>s,
     * the interpolation <abbr>CRS</abbr> is a geographic <abbr>CRS</abbr> to which the grid is referenced.
     *
     * @return the <abbr>CRS</abbr> to be used for interpolations in a grid.
     *
     * @since 3.1
     */
    @UML(identifier="interpolationCRS", obligation=OPTIONAL, specification=ISO_19111)
    default Optional<CoordinateReferenceSystem> getInterpolationCRS() {
        return Optional.empty();
    }

    /**
     * Returns the date at which source coordinate tuples are valid.
     * This is mandatory if the <abbr>CRS</abbr> is
     * {@linkplain org.opengis.referencing.datum.DynamicReferenceFrame dynamic}.
     *
     * @return epoch at which source coordinate tuples are valid.
     *
     * @since 3.1
     */
    @UML(identifier="sourceCoordinateEpoch", obligation=CONDITIONAL, specification=ISO_19111)
    default Optional<Temporal> getSourceEpoch() {
        return Optional.empty();
    }

    /**
     * Returns the date at which target coordinate tuples are valid.
     * This is mandatory if the <abbr>CRS</abbr> is
     * {@linkplain org.opengis.referencing.datum.DynamicReferenceFrame dynamic}.
     *
     * @return epoch at which target coordinate tuples are valid.
     *
     * @since 3.1
     */
    @UML(identifier="targetCoordinateEpoch", obligation=CONDITIONAL, specification=ISO_19111)
    default Optional<Temporal> getTargetEpoch() {
        return Optional.empty();
    }

    /**
     * Returns the version of this coordinate transformation or point motion operation.
     * The version is an identification of the instantiation due to the stochastic nature of the parameters.
     * It is mandatory when describing a transformation or point motion operation,
     * and should not be supplied for a conversion.
     *
     * @return version of the coordinate transformation or point motion, or {@code null} in none.
     */
    @UML(identifier="operationVersion", obligation=CONDITIONAL, specification=ISO_19111)
    String getOperationVersion();

    /**
     * Returns estimate(s) of the impact of this operation on point accuracy.
     * It gives position error estimates for target coordinates of this coordinate operation,
     * assuming no errors in source coordinates.
     *
     * @return the position error estimates, or an empty collection if not available.
     */
    @UML(identifier="coordinateOperationAccuracy", obligation=OPTIONAL, specification=ISO_19111)
    default Collection<PositionalAccuracy> getCoordinateOperationAccuracy() {
        return Collections.emptyList();
    }

    /**
     * Returns the area or region or timeframe in which this coordinate operation is valid.
     *
     * @return the coordinate operation valid domain, or {@code null} if not available.
     *
     * @see CoordinateReferenceSystem#getDomainOfValidity()
     *
     * @deprecated Replaced by {@link #getDomains()} as of ISO 19111:2019.
     */
    @Deprecated(since = "3.1")
    @UML(identifier="domainOfValidity", obligation=OPTIONAL, specification=ISO_19111, version=2007)
    default Extent getDomainOfValidity() {
        return Legacy.getDomainOfValidity(getDomains());
    }

    /**
     * Returns a description of domain of usage, or limitations of usage, for which this operation is valid.
     *
     * @return a description of domain of usage, or {@code null} if none.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003.
     *   The revision published in 2007 replaced the singleton by a collection and changed the
     *   obligation from "optional" to "mandatory", requiring a return value of
     *   <q>not known</q> if the scope is unknown. This change is still under review.
     *
     * @deprecated Replaced by {@link #getDomains()} as of ISO 19111:2019.
     */
    @Deprecated(since = "3.1")
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19111, version=2007)
    default InternationalString getScope() {
        return Legacy.getScope(getDomains());
    }

    /**
     * Returns the mathematical operation which performs the actual work of changing coordinate values.
     * The math transform will transform positions in the
     * {@linkplain #getSourceCRS() source coordinate reference system} into positions in the
     * {@linkplain #getTargetCRS() target coordinate reference system}.
     * It may be {@code null} in the case of
     * {@linkplain CoordinateOperationFactory#createDefiningConversion defining conversions}.
     *
     * @return the transform from source to target <abbr>CRS</abbr>, or {@code null} if not applicable.
     */
    @UML(identifier="CT_CoordinateTransformation.getMathTransform", specification=OGC_01009)
    MathTransform getMathTransform();

    /**
     * Changes coordinates from being referenced to the source <abbr>CRS</abbr>
     * and/or epoch to being referenced to the target <abbr>CRS</abbr> and/or epoch.
     * The <abbr>CRS</abbr> and epoch (if any) of the given data <em>should</em> (see below) be equivalent to the
     * {@linkplain #getSourceCRS() source CRS} and {@linkplain #getSourceEpoch() source epoch} of this operation.
     * The <abbr>CRS</abbr> and epoch (if any) of the returned data <em>shall</em> be equivalent to the
     * {@linkplain #getTargetCRS() target CRS} and {@linkplain #getTargetEpoch() target epoch} of this operation.
     * The order of coordinate tuples <em>shall</em> be preserved.
     *
     * <p>This method operates on coordinate tuples and does not deal with interpolation of geometry types.
     * When a coordinate set is subjected to a coordinate operation, its geometry might or might not be preserved.</p>
     *
     * <h4>Implementation flexibility</h4>
     * If the <abbr>CRS</abbr> and/or epoch of the given data are not equivalent to the source <abbr>CRS</abbr>
     * and/or epoch of this coordinate operation, implementations can either throw an exception or automatically
     * prepend an additional operation step.
     *
     * <p>Implementations are free to compute the coordinate changes immediately or to delay the computation.
     * For example, the coordinate changes may be computed on-the-fly during {@link CoordinateSet#stream()} consumption.
     * In the latter case, invalid coordinates may not cause a {@link TransformException} to be thrown when this method
     * is invoked, but instead cause an unchecked exception to be thrown later,
     * typically during the stream terminal operation.</p>
     *
     * @param  data  the coordinates to change.
     * @return the result of changing coordinates.
     * @throws TransformException if some coordinates cannot be changed. Note that an absence of exception during
     *         this method call is not a guarantee that the coordinate changes succeeded, because implementations
     *         may have deferred the actual computation.
     *
     * @since 3.1
     */
    @UML(identifier="transform", specification=ISO_19111)
    CoordinateSet transform(CoordinateSet data) throws TransformException;
}
