/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;

// OpenGIS direct dependencies
import org.opengis.gm.Boundary;
import org.opengis.gm.geometry.Position;
import org.opengis.gm.geometry.DirectPosition;


/**
 * Basic data type for a geometric object consisting of one and only one point.
 * In most cases, the state of a <code>Point</code> is fully determined by its
 * position attribute. The only exception to this is if the <code>Point</code>
 * has been subclassed to provide additional non-geometric information such as
 * symbology.
 *
 * @UML type GM_Point
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see Position#toPoint
 *
 * @revisit Some associations are commented out for now.
 */
public interface Point extends Primitive {
    /**
     * Returns the direct position of this point. <code>Point</code> is the only subclass of
     * {@link Primitive} that cannot use {@link Position}s to represent its defining geometry.
     * A {@link Position} is either a {@link DirectPosition} or a reference to a <code>Point</code>
     * (from which a {@link DirectPosition} may be obtained). By not allowing <code>Point</code>
     * to use this technique, infinitely recursive references are prevented. Applications may
     * choose another mechanism to prevent this logical problem.
     *
     * @return The direct position.
     * @UML mandatory position
     */
    public DirectPosition getPosition();

    /**
     * Returns always <code>null</code>, since point has no boundary.
     *
     * @return Always <code>null</code>.
     * @UML operation boundary
     */
    public PrimitiveBoundary getBoundary();

    /**
     * Returns the bearing, as a unit vector, of the tangent (at this <code>Point</code>)
     * to the curve between this <code>Point</code> and a passed {@link Position}.
     * The choice of the curve type for defining the bearing is dependent on the 
     * {@link org.opengis.sc.CRS} in which this <code>Point</code> is defined.
     * For example, in the Mercator projection, the curve is the rhumb line.
     * In 3D, geocentric coordinate system, the curve may be the geodesic joining the two
     * points along the surface of the geoid or ellipsoid in use. Implementations that support
     * this function shall specify the nature of the curve to be used.
     *
     * @param toPoint the destination point.
     * @return The tangent to the curve between this point and the passed position.
     * @UML operation bearing
     */
    public Bearing getBearing(Position toPoint);

//    public org.opengis.spatialschema.geometry.complex.GM_CompositePoint composite[];
}
