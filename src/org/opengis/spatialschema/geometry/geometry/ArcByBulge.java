/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

/**
 * Equivalents to the {@link Arc}, except the bulge representation is maintained.
 *
 * @UML abstract GM_ArcByBulge
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see GeometryFactory#createArcByBulge
 */
public interface ArcByBulge extends ArcStringByBulge {
    /**
     * Recast as a base {@linkplain Arc arc}.
     *
     * @return This arc by bulge as a base {@linkplain Arc arc}.
     */
/// public Arc asArcString();
}
