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
package org.opengis.geometry.primitive;

// OpenGIS direct dependencies
import static org.opengis.annotation.Specification.ISO_19107;

import org.opengis.annotation.UML;
import org.opengis.geometry.complex.CompositeSurface;


/**
 * Represents a single connected component of a {@linkplain SolidBoundary solid boundary}.
 * A shell consists of a number of references to {@linkplain OrientableSurface orientable
 * surfaces} connected in a topological cycle (an object whose boundary is empty). Unlike a
 * {@linkplain Ring ring}, a {@code Shell}'s elements have no natural sort order. Like
 * {@linkplain Ring rings}, {@code Shell}s are simple.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see SolidBoundary
 * @see Ring
 */
@UML(identifier="GM_Shell", specification=ISO_19107)
public interface Shell extends CompositeSurface {
    /**
     * Always returns {@code true} since shell objects are simples.
     *
     * @return Always {@code true}.
     */
    public boolean isSimple();
}
