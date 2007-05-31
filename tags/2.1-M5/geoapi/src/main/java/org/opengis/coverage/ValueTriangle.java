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

import java.util.List;
import java.util.Set;
import org.opengis.coverage.ValueObject;
import org.opengis.coverage.grid.GridPointValuePair;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.coordinate.Triangle;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain ValueObject value object} that consists of three {@linkplain PointValuePair
 * point-value pairs} where the points are non-collinear. Value triangles are used for interpolation
 * of a coverage.
 *
 * @author Alessio Fabiani
 * @author Martin Desruisseaux
 */
@UML(identifier="CV_ValueTriangle", specification=ISO_19123)
public interface ValueTriangle extends ValueObject {
    /**
     * Returns the triangle that defines the relative position of the three
     * {@linkplain PointValuePair point-value pairs} at its vertices.
     *
     * @todo The returns type in ISO-19123 is {@link Triangle}.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject getGeometry();

    /**
     * Returns the three <var>point</var>-<var>value</var> pairs at the vertices of this
     * value triangle.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<PointValuePair> getControlValues();

    /**
     * Returns the barycentric coordinates of the specified position as a sequence of numbers.
     *
     * @todo Why not returning an other {@link DirectPosition}?
     */
    @UML(identifier="point", obligation=MANDATORY, specification=ISO_19123)
    double[] point(DirectPosition p);
}
