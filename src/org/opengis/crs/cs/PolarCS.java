/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.crs.cs;


/**
 * A two-dimensional coordinate system in which position is specified by the distance from the
 * origin and the angle between the line from the origin to a point and a reference direction.
 * A <code>PolarCS</code> shall have two {@linkplain #getAxis axis associations}.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CRS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.sc.EngineeringCRS Engineering}
 * </TD></TR></TABLE>
 *
 * @UML abstract CS_PolarCS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see CylindricalCS
 */
public interface PolarCS extends CoordinateSystem {
}
