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
package org.opengis.coverage.grid;


/**
 * The parameter required for a grid coverage processing operation.
 * This structure contains the parameter name (as defined from the
 * {@link GC_ParameterInfo} structure) and it s value.
 *
 * @version 1.00
 * @since   1.00
 */
public interface GC_Parameter {
    /**
     * Parameter name.
     */
    String getName();

    /**
     * The value for parameter.
     * The type {@link Object} can be any type including a {@link Number},
     * a {@link String} or an instance of an interface. For example, a grid
     * processor operation will typically require a parameter for the input
     * grid coverage. This parameter may have <code>"Source"</code> as the
     * parameter name and the instance of the grid coverage as the value.
     */
    Object getValue();
}
