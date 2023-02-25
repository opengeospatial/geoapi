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

import org.opengis.referencing.IdentifiedObject;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Definition of an algorithm used to perform a coordinate operation. Most operation
 * methods use a number of operation parameters, although some coordinate conversions
 * use none. Each coordinate operation using the method assigns values to these parameters.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see CoordinateOperation
 *
 * @navassoc 1 - - Formula
 * @navassoc 1 - - ParameterDescriptorGroup
 */
@UML(identifier="CC_OperationMethod", specification=ISO_19111)
public interface OperationMethod extends IdentifiedObject {
    /**
     * Key for the <code>{@value}</code> property.
     * This is used for setting the value to be returned by {@link #getFormula()}.
     *
     * @see #getFormula()
     */
    String FORMULA_KEY = "formula";

    /**
     * Formula(s) or procedure used by this operation method. This may be a reference to a
     * publication. Note that the operation method may not be analytic, in which case this
     * attribute references or contains the procedure, not an analytic formula.
     *
     * @return The formula used by this method.
     */
    @UML(identifier="formulaReference", obligation=MANDATORY, specification=ISO_19111)
    Formula getFormula();

    /**
     * Number of dimensions in the source CRS of this operation method.
     * Note that some operation methods work with an arbitrary number of
     * dimensions (e.g. <cite>Affine Transform</cite>) and may return {@code null}.
     *
     * @return The dimension of source CRS, or {@code null} if unknown.
     */
    @UML(identifier="sourceDimensions", obligation=OPTIONAL, specification=ISO_19111)
    Integer getSourceDimensions();

    /**
     * Number of dimensions in the target CRS of this operation method.
     * Note that some operation methods work with an arbitrary number of
     * dimensions (e.g. <cite>Affine Transform</cite>) and may return {@code null}.
     *
     * @return The dimension of target CRS, or {@code null} if unknown.
     */
    @UML(identifier="targetDimensions", obligation=OPTIONAL, specification=ISO_19111)
    Integer getTargetDimensions();

    /**
     * The set of parameters.
     *
     * @return The parameters, or an empty group if none.
     */
    @UML(identifier="parameter", obligation=MANDATORY, specification=ISO_19111)
    ParameterDescriptorGroup getParameters();
}
