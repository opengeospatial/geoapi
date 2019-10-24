/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.referencing.operation;

import java.util.Collection;
import java.util.Collections;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A mathematical operation on coordinates that transforms or converts coordinates to
 * another coordinate reference system.
 *
 * <h2>Inverse operation</h2>
 * Many but not all coordinate operations (from
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <var>A</var> to
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <var>B</var>)
 * also uniquely define the inverse operation (from
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <var>B</var> to
 * {@linkplain CoordinateReferenceSystem coordinate reference system} <var>A</var>).
 * In some cases, the operation method algorithm for the inverse operation is the same
 * as for the forward algorithm, but the signs of some operation parameter values must
 * be reversed. In other cases, different algorithms are required for the forward and
 * inverse operations, but the same operation parameter values are used. If (some)
 * entirely different parameter values are needed, a different coordinate operation
 * shall be defined.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CoordinateOperationAuthorityFactory#createCoordinateOperation(String)
 * @see CoordinateOperationAuthorityFactory#createFromCoordinateReferenceSystemCodes(String, String)
 * @see CoordinateOperationFactory#createOperation(CoordinateReferenceSystem, CoordinateReferenceSystem)
 */
@Classifier(Stereotype.ABSTRACT)
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
     */
    String COORDINATE_OPERATION_ACCURACY_KEY = "coordinateOperationAccuracy";

    /**
     * Key for the <code>{@value}</code> property.
     * This is used for setting the value to be returned by {@link #getDomainOfValidity()}.
     *
     * @see #getDomainOfValidity()
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
     * Returns the source CRS. The source CRS is mandatory for {@linkplain Transformation transformations} only.
     * {@linkplain Conversion Conversions} may have a source CRS that is not specified here, but through
     * {@link org.opengis.referencing.crs.GeneralDerivedCRS#getBaseCRS()} instead.
     *
     * @return the source CRS, or {@code null} if not available.
     *
     * @see Conversion#getSourceCRS()
     * @see Transformation#getSourceCRS()
     */
    @UML(identifier="sourceCRS", obligation=CONDITIONAL, specification=ISO_19111)
    CoordinateReferenceSystem getSourceCRS();

    /**
     * Returns the target CRS. The target CRS is mandatory for {@linkplain Transformation transformations} only.
     * {@linkplain Conversion Conversions} may have a target CRS that is not specified here, but through
     * {@link org.opengis.referencing.crs.GeneralDerivedCRS} instead.
     *
     * @return the target CRS, or {@code null} if not available.
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
     * @return the coordinate operation version, or {@code null} in none.
     */
    @UML(identifier="operationVersion", obligation=CONDITIONAL, specification=ISO_19111)
    String getOperationVersion();

    /**
     * Estimate(s) of the impact of this operation on point accuracy. Gives
     * position error estimates for target coordinates of this coordinate
     * operation, assuming no errors in source coordinates.
     *
     * @return the position error estimates, or an empty collection if not available.
     */
    @UML(identifier="coordinateOperationAccuracy", obligation=OPTIONAL, specification=ISO_19111)
    default Collection<PositionalAccuracy> getCoordinateOperationAccuracy() {
        return Collections.emptyList();
    }

    /**
     * Area or region or timeframe in which this coordinate operation is valid.
     *
     * @return the coordinate operation valid domain, or {@code null} if not available.
     */
    @UML(identifier="domainOfValidity", obligation=OPTIONAL, specification=ISO_19111)
    default Extent getDomainOfValidity() {
        return null;
    }

    /**
     * Description of domain of usage, or limitations of usage, for which this operation is valid.
     *
     * @return a description of domain of usage, or {@code null} if none.
     *
     * @departure historic
     *   This method has been kept conformant with the specification published in 2003.
     *   The revision published in 2007 replaced the singleton by a collection and changed the
     *   obligation from "optional" to "mandatory", requiring a return value of
     *   <cite>"not known"</cite> if the scope is unknown. This change is still under review.
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19111)
    default InternationalString getScope() {
        return null;
    }

    /**
     * Gets the math transform. The math transform will transform positions in the
     * {@linkplain #getSourceCRS() source coordinate reference system} into positions in the
     * {@linkplain #getTargetCRS() target coordinate reference system}.
     * It may be {@code null} in the case of
     * {@linkplain CoordinateOperationFactory#createDefiningConversion defining conversions}.
     *
     * @return the transform from source to target CRS, or {@code null} if not applicable.
     */
    @UML(identifier="CT_CoordinateTransformation.getMathTransform", specification=OGC_01009)
    MathTransform getMathTransform();
}
