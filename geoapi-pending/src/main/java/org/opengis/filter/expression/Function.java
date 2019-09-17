/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.expression;

import java.util.List;

import org.opengis.annotation.XmlElement;


/**
 * Instances of this class represent a function call into some implementation-specific
 * function.
 * <p>
 * Each execution environment should provide a list of supported functions
 * (and the number of arguments they expect) as part of a FilterCapabilities
 * data structure.
 * <p>
 * This is included for completeness with respect to the
 * OGC Filter specification.  However, no functions are required to be supported
 * by that specification.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Function")
public interface Function extends Expression {
    /**
     * Returns the name of the function to be called.  For example, this might
     * be "{@code cos}" or "{@code atan2}".
     * <p>
     * You can use this name to look up the number of required parameters
     * in a FilterCapabilities data structure. For the specific meaning of
     * the required parameters you will need to consult the documentation.
     */
    String getName();

    /**
     * Returns the list subexpressions that will be evaluated to provide the
     * parameters to the function.
     */
    List<Expression> getParameters();

    /**
     * The value of the fallbackValue attribute is used as a default value, if the SE
     * implementation does not support the function. If the implementation supports the
     * function, then the result value is determined by executing the function.
     *
     * @return Optional literal to use if an implementation for this function is not available.
     *
     * @since GeoAPI 2.2
     *
     * @todo Does this method make sense for a Java interface?
     */
    @XmlElement("fallbackValue")
    Literal getFallbackValue();
}
