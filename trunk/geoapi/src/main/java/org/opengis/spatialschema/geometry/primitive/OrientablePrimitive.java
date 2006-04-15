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
package org.opengis.spatialschema.geometry.primitive;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Primitives that can be mirrored into new geometric objects in terms of their internal local
 * coordinate systems (manifold charts). For curves, the orientation reflects the direction in
 * which the curve is traversed, that is, the sense of its parameterization. When used as boundary
 * curves, the surface being bounded is to the "left" of the oriented curve. For surfaces, the
 * orientation reflects from which direction the local coordinate system can be viewed as right
 * handed, the "top" or the surface being the direction of a completing <var>z</var>-axis that
 * would form a right-handed system. When used as a boundary surface, the bounded solid is "below"
 * the surface. The orientation of points and solids has no immediate geometric interpretation in
 * 3-dimensional space.
 * <p>
 * {@code OrientablePrimitive} objects are essentially references to geometric primitives
 * that carry an "orientation" reversal flag (either "+" or "-") that determines whether this
 * primitive agrees or disagrees with the orientation of the referenced object.
 *
 * <blockquote><font size=2>
 * <strong>NOTE:</strong> There are several reasons for subclassing the “positive” primitives
 * under the orientable primitives. First is a matter of the semantics of subclassing. Subclassing
 * is assumed to be a “is type of” hierarchy. In the view used, the “positive” primitive is simply
 * the orientable one with the positive orientation. If the opposite view were taken, and orientable
 * primitives were subclassed under the “positive” primitive, then by subclassing logic, the
 * “negative” primitive would have to hold the same sort of geometric description that the
 * “positive” primitive does. The only viable solution would be to separate “negative” primitives
 * under the geometric root as being some sort of reference to their opposite. This adds a great
 * deal of complexity to the subclassing tree. To minimize the number of objects and to bypass this
 * logical complexity, positively oriented primitives are self-referential (are instances of the
 * corresponding primitive subtype) while negatively oriented primitives are not.
 * </font></blockquote>
 *  
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_OrientablePrimitive", specification=ISO_19107)
public interface OrientablePrimitive extends Primitive {
    /**
     * Determines which of the two possible orientations this object represents.
     *
     * @return +1 for a positive orientation, or -1 for a negative orientation.
     */
    @UML(identifier="orientation", obligation=MANDATORY, specification=ISO_19107)
    public int getOrientation();

    /**
     * Returns the primitive associated with this {@code OrientablePrimitive}.
     * Each {@linkplain Primitive primitive} of dimension 1 or 2 is associated to two
     * {@code OrientablePrimitive}s, one for each possible orientation.
     * For curves and surfaces, there are exactly two orientable primitives
     * for each geometric object.
     *
     * @return The primitive, never {@code null}.
     *
     * @see Primitive#getProxy
     */
    @UML(identifier="primitive", obligation=MANDATORY, specification=ISO_19107)
    public Primitive getPrimitive();
}
