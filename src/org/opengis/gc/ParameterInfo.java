/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gc;


/**
 * Provides information for the parameters required for grid coverage processing
 * operations and grid exchange. This information includes such information as
 * the name of the parameter, parameter description, parameter type etc.
 *
 * @UML datatype CV_ParameterInfo
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 */
public interface ParameterInfo {
    /**
     * Parameter name.
     *
     * @UML mandatory name
     */
    String getName();

    /**
     * Parameter description.
     * If no description, the value will be null.
     *
     * @UML mandatory description
     */
    String getDescription();

    /**
     * Parameter type.
     * The enumeration contains standard parameter types for integer, string,
     * floating-point numbers, objects, etc.
     *
     * @UML mandatory type
     */
    ParameterType getType();

    /**
     * Default value for parameter.
     * The type {@link Object} can be any type including a {@link Number} or a
     * {@link String}. For example, a filtering operation could have a default
     * kernel size of 3. If there is no default value, <code>defaultValue</code>
     * will be null.
     *
     * @UML mandatory defaultValue
     */
    Object getDefaultValue();

    /**
     * Minimum parameter value.
     * For example, a filtering operation could have a minimum kernel size of 3.
     *
     * @UML mandatory minimumValue
     */
    double getMinimumValue();

    /**
     * Maximum parameter value.
     * For example, a filtering operation could have a maximum kernel size of 9.
     *
     * @UML mandatory maximumValue
     */
    double getMaximumValue();
}
