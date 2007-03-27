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

// OpenGIS direct dependencies
import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19107;

import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Point;


/**
 * A type consisting of either a {@linkplain DirectPosition direct position} or of a
 * {@linkplain Point point} from which a {@linkplain DirectPosition direct position}
 * shall be obtained. The use of this data type allows the identification of a position
 * either directly as a coordinate (variant direct) or indirectly as a {@linkplain Point point}
 * (variant indirect).
 * <ul>
 *   <li>In the variant direct case, uses {@link #getPosition} method.</li>
 *   <li>In the variant indirect case, cast this position to a {@link Point}.</li>
 * </ul>
 *  
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_Position", specification=ISO_19107)
public interface Position {
    /**
     * Returns the direct position.
     *
     * @return The direct position.
     */
    @UML(identifier="direct", obligation=CONDITIONAL, specification=ISO_19107)
    public DirectPosition getPosition();
}
