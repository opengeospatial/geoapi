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
package org.opengis.feature;

import org.opengis.annotation.UML;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.Classifier;

import static org.opengis.annotation.Specification.OGC_MOVING_FEATURE;


/**
 * Definition of a dynamic attribute in a moving feature type.
 * Dynamic attributes describe attributes of a feature in which the values vary with time and/or location.
 * A {@link Feature} having dynamic attributes is a moving feature.
 * A moving feature can have an arbitrary amount of time-varying attributes,
 * such as the velocity of vehicles or the wind speed of hurricanes.
 *
 * <p>Dynamic attributes are used for non-spatial attribute.
 * If the value type of a dynamic attribute is geometric point, it is a trajectory.</p>
 *
 * @param <V> the type of attribute values.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see DynamicAttribute
 * @see AttributeType
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="AttributeType", specification=OGC_MOVING_FEATURE)
public interface DynamicAttributeType<V> extends AttributeType<V> {
    /**
     * Creates a new attribute instance of this type.
     *
     * @return a new dynamic attribute instance.
     * @throws UnsupportedOperationException if this type does not support new instance creation.
     */
    @Override
    DynamicAttribute<V> newInstance() throws UnsupportedOperationException;
}
