/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cs;


/**
 * A 1-, 2-, or 3-dimensional coordinate system. Gives the position of points relative to
 * orthogonal straight axes in the 2- and 3-dimensional cases. In the 1-dimensional case,
 * it contains a single straight coordinate axis. In the multi-dimensional case, all axes
 * shall have the same length unit of measure. A <code>CartesianCS</code> shall have one,
 * two, or three {@linkplain #getAxis axis associations}.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CRS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.sc.GeocentricCRS  Geocentric},
 *   {@link org.opengis.sc.ProjectedCRS   Projected},
 *   {@link org.opengis.sc.EngineeringCRS Engineering},
 *   {@link org.opengis.sc.ImageCRS       Image}
 * </TD></TR></TABLE>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see ObliqueCartesianCS
 *
 * @revisit This interface sounds like a special case of {@link ObliqueCartesianCS}.
 *          Then, why it doesn't extends <code>ObliqueCartesianCS</code>? It would
 *          help to clarify the semantic of {@link org.opengis.sc.ImageCRS} (no need
 *          for separated <code>getCartesianCS()</code> and <code>getObliqueCartesianCS()</code>
 *          methods).
 */
public interface CartesianCS extends CoordinateSystem {
}
