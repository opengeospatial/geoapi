/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.complex;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.OrientableSurface;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain Complex complex} with all the geometric properties of a surface. Thus, this
 * composite can be considered as a type of {@linkplain OrientableSurface orientable surface}.
 * Essentially, a composite surface is a collection of oriented surfaces that join in pairs on
 * common boundary curves and which, when considered as a whole, form a single surface.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @todo This interface extends (indirectly) both {@link org.opengis.spatialschema.geometry.primitive.Primitive} and
 *       {@link org.opengis.spatialschema.geometry.complex.Complex}. Concequently, there is a clash in the semantics
 *       of some set theoretic operation. Specifically, {@code Primitive.contains(...)}
 *       (returns FALSE for end points) is different from {@code Complex.contains(...)}
 *       (returns TRUE for end points).
 */
@UML(identifier="GM_CompositeSurface", specification=ISO_19107)
public interface CompositeSurface extends Composite, OrientableSurface {
    /**
     * Returns the list of orientable surfaces in this composite.
     *
     * To get a full representation of the elements in the {@linkplain Complex complex}, the
     * {@linkplain org.opengis.spatialschema.geometry.primitive.Curve curves} and {@link org.opengis.spatialschema.geometry.primitive.Point
     * points} on the boundary of the generator set of {@linkplain org.opengis.spatialschema.geometry.primitive.Surface
     * surfaces} would be added to the curves in the generator list.
     *
     * @return The list of orientable surfaces in this composite.
     */
    @UML(identifier="generator", obligation=MANDATORY, specification=ISO_19107)
    public List<OrientableSurface> getGenerators();
}
