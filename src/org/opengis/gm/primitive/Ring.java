/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;

// OpenGIS direct dependencies
import org.opengis.gm.complex.CompositeCurve;


/**
 * Represent a single connected component of a {@link SurfaceBoundary}. It consists of a number
 * of references to {@link OrientableCurve}s connected in a cycle (an object whose boundary is
 * empty). A <code>Ring</code> is structurally similar to a {@link CompositeCurve} in that the
 * end point of each <code>OrientableCurve</code> in the sequence is the start point of the next
 * <code>OrientableCurve</code> in the Sequence. Since the sequence is circular, there is no
 * exception to this rule. Each ring, like all boundaries is a cycle and each ring is simple.
 * <br><br>
 * Even though each <code>Ring</code> is simple, the boundary need not be simple. The easiest
 * case of this is where one of the interior rings of a surface is tangent to its exterior ring.
 * Implementations may enforce stronger restrictions on the interaction of boundary elements.
 *
 * @UML type GM_Ring
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see SurfaceBoundary
 * @see Shell
 */
public interface Ring extends CompositeCurve {
    /**
     * Always returns <code>true</code> since ring objects are simples.
     *
     * @return Always <code>true</code>.
     */
    public boolean isSimple();
}
