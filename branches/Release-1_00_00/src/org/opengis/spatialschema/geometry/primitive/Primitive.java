/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Geometry;
import org.opengis.spatialschema.geometry.Envelope;
import org.opengis.spatialschema.geometry.complex.Complex;


/**
 * Abstract root class of the geometric primitives. Its main purpose is to define the basic
 * "boundary" operation that ties the primitives in each dimension together. A geometric primitive
 * is a geometric object that is not decomposed further into other primitives in the system. This
 * includes curves and surfaces, even though they are composed of curve segments and surface patches,
 * respectively. Those curve segments and surface patches cannot exist outside the context of a
 * primitive.
 * <br><br>
 * Any geometric object that is used to describe a feature is a collection of geometric primitives.
 * A collection of geometric primitives may or may not be a geometric complex. Geometric complexes
 * have additional properties such as closure by boundary operations and mutually exclusive component
 * parts. <code>Primitive</code> and {@link Complex} share most semantics, in the meaning of operations
 * and attributes. There is an exception in that a <code>Primitive</code> shall not contain its boundary
 * (except in the trivial case of {@linkplain Point point} where the boundary is empty), while a
 * {@linkplain Complex complex} shall contain its boundary in all cases.
 *
 * @UML type GM_Primitive
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see PrimitiveFactory#createPrimitive(Envelope)
 *
 * @revisit Some associations are commented out for now.
 */
public interface Primitive extends Geometry {
    /**
     * Returns the boundary of a <code>Primitive</code> as a set of 
     * <code>Primitive</code>s. This is a specialization of the operation at
     * {@link Geometry}, which does not restrict the class of the returned collection.
     * The organization of the boundary set of a <code>Primitive</code> depends on the
     * type of the primitive.
     *
     * @return The sets of positions on the boundary.
     * @UML operation boundary
     */
/// public PrimitiveBoundary getBoundary();

    /**
     * Returns the <code>Primitive</code>s which are by definition coincident with this one.
     * This allows applications to override the
     * {@link org.opengis.spatialschema.geometry.TransfiniteSet TransfiniteSet&lt;DirectPosition&gt;}
     * interpretation and its associated computational geometry, and declare one
     * <code>Primitive</code> to be "interior to" another.
     *
     * This set should normally be empty when the <code>Primitive</code>s are within a
     * {@linkplain Complex complex}, since in that case the boundary
     * information is sufficient for most cases.
     *
     * This association should not be used when the two <code>Primitive</code>s are not close
     * to one another. The intent is to allow applications to compensate for inherent and
     * unavoidable round off, truncation, and other mathematical problems indigenous to
     * computer calculations.
     *
     * @return The set of primitives contained into this primitive.
     * @UML association containedPrimitive
     *
     * @revisit Using a {@link Set} returns type allows the user to add or remove element in
     *          this set at his convenience. Is it the right interpretation of this specification?
     *
     * @see #getContainingPrimitives
     */
    public Set/*<Primitive>*/ getContainedPrimitives();

    /**
     * Returns the <code>Primitive</code>s which are by definition coincident with this one.
     *
     * @return The set of primitives which contains this primitive.
     * @UML association containingPrimitive
     *
     * @revisit Using a {@link Set} returns type allows the user to add or remove element in
     *          this set at his convenience. Is it the right interpretation of this specification?
     *
     *          Should we stretch out some relation with contained primitive? For example
     *          should we update the specification with something like the following?
     *          "Invoking <code>B.getContainingPrimitive().add(A)</code> is equivalent to
     *           invoking <code>A.getContainedPrimitive().add(B)</code>".
     *
     * @see #getContainedPrimitives
     */
    public Set/*<Primitive>*/ getContainingPrimitives();

    /**
     * Returns the set of complexes which contains this primitive. A <code>Primitive</code> may
     * be in several {@linkplain Complex complexes}. This association may not be navigable in this
     * direction (from primitive to complex), depending on the implementation.
     *
     * @return The set of complexex which contains this primitive.
     * @UML association complex
     *
     * @revisit Does it means that <code>Primitive</code> can't be immutable, since
     *          adding this primitive to a complex will change this set?
     */
    public Set/*<Complex>*/ getComplexes();

    /**
     * Returns the orientable primitives associated with this primitive. Each <code>Primitive</code>
     * of dimension 1 or 2 is associated to two {@linkplain OrientablePrimitive orientable primitives},
     * one for each possible orientation. For curves and surfaces, there are exactly two orientable
     * primitives for each geometric object. For the positive orientation, the
     * {@linkplain OrientablePrimitive orientable primitive} shall be the corresponding
     * {@linkplain Curve curve} or {@linkplain Surface surface}.
     *
     * @return The orientable primitives as an array of length 2, or <code>null</code> if none.
     * @UML association proxy
     *
     * @see OrientablePrimitive#getPrimitive
     *
     * @revisit Should we use the plural form for the method names?
     */
    public OrientablePrimitive[] getProxy();

//    public org.opengis.spatialschema.topology.primitive.TP_Primitive topology[];
//    public org.opengis.spatialschema.geometry.complex.GM_Composite composite[];
}
