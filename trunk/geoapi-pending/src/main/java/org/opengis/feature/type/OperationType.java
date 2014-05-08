/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2014 Open Geospatial Consortium, Inc.
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
package org.opengis.feature.type;

import java.util.List;

import org.opengis.filter.Filter;

/**
 * The type of operations to be invoked on an attribute.
 * <p>
 * Invoking an operation on an attribute is used to calculate a derived quantity
 * or update attribute state. OperationType is used to define the required
 * parameters and expected result for an Operation.
 *
 * @author Jody Garnett, Refractions Research, Inc.
 */
 public interface OperationType extends PropertyType {

    /**
     * Access to super type information.
     * <p>
     * The super type of an operation provides additional
     * restrictions and description for this operation.
     * </p>
     * @return super type
     */
     OperationType getSuper();

    /**
     * Indicate that this OperationType may not be used directly.
     * <p>
     * This indicates that a sub type will need to actually define the operation
     * meaning here. As an example a graph system could have an Edge that would
     * have "related" operation returning that was abstract, and a sub type road
     * would define "related" based on touches, or "contains" or "common vertex".
     * </p>
     */
     boolean isAbstract();

    /**
     * AttributeType this operation type can function against.
     */
     AttributeType getTarget();

     /**
      * Indicates the expected result type, may be <code>null</code>.
      *
      * @return expected result type, may be <code>null</code>
      */
     AttributeType getResult();

    /**
     * We need the following AttributeTypes as parameters.
     * <p>
     * Note we do not need AttributeDescriptors here as parameters
     * are ordered, so name is not needed.
     * </p>
     * @return indicates paramters required for operation
     */
    List<AttributeType> getParameters();

    /**
     * List of restrictions used to limit the allowable returned value.
     *
     * @return Restrictions on valid return values
     */
     List<Filter> getRestrictions();
}
