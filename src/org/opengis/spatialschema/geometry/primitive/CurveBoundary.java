/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The boundary of {@linkplain Curve curves}.
 * A <code>CurveBoundary</code> contains two {@linkplain Point point} references
 * ({@linkplain #getStartPoint start point} and {@linkplain #getEndPoint end point}).
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @since 1.0
 */
@UML (identifier="GM_CurveBoundary", specification=ISO_19107)
public interface CurveBoundary extends PrimitiveBoundary {
    /**
     * Returns the start point.
     *
     * @see #getEndPoint
     */
    @UML (identifier="startPoint", obligation=MANDATORY, specification=ISO_19107)
    public Point getStartPoint();

    /**
     * Returns the end point.
     *
     * @see #getStartPoint
     */
    @UML (identifier="endPoint", obligation=MANDATORY, specification=ISO_19107)
    public Point getEndPoint();
}
