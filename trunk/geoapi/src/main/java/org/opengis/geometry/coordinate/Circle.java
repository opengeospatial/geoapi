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
package org.opengis.spatialschema.geometry.geometry;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Same as an {@linkplain Arc arc}, but closed to form a full circle.
 * The {@linkplain #getStartAngle start} and {@linkplain #getEndAngle end bearing}
 * are equal and shall be the bearing for the first {@linkplain #getControlPoints
 * control point} listed.
 *
 * This still requires at least 3 distinct non-co-linear points to be unambiguously
 * defined. The arc is simply extended until the first point is encountered.
 *  
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_Circle", specification=ISO_19107)
public interface Circle extends Arc {
}
