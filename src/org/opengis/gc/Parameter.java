/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gc;


/**
 * The parameter required for a grid coverage processing operation.
 * This structure contains the parameter name (as defined from the
 * {@link ParameterInfo} structure) and it's value.
 *
 * @UML datatype CV_Parameter
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 */
public interface Parameter {
    /**
     * Returns the informations associated to this parameter.
     */
    ParameterInfo getParameterInfo();

    /**
     * Parameter name.
     *
     * @UML mandatory name
     */
    String getName();

    /**
     * The value for parameter.
     * The type {@link Object} can be any type including a {@link Number},
     * a {@link String} or an instance of an interface. For example, a grid
     * processor operation will typically require a parameter for the input
     * grid coverage. This parameter may have <code>"Source"</code> as the
     * parameter name and the instance of the grid coverage as the value.
     *
     * @UML mandatory value
     */
    Object getValue();
}
