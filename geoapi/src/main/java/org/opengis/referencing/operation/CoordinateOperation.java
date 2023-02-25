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
package org.opengis.referencing.operation;

import java.util.Collection;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A mathematical operation on coordinates that transforms or converts coordinates to
 * another coordinate reference system. Many but not all coordinate operations (from
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <VAR>A</VAR> to
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <VAR>B</VAR>)
 * also uniquely define the inverse operation (from
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <VAR>B</VAR> to
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <VAR>A</VAR>).
 * In some cases, the operation method algorithm for the inverse operation is the same
 * as for the forward algorithm, but the signs of some operation parameter values must
 * be reversed. In other cases, different algorithms are required for the forward and
 * inverse operations, but the same operation parameter values are used. If (some)
 * entirely different parameter values are needed, a different coordinate operation
 * shall be defined.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 2 - - CoordinateReferenceSystem
 * @navassoc - - - PositionalAccuracy
 * @navassoc - - - Extent
 * @navassoc 1 - - MathTransform
 */
@UML(identifier="CC_CoordinateOperation", specification=ISO_19111)
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
     *
     * @since 2.1
     */
    String COORDINATE_OPERATION_ACCURACY_KEY = "coordinateOperationAccuracy";

    /**
     * Key for the <code>{@value}</code> property.
     * This is used for setting the value to be returned by {@link #getDomainOfValidity()}.
     *
     * @see #getDomainOfValidity()
     *
     * @since 2.1
     */
    String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";

    /**
     * Key for the <code>{@value}</code> property.
     * This is used for setting the value to be returned by {@link #getScope()}.
     *
     * @see #getScope()
     */
    String SCOPE_KEY = "scope";

    /**
     * Returns the source CRS. The source CRS is mandatory for {@linkplain Transformation
     * transformations} only. {@linkplain Conversion Conversions} may have a source CRS that
     * is not specified here, but through
     * {@link org.opengis.referencing.crs.GeneralDerivedCRS#getBaseCRS()} instead.
     *
     * @return The source CRS, or {@code null} if not available.
     *
     * @see Conversion#getSourceCRS()
     * @see Transformation#getSourceCRS()
     */
    @UML(identifier="sourceCRS", obligation=CONDITIONAL, specification=ISO_19111)
    CoordinateReferenceSystem getSourceCRS();

    /**
     * Returns the target CRS. The target CRS is mandatory for {@linkplain Transformation
     * transformations} only. {@linkplain Conversion Conversions} may have a target CRS
     * that is not specified here, but through
     * {@link org.opengis.referencing.crs.GeneralDerivedCRS} instead.
     *
     * @return The target CRS, or {@code null} if not available.
     *
     * @see Conversion#getTargetCRS()
     * @see Transformation#getTargetCRS()
     */
    @UML(identifier="targetCRS", obligation=CONDITIONAL, specification=ISO_19111)
    CoordinateReferenceSystem getTargetCRS();

    /**
     * Version of the coordinate transformation (i.e., instantiation due to the stochastic
     * nature of the parameters). Mandatory when describing a transformation, and should not
     * be supplied for a conversion.
     *
     * @return The coordinate operation version, or {@code null} in none.
     */
    @UML(identifier="operationVersion", obligation=CONDITIONAL, specification=ISO_19111)
    String getOperationVersion();

    /**
     * Estimate(s) of the impact of this operation on point accuracy. Gives
     * position error estimates for target coordinates of this coordinate
     * operation, assuming no errors in source coordinates.
     *
     * @return The position error estimates, or an empty collection if not available.
     *
     * @since 2.1
     */
    @UML(identifier="coordinateOperationAccuracy", obligation=OPTIONAL, specification=ISO_19111)
    Collection<PositionalAccuracy> getCoordinateOperationAccuracy();

    /**
     * Area or region or timeframe in which this coordinate operation is valid.
     *
     * @return The coordinate operation valid domain, or {@code null} if not available.
     *
     * @since 2.1
     */
    @UML(identifier="domainOfValidity", obligation=OPTIONAL, specification=ISO_19111)
    Extent getDomainOfValidity();

    /**
     * Description of domain of usage, or limitations of usage, for which this operation is valid.
     *
     * @return A description of domain of usage, or {@code null} if none.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003.
     *   The revision published in 2007 replaced the singleton by a collection and changed the
     *   obligation from "optional" to "mandatory", requiring a return value of 
     *   "<cite>not known</cite>" if the scope is unknown. This change is still under review.
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19111)
    InternationalString getScope();

    /**
     * Gets the math transform. The math transform will transform positions in the
     * {@linkplain #getSourceCRS source coordinate reference system} into positions in the
     * {@linkplain #getTargetCRS target coordinate reference system}. It may be {@code null}
     * in the case of {@linkplain CoordinateOperationFactory#createDefiningConversion
     * defining conversions}.
     *
     * @return The transform from source to target CRS, or {@code null} if not applicable.
     */
    @UML(identifier="CT_CoordinateTransformation.getMathTransform", specification=OGC_01009)
    MathTransform getMathTransform();
}
