/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cs;


/**
 * A one-dimensional coordinate system containing a single time axis, used to describe the
 * temporal position of a point in the specified time units from a specified time origin.
 * A <code>TemporalCS</code> shall have one {@linkplain #getAxis axis association}.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CRS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.sc.TemporalCRS Temporal}
 * </TD></TR></TABLE>
 *
 * @UML abstract CS_TemporalCS
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface TemporalCS extends CoordinateSystem {
}
