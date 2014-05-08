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
package org.opengis.feature;

import org.opengis.feature.type.FeatureType;
import org.opengis.filter.identity.FeatureId;
import org.opengis.geometry.Envelope;

/**
 * An instance of {@link FeatureType} representing a geographic feature composed of geometric
 * and non-geometric properties.
 * <p>
 * Beyond being a complex attribute, a feature contains the following additional information:
 * <ul>
 * <li>A default geometry. To be used when drawing when nothing more
 *     specific has been provided.
 * <li>The bounds of all the geometric attributes of the feature
 * </ul>
 * </p>
 * @author Jody Garnett (Refractions Research)
 * @author Justin Deoliveira (The Open Planning Project)
 * @version GeoAPI 2.2
 */
public interface Feature extends ComplexAttribute {

    /**
     * Override and type narrow to FeatureType.
     * @return The feature type
     */
    FeatureType getType();

    /**
     * A unique identifier for the feature.
     * <p>
     * <code>getType().isIdentifiable()</code> must return <code>true</code>
     * so this value must not return <code>null</code>.
     * </p>
     * <p>
     * Generation of the identifier is dependent on the underlying data storage
     * medium. Often this identifier is not persistent. Mediums such shapefiles
     * and database tables have "keys" built in which map naturally to
     * persistent feature identifiers. But other mediums do not have such keys
     * and may have to generate feature identifiers "on-the-fly". This means
     * that client code being able to depend on this value as a persistent
     * entity is dependent on which storage medium or data source is being used.
     * </p>
     *
     * @return The feature identifier, never <code>null</code>.
     */
    FeatureId getIdentifier();

    /**
     * The bounds of this Feature, if available.
     * <p>
     * This value is derived from any geometric attributes that the feature is
     * composed of.
     * </p>
     * <p>
     * In the case that the feature has no geometric attributes this method
     * should return an empty bounds, ie, <code>bounds.isEmpty() == true</code>.
     * This method should never return <code>null</code>.
     * </p>
     * <p>
     * The coordinate reference system of the returned bounds is derived from
     * the geometric attributes which were used to compute the bounds. In the
     * event that the feature contains multiple geometric attributes which have
     * different crs's, the one defined by {@link #getGeometryDescriptor()} should
     * take precedence and the others should be reprojected accordingly.
     * </p>
     *
     * @return the feature bounds, possibly empty.
     */
    Envelope getBounds();

    /**
     * The default geometric attribute of the feature.
     * <p>
     * This method returns <code>null</code> in the case where no such
     * attribute exists.
     * </p>
     *
     * @return The default geometry attribute, or <code>null</code>.
     */
    GeometryAttribute getDefaultGeometryProperty();

    /**
     * Sets the default geometric attribute of the feature.
     * <p>
     * This value must be an attribute which is already defined for the feature.
     * In other words, this method can not be used to add a new attribute to the
     * feature.
     * </p>
     *
     * @param geometryAttribute
     *            The new geomtric attribute.
     *
     * @throws IllegalArgumentException If the specified attribute is not already
     * an attribute of the feature.
     */
    void setDefaultGeometryProperty(GeometryAttribute geometryAttribute);
}
