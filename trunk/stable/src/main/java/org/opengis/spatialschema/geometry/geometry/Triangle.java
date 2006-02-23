/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// J2SE direct dependencies
import java.util.List;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A planar polygon defined by 3 corners. That is, a triangle would be the result of a constructor
 * of the form: {@code Polygon(LineString({P1, P2, P3, P1}))} where <var>P</var><sub>1</sub>,
 * <var>P</var><sub>2</sub>, and <var>P</var><sub>3</sub> are three {@linkplain Position positions}.
 * Triangles have no holes. Triangle shall be used to construct
 * {@linkplain TriangulatedSurface triangulated surfaces}.
 * <p>
 * <strong>Note:</strong> The points in a triangle can be located in terms of their corner points
 * by defining a set of barycentric coordinates, three nonnegative numbers <var>c</var><sub>1</sub>,
 * <var>c</var><sub>2</sub>, and <var>c</var><sub>3</sub> such that
 *
 * <var>c</var><sub>1</sub> + <var>c</var><sub>2</sub> + <var>c</var><sub>3</sub> = 1.0.
 *
 * Then, each point <var>P</var> in the triangle can be expressed for some set of barycentric coordinates as: 
 * <p>
 * <blockquote>
 * P = <var>c</var><sub>1</sub><var>P</var><sub>1</sub> +
 *     <var>c</var><sub>2</sub><var>P</var><sub>2</sub> +
 *     <var>c</var><sub>3</sub><var>P</var><sub>3</sub>
 * </blockquote>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_Triangle", specification=ISO_19107)
public interface Triangle extends Polygon {
    /**
     * Returns the triangle corner. The list must contains exactly 3 elements.
     */
    @UML(identifier="corners", obligation=MANDATORY, specification=ISO_19107)
    List<Position> getCorners();

    /**
     * Returns the patch which own this surface patch.
     */
/// @UML(identifier="surface", obligation=MANDATORY, specification=ISO_19107)
/// TriangulatedSurface getSurface();
}
