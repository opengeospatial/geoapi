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
 * A three-dimensional coordinate system consisting of a {@linkplain PolarCS polar coordinate
 * system} extended by a straight coordinate axis perpendicular to the plane spanned by the
 * polar coordinate system. A <code>CylindricalCS</code> shall have three
 * {@linkplain #getAxis axis associations}.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CRS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.crs.crs.EngineeringCRS Engineering}
 * </TD></TR></TABLE>
 *
 * @UML abstract CS_CylindricalCS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see PolarCS
 */
public interface CylindricalCS extends CoordinateSystem {
}
