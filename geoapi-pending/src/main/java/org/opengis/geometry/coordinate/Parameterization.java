/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import org.opengis.annotation.UML;
import org.opengis.annotation.Draft;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.DirectPosition;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A locally bi-continuous mapping from a {@linkplain #getDomain() domain}
 * {@linkplain CoordinateSystem coordinate system} to a {@linkplain #getRange() range}
 * coordinate system. The mapping is defined inside a {@linkplain Geometry geometry object}
 * (the {@linkplain #getExtentOfValidity() extent of validity} contained in the
 * {@linkplain #getDomain() domain} of the parameterization). This allows us to define a
 * “smaller” geometry object interior this domain geometry object, and then use transform
 * operation of the parameterization to move this smaller thing into the coordinate system
 * defined by the {@linkplain #getRange() range} of the parameterization.
 *
 * <div class="note">
 * For example, if we have a 3D CAD or AEC drawing in a Euclidean design space,
 * and a 3D spline solid, then we can map designs in this drawing to geographically
 * referenced {@linkplain Geometry geometry object} by using the parameterization inherent
 * in the spline as long as the objects are within the range of the splines parameter space.
 * </div>
 *
 * It is defined as an interface, and so instantiations of this concept can carry
 * a variety of mappings, even if they are not defined within the ISO 19107 standard.
 *
 * @author  Axel Francois (LSIS/Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @todo This interface is defined in the ISO 19107 draft. But maybe we should consider retrofitting
 *       it in the GeoAPI <code>org.opengis.referencing.operation.MathTransform</code> interface instead.
 *       See <a href="https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-202">GEO-203</a>.
 */
@Draft
@UML(identifier="GM_Parameterization", specification=ISO_19107)
public interface Parameterization {
    /**
     * Returns the domain of the mapping being represented by this parameterization. The returned
     * geometry object will be in the coordinate system {@linkplain #getDomain() domain} of the
     * parameterization.
     *
     * <div class="note">
     * The parameterization may be considered as a geometry proxy for
     * its “<code>extentOfValidity</code>” geometry object, since it creates an image of this
     * geometry object in its {@linkplain #getRange() range} coordinate system. Splines do
     * exactly this.
     * </div>
     *
     * @return the domain of the mapping being represented by this parameterization.
     */
    @Draft
    @UML(identifier="extentOfValidity", obligation=MANDATORY, specification=ISO_19107)
    Geometry getExtentOfValidity();

    /**
     * The coordinate system which contains the values from which this parameterization maps.
     * This shall be the same as the coordinate system of the
     * {@linkplain #getExtentOfValidity() extent of validity}.
     *
     * <div class="note">
     * In many cases where the parameterization is the constructive mechanism for
     * a {@linkplain Geometry geometry object}, such as a spline, the domain will
     * be a simple Euclidean space of the topological dimension of this target object.
     * </div>
     *
     * @return This parameterization {@linkplain #getDomain() domain} coordinate system.
     */
    @Draft
    @UML(identifier="domain", obligation=MANDATORY, specification=ISO_19107)
    CoordinateSystem getDomain();

    /**
     * Returns the coordinate system which contains the values
     * to which this parameterization maps.
     *
     * <div class="note">
     * In many cases where the parameterization is the constructive mechanism for a
     * {@linkplain Geometry geometry object}, such as a spline, the range will be
     * “coordinateSystem” of the target spline. In this case, the parameterization
     * shall also be a realization of {@linkplain Geometry geometry object}, e.g. the
     * object being constructed. The image in the range is thereby equivalent to a
     * GeometryProxy geometry proxy, under the parameterization of the
     * {@linkplain #getExtentOfValidity() extent of validity}.
     * </div>
     *
     * @return the coordinate system mapped by this parameterization.
     */
    @Draft
    @UML(identifier="range", obligation=MANDATORY, specification=ISO_19107)
    CoordinateSystem getRange();

    /**
     * Transforms a direct position from the {@linkplain #getDomain() domain} coordinate
     * system to the {@linkplain #getRange() range} coordinate system.
     *
     * @param  point The position in the {@linkplain #getDomain() domain} coordinate system.
     * @return the given position in the {@linkplain #getRange() range} coordinate system,
     *         stored in a new object.
     */
    @Draft
    @UML(identifier="transform(DirectPostion)", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition transform(DirectPosition point);

    /**
     * Transforms a geometry from the {@linkplain #getDomain() domain} coordinate
     * system to the {@linkplain #getRange() range} coordinate system.
     *
     * @param  object The geometry in the {@linkplain #getDomain() domain} coordinate system.
     * @return the given geometry in the {@linkplain #getRange() range} coordinate system,
     *         stored in a new object.
     */
    @Draft
    @UML(identifier="transform(Geometry)", obligation=MANDATORY, specification=ISO_19107)
    Geometry transform(Geometry object);
}
