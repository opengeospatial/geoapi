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

import java.lang.reflect.InvocationTargetException;

import org.opengis.feature.Attribute;

/**
 * An implementation of an operation that may be invoked on an Attribute.
 *
 * @author Jody Garnett, Refractions Research
 */
public interface Operation extends PropertyDescriptor {

    /**
     * Operations are not part of the structure.
     * @return 0 in order to not trip up
     */
    int getMaxOccurs();

    /**
     * Operations are not part of the structure.
     * @return 0 in order to not trip up
     */
    int getMinOccurs();

    /** Indicates the OpperationType of this attribute */
    OperationType getType();

    /**
     * Indicates if invoke may be called.
     * <p>
     * In order allow for faithful description of a software system we will need
     * construct models dynamically at runtime, possibly when no implementation
     * of this Operation is available. As an example when working with features
     * in a web application some operations may only be available
     * when being executed on a remote web processing service.
     * </p>
     *
     * @return true if invoke may be called.
     */
    boolean isImplemented();

    /**
     * Invoke this operation on an attribute using the provided parameters.
     * <p>
     * The state of the attribute may be used and / or updated during the
     * execution of the operation.
     * </p>
     * <p>
     * Please check to ensure that isImplemented returns <code>true</code>
     * before calling invoke.
     *
     * @param target
     *            Attribute this operation is being applied to, the state of this
     *            attribute may be changed by this operation.
     * @param params parameters used by the operation
     * @return the result of the operation
     * @throws InvoationTargetException
     *             if an error occurred while processing
     */
    Object invoke(Attribute target, Object params[])
            throws InvocationTargetException;

}
