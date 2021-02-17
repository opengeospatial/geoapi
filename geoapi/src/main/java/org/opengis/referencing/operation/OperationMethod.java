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

import java.util.Map;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Definition of an algorithm used to perform a coordinate operation. Most operation methods
 * use a number of operation {@linkplain org.opengis.parameter.ParameterDescriptor parameters},
 * although some coordinate conversions use none.
 * Each {@linkplain CoordinateOperation coordinate operation} using the method assigns
 * {@linkplain org.opengis.parameter.ParameterValue values} to these parameters.
 *
 * <div class="note"><b>Example:</b>
 * an operation method named “<cite>Mercator (variant A)</cite>” (EPSG:9804) declares the following parameters:
 * <ul>
 *   <li>“<cite>Latitude of natural origin</cite>” in degrees.</li>
 *   <li>“<cite>Longitude of natural origin</cite>” in degrees.</li>
 *   <li>“<cite>Scale factor at natural origin</cite>” as a dimensionless number.</li>
 *   <li>“<cite>False easting</cite>” in metres.</li>
 *   <li>“<cite>False northing</cite>” in metres.</li>
 * </ul>
 * Implementations can optionally assign {@linkplain org.opengis.parameter.ParameterDescriptor#getDefaultValue()
 * default values} to those parameters.</div>
 *
 * Operation method {@linkplain #getIdentifiers() identifiers} are optional but recommended,
 * since the method {@linkplain #getName() name} is potentially ambiguous.
 * Some recommended EPSG identifiers are reproduced below
 * (see {@linkplain org.opengis.annotation.Specification#ISO_19162 ISO 19162}
 * or the <a href="https://epsg.org/">EPSG repository</a> for a more complete list):
 *
 * <table class="ogc">
 *   <caption>EPSG identifier for some operation method names</caption>
 *   <tr><th>Name</th>                                <th>Alias</th>                       <th>Identifier</th></tr>
 *   <tr><td>Albers Equal Area</td>                   <td>Albers</td>                      <td>9822</td></tr>
 *   <tr><td>Hotine Oblique Mercator (variant A)</td> <td>Rectified skew orthomorphic</td> <td>9812</td></tr>
 *   <tr><td>Hotine Oblique Mercator (variant B)</td> <td>Rectified skew orthomorphic</td> <td>9815</td></tr>
 *   <tr><td>Lambert Azimuthal Equal Area</td>        <td>Lambert Equal Area</td>          <td>9820</td></tr>
 *   <tr><td>Lambert Conic Conformal (1SP)</td>       <td>Lambert Conic Conformal</td>     <td>9801</td></tr>
 *   <tr><td>Lambert Conic Conformal (2SP)</td>       <td>Lambert Conic Conformal</td>     <td>9802</td></tr>
 *   <tr><td>Mercator (variant A)</td>                <td>Mercator</td>                    <td>9804</td></tr>
 *   <tr><td>Mercator (variant B)</td>                <td>Mercator</td>                    <td>9805</td></tr>
 *   <tr><td>Oblique stereographic</td>               <td>Double stereographic</td>        <td>9809</td></tr>
 *   <tr><td>Transverse Mercator</td>                 <td>Gauss-Boaga / Gauss-Krüger</td>  <td>9807</td></tr>
 * </table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CoordinateOperation
 * @see MathTransformFactory#getAvailableMethods(Class)
 * @see CoordinateOperationFactory#getOperationMethod(String)
 * @see CoordinateOperationAuthorityFactory#createOperationMethod(String)
 * @see CoordinateOperationFactory#createOperationMethod(Map, Integer, Integer, ParameterDescriptorGroup)
 */
@UML(identifier="CC_OperationMethod", specification=ISO_19111)
public interface OperationMethod extends IdentifiedObject {
    /*
     * NOTE FOR JAVADOC WRITER:
     * The "method" word is ambiguous here, because it can be "Java method" or "coordinate operation method".
     * In this interface, we reserve "method" for coordinate operation methods as much as possible.
     */

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
     * @return the formula used by this method.
     */
    @UML(identifier="formulaReference", obligation=MANDATORY, specification=ISO_19111)
    Formula getFormula();

    /**
     * Number of dimensions in the source CRS of this operation method.
     * Note that some operation methods work with an arbitrary number of
     * dimensions (e.g. <cite>Affine Transform</cite>) and may return {@code null}.
     *
     * @return the dimension of source CRS, or {@code null} if unknown.
     *
     * @see MathTransform#getSourceDimensions()
     *
     * @deprecated This attribute has been removed from ISO 19111:2019.
     */
    @Deprecated
    @UML(identifier="sourceDimensions", obligation=OPTIONAL, specification=ISO_19111, version=2007)
    default Integer getSourceDimensions() {
        return null;
    }

    /**
     * Number of dimensions in the target CRS of this operation method.
     * Note that some operation methods work with an arbitrary number of
     * dimensions (e.g. <cite>Affine Transform</cite>) and may return {@code null}.
     *
     * @return the dimension of target CRS, or {@code null} if unknown.
     *
     * @see MathTransform#getTargetDimensions()
     *
     * @deprecated This attribute has been removed from ISO 19111:2019.
     */
    @Deprecated
    @UML(identifier="targetDimensions", obligation=OPTIONAL, specification=ISO_19111, version=2007)
    default Integer getTargetDimensions() {
        return null;
    }

    /**
     * The set of parameters.
     *
     * @return the parameters, or an empty group if none.
     */
    @UML(identifier="parameter", obligation=MANDATORY, specification=ISO_19111)
    ParameterDescriptorGroup getParameters();
}
