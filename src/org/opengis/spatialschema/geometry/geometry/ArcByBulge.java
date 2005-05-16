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
package org.opengis.spatialschema.geometry.geometry;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Equivalents to the {@link Arc}, except the bulge representation is maintained.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @since GeoAPI 1.0
 *
 * @see GeometryFactory#createArcByBulge
 */
@UML(identifier="GM_ArcByBulge", specification=ISO_19107)
public interface ArcByBulge extends ArcStringByBulge {
    /**
     * Recast as a base {@linkplain Arc arc}.
     *
     * @return This arc by bulge as a base {@linkplain Arc arc}.
     */
/// public Arc asArcString();
}
