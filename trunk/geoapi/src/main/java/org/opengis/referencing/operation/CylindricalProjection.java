/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;


/**
 * Base interface for cylindrical map projections.
 *
 * <p>&nbsp;</p>
 * <p align="center"><img src="../doc-files/CylindricalProjection.png"></p>
 *
 * @departure extension
 *   This interface is not part of ISO specification. It has been added in GeoAPI at user
 *   request, in order to provide a way to known the kind of map projection.
 *
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 *
 * @see org.opengis.referencing.crs.ProjectedCRS
 * @see <A HREF="http://mathworld.wolfram.com/CylindricalProjection.html">Cylindrical projection on MathWorld</A>
 */
public interface CylindricalProjection extends Projection {
}
