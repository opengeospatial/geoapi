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
package org.opengis.geometry.coordinate;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Equivalents to the {@link Arc}, except the bulge representation is maintained.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
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
    Arc asArcString();
}
