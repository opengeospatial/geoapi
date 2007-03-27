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

// OpenGIS direct dependencies
import static org.opengis.annotation.Specification.ISO_19123;

import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.Point;


/**
 * A {@linkplain GeometryValuePair geometry-value pair} that has a {@linkplain Point point}
 * as the value of its geometry attribute.
 *
 * @author Wim Koolhoven
 */
@UML(identifier="CV_PointValuePair", specification=ISO_19123)
public interface PointValuePair extends GeometryValuePair {
    /**
     * The point that is a member of this <var>point</var>-<var>value</var> pair.
     *
     * @todo Clash in return type!
     */
//  @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
//  Point getGeometry();
}
