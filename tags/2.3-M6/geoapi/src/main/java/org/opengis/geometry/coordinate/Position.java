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

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Point;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A type consisting of either a {@linkplain DirectPosition direct position} or of a
 * {@linkplain Point point} from which a {@linkplain DirectPosition direct position}
 * shall be obtained. The use of this data type allows the identification of a position
 * either directly as a coordinate (variant direct) or indirectly as a {@linkplain Point point}
 * (variant indirect).
 *
 * @departure constraint
 *   ISO 19107 defines <code>Position</code> as a <code>union</code> of
 *   <code>DirectPosition</code> and <code>Point</code> but unions are not allowed in Java.
 *   GeoAPI defines <code>Position</code> as the base interface of both types so the two
 *   conditional accessor methods, <code>getPoint()</code> and <code>getDirectPosition()</code>,
 *   can be replaced by an <code>instanceof</code> check. However, the
 *   <code>getDirectPosition()</code> has been retained with different semantics, conceptually
 *   returning a <code>DirectPosition</code> at the same location. The conditionality has also
 *   been changed to mandatory since all three types conceptually have a well defined location.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @issue http://jira.codehaus.org/browse/GEO-87
 */
@UML(identifier="GM_Position", specification=ISO_19107)
public interface Position {
    /**
     * Returns the direct position. This method shall never returns {@code null}, but may returns
     * {@code this} if invoked on an object which is already a {@code DirectPosition} instance.
     *
     * @return The direct position (may be {@code this}).
     *
     * @since GeoAPI 2.2
     */
    @UML(identifier="direct", obligation=CONDITIONAL, specification=ISO_19107)
    DirectPosition getDirectPosition();
}
