/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.complex;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Geometry;
import org.opengis.spatialschema.geometry.primitive.Primitive; // For javadoc


/**
 * A collection of geometrically disjoint, simple {@linkplain Primitive primitives}. If a
 * {@linkplain Primitive primitive} (other than a {@linkplain org.opengis.spatialschema.geometry.primitive.Point point}
 * is in a particular <code>Complex</code>, then there exists a set of primitives of lower dimension
 * in the same complex that form the boundary of this primitive.
 * <br><br>
 * A geometric complex can be thought of as a set in two distinct ways. First, it is a finite set
 * of objects (via delegation to its elements member) and, second, it is an infinite set of point
 * values as a subtype of geometric object. The dual use of delegation and subtyping is to
 * disambiguate the two types of set interface. To determine if a {@linkplain Primitive primitive}
 * <var>P</var> is an element of a <code>Complex</code> <var>C</var>,
 * call: <code>C.element().contains(P)</code>.
 * <br><br>
 * The "{@linkplain #getElements elements}" attribute allows <code>Complex</code> to inherit the
 * behavior of {@link Set Set&lt;Primitive&gt;} without confusing the same sort of behavior
 * inherited from {@link org.opengis.spatialschema.geometry.TransfiniteSet TransfiniteSet&lt;DirectPosition&gt;}
 * inherited through {@link Geometry}. Complexes shall be used in application schemas where
 * the sharing of geometry is important, such as in the use of computational topology. In a
 * complex, primitives may be aggregated many-to-many into composites for use as attributes
 * of features.
 *
 * @UML type GM_Complex
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit Some associations are commented out for now.
 */
public interface Complex extends Geometry {
    /**
     * Returns <code>true</code> if and only if this <code>Complex</code> is maximal.
     * A complex is maximal if it is a subcomplex of no larger complex.
     *
     * @return <code>true</code> if this GM_Complex is maximal.
     * @UML operation isMaximal
     */
    public boolean isMaximal();

    /**
     * Returns a superset of primitives that is also a complex.
     *
     * @return The supercomplexes, or an empty array if none.
     * @UML operation superComplex
     */
    public Complex[] getSuperComplexex();

    /**
     * Returns a subset of the primitives of that complex
     * that is, in its own right, a geometric complex.
     *
     * @return The subcomplexes, or an empty array if none.
     * @UML operation subComplex
     */
    public Complex[] getSubComplexes();

    /**
     * Returns the set of primitives contained in this complex.
     *
     * @return The set of primitives for this complex.
     * @UML operation element
     */
    public Set/*<Primitive>*/ getElements();

//    public org.opengis.spatialschema.topology.complex.TP_Complex topology[];
}
