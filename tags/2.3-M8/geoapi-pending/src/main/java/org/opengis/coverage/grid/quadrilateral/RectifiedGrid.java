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
package org.opengis.coverage.grid.quadrilateral;

import java.util.List;
import org.opengis.referencing.operation.Conversion;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A modified copy of {@link org.opengis.coverage.grid.RectifiedGrid} which does not inherit
 * {@link org.opengis.coverage.grid.Grid}. In addition, conversion methods have been replaced
 * by a conversion object.
 *
 * @issue http://jira.codehaus.org/browse/GEO-82
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @author  Alexander Petkov
 */
@UML(identifier="CV_RectifiedGrid", specification=ISO_19123)
public interface RectifiedGrid extends RectifiableGrid {
    /**
     * Returns the origin of the rectified grid in an external coordinate reference system.
     */
    @UML(identifier="origin", obligation=MANDATORY, specification=ISO_19123)
    DirectPosition getOrigin();

    /**
     * Returns the offset vectors that determine the grid spacing in each direction. The vectors
     * are defined in terms of the external coordinate reference system.
     */
    @UML(identifier="offsetVectors", obligation=MANDATORY, specification=ISO_19123)
    List<double[]> getOffsetVectors();

    /**
     * {@inheritDoc}
     * In the context of the {@code RectifiedGrid} type, the {@linkplain CoordinateReferenceSystem
     * coordinate reference system} attribute inherited from {@linkplain GridPositioning grid positioning}
     * shall be derived from the Coordinate Reference System association of the origin.
     */
    @Extension
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * The conversion defined by this object is an affine transformation
     * defined by the origin and offset vectors attributes.
     */
    @Extension
    Conversion getConversion();

     /**
      * The inverseConversion defined by this object is an affine transformation
      * defined by the origin and offset vectors attributes.
      */
    @Extension
    Conversion getInverseConversion();
}
