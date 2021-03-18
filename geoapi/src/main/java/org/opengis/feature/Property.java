/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.feature;

import org.opengis.util.GenericName;


/**
 * An instance of a {@link PropertyType}.
 * A property is usually part of another entity such as a {@link Feature}.
 * This interface is the parent type of {@linkplain Attribute attribute} and
 * {@linkplain FeatureAssociation feature association} but not feature.
 *
 * <p>A property is a wrapper around an arbitrary object or value.
 * It provides the following information:</p>
 *
 * <ul>
 *   <li>A value, available via the {@link #getValue()} method.
 *       The value can be set via a setter method provided by the sub-interface.</li>
 *   <li>A type, available via the {@code getType()} or {@code getRole()} method provided by the sub-interface.
 *       The {@link PropertyType} defines information about the property.
 *       This includes which Java class the value of the property is an instance of, any restrictions on
 *       the value, <i>etc</i>.</li>
 * </ul>
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public interface Property {
    /**
     * Returns the name of this property.
     * This is a convenience method for {@code getType().getName()} or
     * {@code getRole().getName()}, depending on the sub-interface.
     *
     * @return name of this property.
     */
    GenericName getName();

    /**
     * Returns the value or content of the property, or {@code null} if none.
     * <ul>
     *   <li>If this property is an {@link Attribute}, then the returned object may be an instance of any Java class
     *       assignable to {@link AttributeType#getValueClass()}.</li>
     *   <li>If this property is an {@link FeatureAssociation}, then the returned object is a {@link Feature}.</li>
     * </ul>
     *
     * @return the value of the property, or {@code null} if none.
     */
    Object getValue();
}
