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

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.complex.CompositeSurface;


/**
 * A surface and an orientation inherited from {@link OrientablePrimitive}. If the orientation is
 * positive, then the <code>OrientableSurface</code> is a {@linkplain Surface surface}. If the
 * orientation is negative, then the <code>OrientableSurface</code> is a reference to a
 * {@linkplain Surface surface} with an upNormal that reverses the direction for this
 * <code>OrientableSurface</code>, the sense of "the top of the surface".
 *  
 * @UML type GM_OrientableSurface
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface OrientableSurface extends OrientablePrimitive {
    /**
     * Returns the set of circular sequences of {@linkplain OrientableCurve orientable curve} that
     * limit the extent of this <code>OrientableSurface</code>. These curves shall be organized
     * into one circular sequence of curves for each boundary component of the
     * <code>OrientableSurface</code>. In cases where "exterior" boundary is not
     * well defined, all the rings of the {@linkplain SurfaceBoundary surface boundary}
     * shall be listed as "interior".
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> The concept of exterior boundary for a surface is really only
     * valid in a 2-dimensional plane. A bounded cylinder has two boundary components, neither
     * of which can logically be classified as its exterior. Thus, in 3 dimensions, there is no
     * valid definition of exterior that covers all cases.
     * </font></blockquote>
     *
     * @return The sets of positions on the boundary.
     * @UML operation boundary
     */
/// public SurfaceBoundary getBoundary();

    /**
     * Returns the owner of this orientable surface, or <code>null</code> if none.
     *
     * @return The owner of this orientable surface, or <code>null</code> if none.
     * @UML association composite
     *
     * @revisit I'm not sure to interpret correctly the ISO specification.
     *          Sound like ISO returns an array (or a sequence) here.
     */
    public CompositeSurface getComposite();
}
