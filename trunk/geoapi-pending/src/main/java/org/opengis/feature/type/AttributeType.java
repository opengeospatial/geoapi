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

import org.opengis.feature.Attribute;

/**
 * The type of an attribute.
 * <p>
 * An attribute is similar to the notion of a UML attribute, or a field of a java
 * object. See the javadoc of {@link Attribute} for more info on the semantics
 * of attributes.
 * </p>
 * <p>
 * <h3>Identifiablily</h3>
 * An attribute may be "identifiable". When this is the case the attribute has a
 * unique identifier associated with it. See {@link Attribute#getID()}. The type
 * of the attribute specifies wether it is identifiable or not ({@link #isIdentified()}.
 * </p>
 *
 * @author Jody Garnett, Refractions Research
 * @author Justin Deoliveira, The Open Planning Project
 *
 * @deprecated Moved to {@link org.opengis.feature.AttributeType} in the {@code org.opengis.feature} package.
 */
public interface AttributeType extends org.opengis.feature.AttributeType<Object>, PropertyType {

    /**
     * Indicates if the type is identified or not.
     * <p>
     * If this method returns <code>true</code>, then the corresponding
     * attribute must have a unique identifier, ie, {@link Attribute#getID()}
     * must return a value, and cannot be <code>null</code>.
     * </p>
     *
     * @return <code>true</code> if the attribute is identified, otherwise <code>false</code>.
     *
     * @see Attribute#getID()
     */
    boolean isIdentified();

    /**
     * Override of {@link PropertyType#getSuper()} which type narrows to
     * {@link AttributeType}.
     *
     * @see PropertyType#getSuper()
     */
    AttributeType getSuper();
}
