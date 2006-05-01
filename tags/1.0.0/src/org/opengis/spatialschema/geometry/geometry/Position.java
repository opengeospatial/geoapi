/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.primitive.Point;


/**
 * A union type consisting of either a {@linkplain DirectPosition direct position} or of a
 * reference to a {@linkplain Point point} from which a {@linkplain DirectPosition direct
 * position} shall be obtained. The use of this data type allows the identification of a
 * position either directly as a coordinate (variant direct) or indirectly as a reference
 * to a {@linkplain Point point} (variant indirect).
 *  
 * @UML datatype GM_Position
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
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
}
