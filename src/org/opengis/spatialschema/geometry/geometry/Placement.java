/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Takes a standard geometric construction and places it in geographic space.
 * This interface defines a transformation from a constructive parameter space
 * to the coordinate space of the coordinate reference system being used. Parameter
 * spaces in formulae are given as (<var>u</var>, <var>v</var>) in 2D and
 * (<var>u</var>, <var>v</var>, <var>w</var>) in 3D. Coordinate reference systems
 * positions are given in formulae by either (<var>x</var>, <var>y</var>) in 2D,
 * or (<var>x</var>, <var>y</var>, <var>z</var>) in 3D. 
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
///@UML (identifier="GM_Placement")
public interface Placement {
    /**
     * Return the dimension of the input parameter space.
     */
/// @UML (identifier="inDimension", obligation=MANDATORY)
    public int getInDimension();

    /**
     * Return the dimension of the output coordinate reference system.
     * Normally, <code>outDimension</code> (the dimension of the coordinate reference system)
     * is larger than <code>inDimension</code>. If this is not the case, the transformation is
     * probably singular, and may be replaceable by a simpler one from a smaller dimension
     * parameter space.
     */
/// @UML (identifier="outDimension", obligation=MANDATORY)
    public int getOutDimension();

    /**
     * Maps the parameter coordinate points to the coordinate points in the output Cartesian space.
     *
     * @param in Input coordinate points. The length of this vector must be equals to {@link #getInDimension inDimension}.
     * @return The output coordinate points. The length of this vector is equals to {@link #getOutDimension outDimension}.
     */
/// @UML (identifier="transform", obligation=MANDATORY)
    public double[] transform (double[] in);
}
