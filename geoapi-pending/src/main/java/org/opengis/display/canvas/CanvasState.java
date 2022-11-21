/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.display.canvas;

import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.SphericalCS;
import org.opengis.referencing.crs.DerivedCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.util.InternationalString;


/**
 * Describe the current state of a {@linkplain Canvas canvas}. The information contained
 * by instances of this interface should only describe the viewing area or volume of the
 * canvas and should not contain any state information regarding the data contained within it.
 * <p>
 * When an instance of this class is returned from {@code Canvas} methods, a "snapshot"
 * of the current state of the canvas is taken and the values will never change (even
 * if the canvas changes state).
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since  GeoAPI 2.2
 */
public interface CanvasState {
    /**
     * Returns the title of the {@linkplain Canvas canvas}.
     *
     * @return the canvas title.
     *
     * @see CanvasController#setTitle
     */
    InternationalString getTitle();

    /**
     * Returns the position of the center point of the {@linkplain Canvas canvas}.
     * The coordinate shall be in {@linkplain #getObjectiveCRS objective CRS}.
     *
     * @return the center point in objective CRS.
     *
     * @see CanvasController#setCenter
     */
    DirectPosition getCenter();

    /**
     * Returns the "real world" Coordinate Reference System. This is typically
     * a {@linkplain ProjectedCRS projected CRS} using linear units like metre.
     * Graphic data are projected to this CRS before to be display.
     *
     * @return the "real world" Coordinate Reference System.
     *
     * @see CanvasController#setObjectiveCRS
     */
    CoordinateReferenceSystem getObjectiveCRS();

    /**
     * Returns the Coordinate Reference System associated with the
     * display of the {@linkplain Canvas canvas}. The display CRS
     * {@linkplain CoordinateReferenceSystem#getCoordinateSystem has a Coordinate System}
     * corresponding to the geometry of the display device. For example, flat video monitors
     * are associated to {@linkplain CartesianCS cartesian CS} while planetarium may be
     * associated to {@linkplain SphericalCS spherical CS}. Axis units are typically (but
     * are not restricted to) some linear units like 1/72 of inch.
     * <p>
     * This CRS can be implemented as a {@linkplain DerivedCRS derived CRS} based on the
     * {@linkplain #getObjectiveCRS objective CRS}. In such implementations, the display
     * CRS changes after every zoom or translation action.
     *
     * @return the display Coordinate Reference System.
     */
    CoordinateReferenceSystem getDisplayCRS();

    /**
     * Returns the transform from {@linkplain #getObjectiveCRS objective} to
     * {@linkplain #getDisplayCRS display} CRS. If the latter is implemented as
     * a {@linkplain DerivedCRS derived CRS}, then this transform shall be equal
     * to the following:
     *
     * <blockquote><code>
     * getDisplayCRS().{@linkplain DerivedCRS#getConversionFromBase getConversionFromBase()}.getMathTransform()
     * </code></blockquote>
     *
     * This transform is typically (but is not required to be) affine. When this transform is
     * affine, then the scale factors (the coefficients on the matrix diagonal when there is
     * no rotation or shear) are the map scale along the corresponding axis.
     *
     * @return the transform from {@linkplain #getObjectiveCRS objective} to
     *         {@linkplain #getDisplayCRS display} CRS.
     */
    MathTransform getObjectiveToDisplayTransform();

    /**
     * Returns the transform from {@linkplain #getDisplayCRS display}
     * to {@linkplain #getObjectiveCRS objective} CRS. This is the
     * {@linkplain MathTransform#inverse inverse} of the
     * {@linkplain #getObjectiveToDisplayTransform objective to display transform}.
     *
     * @return the transform from {@linkplain #getDisplayCRS display} to
     *         {@linkplain #getObjectiveCRS objective} CRS.
     */
    MathTransform getDisplayToObjectiveTransform();
}
