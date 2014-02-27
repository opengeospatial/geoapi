/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2013 Open Geospatial Consortium, Inc.
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

import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.feature.type.GeometryType;
import org.opengis.geometry.Envelope;

/**
 * An attribute which has a geometric value.
 * <p>
 * The type of the value of the attribute is an arbitrary object and is
 * implementation dependent. Implementations of this interface may wish to type
 * narrow {@link Property#getValue()} to be specific about the type geometry.
 * For instance to return explicitly a JTS geometry.
 * </p>
 * <p>
 * Past a regular attribute, GeometryAttribute provides a method for obtaining
 * the bounds of the underlying geometry, {@link #getBounds()}. The
 * {@link #setBounds(Envelope)} method is used to explicitly set the bounds
 * which can be useful in situations where the data source stores the bounds
 * explicitly along with the geometry.
 * </p>
 *
 * @author Jody Garnett, Refractions Research
 * @author Justin Deoliveira, The Open Planning Project
 */
public interface GeometryAttribute extends Attribute {

    /**
     * Override and type narrow to GeometryType.
     */
    GeometryType getType();

    /**
     * Override and type narrow to GeometryDescriptor.
     * @return The geometry descriptor, may be null if this is a top-level value
     */
    GeometryDescriptor getDescriptor();

    /**
     * The bounds of the attribute.
     * <p>
     * This value should be derived unless explicitly set via
     * {@link #setBounds(Envelope)}.
     * </p>
     * <p>
     * In the case that the underlying geometry is <code>null</code>, this
     * method should return an empty bounds as opposed to returning
     * <code>null</code>.
     * </p>
     *
     * @return The bounds of the underlying geometry, possibly empty.
     */
    Envelope getBounds();

    /**
     * Sets the bounds of the geometry.
     * <p>
     * This method should be used when the bounds is pre-computed and there is
     * no need to derive it from scratch. This is mostly only relevant to data
     * sources which store the bounds along with the geometry.
     * </p>
     * <p>
     * Setting the bounds to <code>null</code> is allowed and will force the
     * bounds to be derived manually on the next call to {@link #getBounds()}.
     * </p>
     *
     * @param bounds
     *            The bounds of the attribute.
     */
    void setBounds(Envelope bounds);

}
