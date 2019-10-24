/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A placement defined by linear transformation from the parameter space to the target
 * coordinate space. In 2-dimensional Cartesian parameter space, (<var>u</var>, <var>v</var>),
 * transforms into a 3-dimensional coordinate reference system, (<var>x</var>, <var>y</var>, <var>z</var>),
 * by using an affine transformation, (<var>u</var>, <var>v</var>) → (<var>x</var>, <var>y</var>, <var>z</var>),
 * which is defined:
 *
 * <img src="doc-files/AffinePlacement.png" alt="Afine placement">
 *
 * Then, given this equation, the {@link #getLocation()} method returns the direct position
 * (<var>x₀</var>, <var>y₀</var>, <var>z₀</var>), which
 * is the target position of the origin in (<var>u</var>, <var>v</var>). The two
 * {@linkplain #getReferenceDirection(int) reference directions}
 * (<var>u</var><sub>x</sub>, <var>u</var><sub>y</sub>, <var>u</var><sub>z</sub>)
 * and (<var>v</var><sub>x</sub>, <var>v</var><sub>y</sub>, <var>v</var><sub>z</sub>) are the
 * target directions of the unit basis vectors at the origin in (<var>u</var>, <var>v</var>).
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Axel Francois (LSIS/Geomatys)
 * @version 3.1
 * @since   2.0
 *
 * @todo Refactor as an {@code org.opengis.referencing.operation.AffineTransform} interface?
 */
@UML(identifier="GM_AffinePlacement", specification=ISO_19107)
public interface AffinePlacement extends Placement {
    /**
     * Gives the target of the parameter space origin. This is the vector
     * (<var>x₀</var>, <var>y₀</var>, <var>z₀</var>)
     * in the formulae shown in the class description.
     *
     * @return the target of the parameter space origin.
     */
    @UML(identifier="location", obligation=MANDATORY, specification=ISO_19107)
    Position getLocation();

    /**
     * Gives the target directions for the coordinate basis vectors of the parameter space.
     * These are the columns of the matrix in the formulae given in the class description.
     * The number of directions given shall be {@link #getInDimension() inDimension}.
     * The dimension of the directions shall be {@link #getOutDimension() outDimension}.
     *
     * @param  dimension  the in dimension, as an index from 0 inclusive to
     *         {@link #getInDimension() inDimension} exclusive.
     * @return the direction as an array of length {@link #getOutDimension() outDimension}.
     */
    @UML(identifier="refDirection", obligation=MANDATORY, specification=ISO_19107)
    double[] getReferenceDirection(int dimension);
}
