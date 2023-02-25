/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */

/**
 * Implementation of some interfaces from the {@link org.opengis.parameter} package.
 * In order to provide a simpler model, the classes in this package implement both
 * {@link org.opengis.parameter.GeneralParameterValue} and
 * {@link org.opengis.parameter.GeneralParameterDescriptor}.
 * This means that each parameter value is also its own descriptor.
 *
 * <p><b><u>Example: Creating parameters for the Mercator projection</u></b></p>
 *
 * {@snippet lang="java" :
 * public ParameterValueGroup createMercatorParameters() {
 *     final Citation authority = new SimpleCitation("EPSG");
 *     return new SimpleParameterGroup(authority, "Mercator (variant A)",
 *             new SimpleParameter(authority, "Latitude of natural origin",     SimpleParameter.Type.LATITUDE),
 *             new SimpleParameter(authority, "Longitude of natural origin",    SimpleParameter.Type.LONGITUDE),
 *             new SimpleParameter(authority, "Scale factor at natural origin", SimpleParameter.Type.SCALE),
 *             new SimpleParameter(authority, "False easting",                  SimpleParameter.Type.LINEAR),
 *             new SimpleParameter(authority, "False northing",                 SimpleParameter.Type.LINEAR));
 * }}
 *
 * <p><b><u>Example: Defining values to the Mercator parameters</u></b></p>
 *
 * {@snippet lang="java" :
 * public ParameterValueGroup createMercatorParameters() {
 *     ParameterValueGroup group = createMercatorParameters();
 *     group.parameter("Latitude of natural origin").setValue(30.0, NonSI.DEGREE_ANGLE);
 *     group.parameter("False easting").setValue(5000.0, SI.METRE);
 * }}
 *
 * <p>Every classes in this package are hereby placed into the Public Domain.
 * This means anyone is free to do whatever they wish with those files.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.example.parameter;
