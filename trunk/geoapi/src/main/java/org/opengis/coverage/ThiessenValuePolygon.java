/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

import java.util.Set;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A value from a {@linkplain ThiessenPolygonCoverage Thiessen polygon coverage}.
 * Individual Thiessen value polygons may be generated during the evaluation of a
 * Thiessen polygon coverage, and need not to be persistent.
 *
 * @author Alessio Fabiani
 * @author Martin Desruisseaux
 */
@UML(identifier="CV_ThiessenValuePolygon", specification=ISO_19123)
public interface ThiessenValuePolygon extends ValueObject {
    /**
     * Returns the <var>point</var>-<var>value</var> pair at the polygon centre.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<? extends PointValuePair> getControlValues();

    /**
     * Returns the geometry of the Thiessen polygon centred on the {@linkplain PointValuePair
     * point-value pair} identified by the {@linkplain #getControlValues control values}.
     *
     * @todo The ISO 19123 specification returns a {@link org.opengis.geometry.coordinate.Polygon}.
     *       We will have some trouble here, since {@code Polygon} is not a
     *       {@link org.opengis.geometry.Geometry}.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject getGeometry();  
}
