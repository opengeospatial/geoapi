/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.cs;


/**
 * A two- or three-dimensional coordinate system with straight axes that are not necessarily
 * orthogonal. An <code>ObliqueCartesianCS</code> shall have two or three
 * {@linkplain #getAxis axis associations}.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CRS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.crs.crs.EngineeringCRS Engineering},
 *   {@link org.opengis.crs.crs.ImageCRS       Image}
 * </TD></TR></TABLE>
 *
 * @UML abstract CS_ObliqueCartesianCS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see CartesianCS
 */
public interface ObliqueCartesianCS extends CoordinateSystem {
}
