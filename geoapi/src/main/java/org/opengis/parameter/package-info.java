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

/**
 * Description and storage of {@linkplain org.opengis.parameter.ParameterValue parameter values}.
 * The first two paragraphs below are adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19111 OpenGIS&reg; Spatial Referencing by
 * Coordinates (Topic 2)} specification.
 *
 * <P ALIGN="justify">Most {@linkplain org.opengis.parameter.ParameterValue parameter values} are
 * numeric, but for some operation methods, notably those implementing a grid interpolation
 * algorithm, the parameter value could be a file name and location (this may be a URI). An
 * example is the coordinate transformation from NAD&nbsp;27 to NAD&nbsp;83 in the USA; depending
 * on the locations of the points to be transformed, one of a series of grid files should be used.</P>
 *
 * <P ALIGN="justify">Some operation methods may require a large number of coordinate operation
 * parameters. Also, some operation methods require that groups of parameters be repeatable as
 * a group. In such cases, it is helpful to group related parameters in
 * {@linkplain org.opengis.parameter.ParameterDescriptorGroup parameter groups}. Two or more parameter
 * groups are then associated with a particular operation method, and each parameter group consists
 * of a set of {@linkplain org.opengis.parameter.ParameterDescriptor operation parameters}, or other,
 * nested parameter groups. This way of modeling is not mandatory; all coordinate operation
 * parameters may be assigned directly to the coordinate operation method.</P>
 *
 * @departure harmonization
 *   Moved the <code>GeneralParameterDescriptor</code>, <code>ParameterDescriptor</code>,
 *   <code>ParameterDescriptorGroup</code>, <code>GeneralParameterValue</code>, <code>ParameterValue</code>,
 *   <code>ParameterValueGroup</code>, <code>InvalidParameterNameException</code>,
 *   <code>InvalidParameterTypeException</code> and <code>InvalidParameterValueException</code>
 *   interfaces from <code>org.opengis.referencing.operation</code> to <code>org.opengis.parameter</code>.
 *   With this move, GeoAPI has extended the use of these parameter classes to a more general use rather
 *   than only for referencing operation types.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.0
 * @since   1.0
 */
package org.opengis.parameter;
