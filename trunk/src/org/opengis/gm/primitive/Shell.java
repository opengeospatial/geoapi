/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;

// OpenGIS direct dependencies
import org.opengis.gm.complex.CompositeSurface;


/**
 * Represents a single connected component of a {@link SolidBoundary}. A shell consists of a
 * number of references to {@link OrientableSurface}s connected in a topological cycle (an
 * object whose boundary is empty). Unlike a {@link Ring}, a <code>Shell</code>'s elements
 * have no natural sort order. Like <code>Ring</code>s, <code>Shell</code>s are simple.
 *
 * @UML type GM_Shell
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see SolidBoundary
 * @see Ring
 */
public interface Shell extends CompositeSurface {
    /**
     * Always returns <code>true</code> since shell objects are simples.
     *
     * @return Always <code>true</code>.
     */
    public boolean isSimple();
}
