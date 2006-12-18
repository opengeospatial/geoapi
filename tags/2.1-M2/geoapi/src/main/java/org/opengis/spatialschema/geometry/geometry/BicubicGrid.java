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

// J2SE dependencies
import java.util.List;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain GriddedSurface gridded surface} that uses cubic polynomial splines as the
 * horizontal and vertical curves. The initial tangents for the splines are often replaced
 * by an extra pair of rows (and columns) of control points.
 * <p>
 * The horizontal and vertical curves require initial and final tangent vectors for a complete
 * definition. These values are supplied by the four methods defined in this interface.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_BicubicGrid", specification=ISO_19107)
public interface BicubicGrid extends GriddedSurface {
    /**
     * Returns the initial tangent vectors.
     */
    @UML(identifier="horiVectorAtStart", obligation=MANDATORY, specification=ISO_19107)
    List<double[]> getHorizontalVectorAtStart();

    /**
     * Returns the initial tangent vectors.
     */
    @UML(identifier="horiVectorAtEnd", obligation=MANDATORY, specification=ISO_19107)
    List<double[]> getHorizontalVectorAtEnd();

    /**
     * Returns the initial tangent vectors.
     */
    @UML(identifier="vertVectorAtStart", obligation=MANDATORY, specification=ISO_19107)
    List<double[]> getVerticalVectorAtStart();

    /**
     * Returns the initial tangent vectors.
     */
    @UML(identifier="vertVectorAtEnd", obligation=MANDATORY, specification=ISO_19107)
    List<double[]> getVerticalVectorAtEnd();
}
