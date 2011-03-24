/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
