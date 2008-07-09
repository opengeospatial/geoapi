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

import org.opengis.geometry.primitive.Surface;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain GeometryValuePair geometry-value pair} that has a {@linkplain Surface surface}
 * as the value of its geometry attribute.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_SurfaceValuePair", specification=ISO_19123)
public interface SurfaceValuePair extends GeometryValuePair {
    /**
     * The domain object that is a member of this <var>geometry</var>-<var>value</var> pair.
     *
     * @return The geometry member of the pair.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject<Surface> getGeometry();
}
