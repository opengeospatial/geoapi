/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.geometry;

// OpenGIS direct dependencies
import org.opengis.gm.primitive.Point;


/**
 * A union type consisting of either a {@link DirectPosition} or of a reference to a {@link Point}
 * from which a {@link DirectPosition} shall be obtained. The use of this data type allows the
 * identification of a position either directly as a coordinate (variant direct) or indirectly as a
 * reference to a {@link Point} (variant indirect).
 *  
 * @UML datatype GM_Position
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit Is this interface really needed for a Java profile?
 */
public interface Position {
    /**
     * Returns the direct position.
     * If <code>null</code>, then {@link #getIndirect} must returns a non-null value.
     *
     * @return The direct position, or <code>null</code>.
     * @UML conditional direct
     */
    public DirectPosition getDirect();

    /**
     * Returns the point.
     * If <code>null</code>, then {@link #getDirect} must returns a non-null value.
     *
     * @return The point, or <code>null</code>.
     * @UML conditional indirect
     */
    public Point getIndirect();

    /**
     * Creates a point at this position.
     *
     * @return The point at this position.
     * @UML constructor Point(Position)
     *
     * @revisit We should probably move this constructor in a factory instead.
     */
    public Point toPoint();
}
