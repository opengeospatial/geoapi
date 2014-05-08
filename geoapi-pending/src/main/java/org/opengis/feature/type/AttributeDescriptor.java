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

/**
 * Describes an instance of an Attribute.
 * <p>
 * An AttributeDescriptor is an extension of {@link PropertyDescriptor} which
 * defines some additional information:
 * <ul>
 *   <li>A default value for an attribute
 * </ul>
 * </p>
 * <p>
 *
 * @author Jody Garnett, Refractions Research
 * @author Justin Deoliveira, The Open Planning Project
 */
public interface AttributeDescriptor extends PropertyDescriptor {

    /**
     * Override of {@link PropertyDescriptor#getType()} which type narrows to
     * {@link AttributeType}.
     *
     *  @see PropertyDescriptor#getType()
     */
    AttributeType getType();

    /**
     * The local name for this AttributeDescriptor.
     * Specifically this returns <code>getName().getLocalPart</code>().
     * @return The local name for this attribute descriptor.
     */
    String getLocalName();

    /**
     * The default value for the attribute.
     * <p>
     * This value is used when an attribute is created and no value for it is
     * specified.
     * </p>
     * <p>
     * This value may be <code>null</code>. If it is non-null it should be an
     * instance of of the class specified by <code>getType().getBinding()</code>.
     * </p>
     */
    Object getDefaultValue();
}
