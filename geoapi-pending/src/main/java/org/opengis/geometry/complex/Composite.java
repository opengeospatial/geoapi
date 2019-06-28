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
package org.opengis.geometry.complex;

import java.util.Collection;
import org.opengis.geometry.primitive.Primitive;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A geometric complex with an underlying core geometry that is isomorphic to a primitive. Thus,
 * a composite curve is a collection of curves whose geometry interface could be satisfied by a
 * single curve (albeit a much more complex one). Composites are intended for use as attribute
 * values in datasets in which the underlying geometry has been decomposed, usually to expose its
 * topological nature.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_Composite", specification=ISO_19107)
public interface Composite extends Complex {
    /**
     * Returns a homogeneous collection of {@linkplain Primitive primitives} whose union would be
     * the core geometry of the composite. The complex would include all primitives in the generator
     * and all primitives on the boundary of these primitives, and so forth until
     * {@linkplain org.opengis.geometry.primitive.Point points} are included. Thus the
     * {@code generators} on {@code Composite} is a subset of the
     * {@linkplain Complex#getElements elements} on {@linkplain Complex complex}.
     *
     * @return the list of primitives in this composite.
     *
     * @see CompositePoint#getGenerators
     * @see CompositeCurve#getGenerators
     * @see CompositeSurface#getGenerators
     * @see CompositeSolid#getGenerators
     */
    @UML(identifier="generator", obligation=MANDATORY, specification=ISO_19107)
    Collection<? extends Primitive> getGenerators();
}
