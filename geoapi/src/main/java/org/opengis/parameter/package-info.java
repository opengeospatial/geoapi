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

/**
 * Description and storage of {@linkplain org.opengis.parameter.ParameterValue parameter values}.
 *
 * <p>Parameters are described extensively in the {@linkplain org.opengis.annotation.Specification#ISO_19111 OpenGIS®
 * Spatial Referencing by Coordinates (Topic 2)} specification, but appear also in other specifications like Metadata
 * and Web Processing Services. This GeoAPI package is derived mostly from the referencing model (ISO 19111),
 * augmented with some properties from the metadata model (ISO 19115)
 * in order to provide a unified framework for both standards.</p>
 *
 * <p>Interfaces in this package can be grouped in two categories:</p>
 * <ul>
 *   <li><b>Parameter descriptors</b> are immutable types that describes the parameters needed by an operation or a
 *     process. Descriptors contain information like parameter name, optionality, repeatability and value type, but
 *     do not contain the actual parameter value.</li>
 *   <li><b>Parameter values</b> are mutable (<var>descriptor</var>, <var>value</var>) tuples, together with convenience
 *     methods for {@linkplain org.opengis.parameter.ParameterValueGroup#parameter(java.lang.String) searching a single
 *     parameter for a given name}, {@linkplain org.opengis.parameter.ParameterValue#doubleValue(javax.measure.Unit)
 *     performing unit conversions} and getting the values as instances of some commonly used types.</li>
 * </ul>
 *
 * <table class="ogc">
 * <caption>Type hierarchy</caption>
 * <tr>
 *   <th>Parameter descriptor</th>
 *   <th class="sep">Parameter value</th>
 * </tr><tr><td class="hierarchy">
 * <pre> {@linkplain org.opengis.referencing.IdentifiedObject}
 *  └─ {@linkplain org.opengis.parameter.GeneralParameterDescriptor}
 *      ├─ {@linkplain org.opengis.parameter.ParameterDescriptor}
 *      └─ {@linkplain org.opengis.parameter.ParameterDescriptorGroup}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@code Object}
 *  └─ {@linkplain org.opengis.parameter.GeneralParameterValue}
 *      ├─ {@linkplain org.opengis.parameter.ParameterValue}
 *      └─ {@linkplain org.opengis.parameter.ParameterValueGroup}</pre>
 * </td></tr></table>
 *
 * Parameter descriptor instantiations are implementation-dependent. Descriptors are usually not instantiated directly,
 * but rather provided as parts of larger objects like {@link org.opengis.referencing.operation.OperationMethod}.
 * Parameter value instances are created by {@link org.opengis.parameter.GeneralParameterDescriptor#createValue()}.
 *
 *
 *
 * <h2>Coordinate Operation parameters</h2>
 * <p>Most parameter values are real numbers. Those values can be obtained by the
 * {@link org.opengis.parameter.ParameterValue#doubleValue() ParameterValue.doubleValue()} method.
 * But for some operation methods, notably those implementing a grid interpolation algorithm, the parameter
 * value could be a file name or URI. Those values can be obtained by the
 * {@link org.opengis.parameter.ParameterValue#getValue() ParameterValue.getValue()} method.
 * Note that such URI may be valid only for some locations.
 * An example is the coordinate transformation from NAD&nbsp;27 to NAD&nbsp;83 in the USA; depending
 * on the locations of the points to be transformed, one of a series of grid files should be used.</p>
 *
 * <p>Some operations may require a large number of parameters.
 * Also, some operations require that groups of parameters be repeatable as a group.
 * In such cases, it is helpful to group related parameters in
 * {@linkplain org.opengis.parameter.ParameterDescriptorGroup parameter descriptor groups}.
 * Each parameter group consists of a set of
 * {@linkplain org.opengis.parameter.ParameterDescriptor parameter descriptors}, or other,
 * nested parameter groups. This way of modeling is not mandatory; all parameters may be
 * assigned directly to the operation method.</p>
 *
 *
 *
 * <h2>Service Metadata parameters</h2>
 * <p>The metadata standard uses a simple parameter class ({@code SV_Parameter}) with only {@code name},
 * {@code direction}, {@code description}, {@code optionality} and {@code repeatibility} properties.
 * However in order to provide a unified parameter API, GeoAPI omits the metadata parameter class
 * and instead defines a mapping from metadata properties to GeoAPI properties.
 * The mapping is documented in {@link org.opengis.metadata.identification.OperationMetadata#getParameters()}
 * and in {@link org.opengis.parameter.GeneralParameterDescriptor} javadoc.</p>
 *
 *
 *
 * <h2>Web Processing Service parameters</h2>
 * <p>The Web Processing Service (WPS) standard defines {@code Input} and {@code Output} structures
 * with some properties similar to the {@link org.opengis.parameter.GeneralParameterDescriptor} ones.
 * In particular, {@code Identifier}, {@code Abstract}, {@code MinOccurs} and {@code MaxOccurs} have
 * direct equivalences to GeoAPI. While GeoAPI does not yet support WPS, we plan to retrofit WPS
 * input/output in existing API if possible.</p>
 *
 *
 * @departure harmonization
 *   ISO 19111 defines parameters in the <cite>Coordinate Operation</cite> package, while
 *   ISO 19115 defines parameters in the <cite>Services</cite> package.
 *   GeoAPI moves those classes in this {@code parameter} independent package
 *   and tries to provide a single model for the two standards when possible.
 *   With this move, GeoAPI has extended the use of these parameter classes to a more general use rather
 *   than only for referencing operation types.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Jody Garnett (Refractions Research)
 * @version 4.0
 * @since   1.0
 */
package org.opengis.parameter;
