/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE direct dependencies
import java.util.Set;
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.geometry.Tin;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain ContinuousCoverage continuous coverage} characterized by a {@linkplain Tin TIN}.
 * The feature attribute values are computed by interpolation within each triangle in the
 * tessellation using the record of feature attribute values provided at each corner; that
 * is, the feature attribute values are produced by an operation on {@link ValueTriangle}s.
 * <p>
 * The basic idea of a TIN is to partition the convex hull of the points in the domain of a
 * discrete point coverage into a computationally unique set of non-overlapping triangles.
 * Each triangle is formed by three of the points in the domain of the discrete point coverage.
 * The Delaunay triangulation method is commonly used to produce TIN tessellations with triangles
 * that are optimally equiangular in shape, and are generated in such a manner that the
 * circumscribing circle containing each triangle contains no point of the discrete point
 * coverage other than those at the vertices of the triangle. {@link Tin} describes a Delaunay
 * triangulation.
 *
 * @author Alessio Fabiani
 * @author Martin Desruisseaux
 *
 * @todo Add a figure derived from figure 22 in ISO 19123.
 */
@UML(identifier="CV_TINCoverage", specification=ISO_19123)
public interface TinCoverage extends ContinuousCoverage {
    /**
     * Returns the triangulated irregular network that provides the structure for evaluating the
     * coverage. {@link Tin} includes a capability for using stop lines and break lines in the
     * triangulation.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    Tin getGeometry();

    /**
     * Returns the interpolation method to be used in evaluating the coverage. The most common
     * interpolation method is "{@linkplain InterpolationMethod#BARYCENTRIC barycentric}"
     */
    @UML(identifier="interpolationType", obligation=OPTIONAL, specification=ISO_19123)
    InterpolationMethod getInterpolationMethod();

    /**
     * Returns the set of value objects used to evaluate the coverage. This
     * association is optional – an analytical coverage needs no value objects.
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<ValueTriangle> getElements();

    /**
     * Returns the set of {@linkplain ValueTriangle value triangles} that contains the specified
     * direct position.
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<ValueTriangle> locate(DirectPosition p);

    /**
     * Returns a set of records of feature attribute values for the specified direct position.
     * Evaluation of a TIN coverage involves two steps. The first is to find the {@linkplain
     * ValueTriangle value triangle} that contains the input direct position; the second is to
     * interpolate the feature attribute values at the direct position from the {@linkplain
     * PointValuePair point-value pairs} at the vertices of the value triangle.
     *
     * @todo The return type should be Set<Record>.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set/*<Record>*/ evaluate(DirectPosition p, Collection<String> list);
}
