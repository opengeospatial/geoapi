/*
 * OpenGIS® Grid Coverage Implementation Specification
 *
 * This Java profile is derived from OpenGIS's specification
 * available on their public web site:
 *
 *     http://www.opengis.org/techno/implementation.htm
 *
 * You can redistribute it, but should not modify it unless
 * for greater OpenGIS compliance.
 */
package org.opengis.gc;


/**
 * Provides information for the parameters required for grid coverage processing
 * operations and grid exchange. This information includes such information as
 * the name of the parameter, parameter description, parameter type etc.
 *
 * @version 1.00
 * @since   1.00
 */
public interface GC_ParameterInfo {
    /**
     * Parameter name.
     */
    String getName();

    /**
     * Parameter description.
     * If no description, the value will be null.
     */
    String getDescription();

    /**
     * Parameter type.
     * The enumeration contains standard parameter types for integer, string,
     * floating-point numbers, objects, etc.
     */
    GC_ParameterType getType();

    /**
     * Default value for parameter.
     * The type {@link Object} can be any type including a {@link Number} or a
     * {@link String}. For example, a filtering operation could have a default
     * kernel size of 3. If there is no default value, defaultValue will be null.
     */
    Object getDefaultValue();

    /**
     * Minimum parameter value.
     * For example, a filtering operation could have a minimum kernel size of 3.
     */
    double getMinimumValue();

    /**
     * Maximum parameter value.
     * For example, a filtering operation could have a maximum kernel size of 9.
     */
    double getMaximumValue();
}
