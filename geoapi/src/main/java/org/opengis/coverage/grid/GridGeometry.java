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
package org.opengis.coverage.grid;

import org.opengis.referencing.datum.PixelInCell;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Describes the geometry and georeferencing information of the grid coverage.
 * The grid range attribute determines the valid grid coordinates and allows
 * for calculation of grid size. A grid coverage may or may not have georeferencing.
 *
 * <P>&nbsp;</P>
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
 *   <TR><TD>
 *     <P align="justify"><STRONG>WARNING: THIS CLASS WILL CHANGE.</STRONG> Current API is derived from OGC
 *     <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</A>.
 *     We plan to replace it by new interfaces derived from ISO 19123 (<CITE>Schema for coverage geometry
 *     and functions</CITE>). Current interfaces should be considered as legacy and are included in this
 *     distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much
 *     compatibility as possible, but no migration plan has been determined yet.</P>
 *   </TD></TR>
 * </TABLE>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 */
@UML(identifier="CV_GridGeometry", specification=OGC_01004)
public interface GridGeometry {
    /**
     * The valid coordinate range of a grid coverage.
     * The lowest valid grid coordinate is often (but not always) zero.
     * A grid with 512 cells typically have a minimum coordinate of 0 and maximum of 512,
     * with 511 as the highest valid index.
     *
     * @return The valid coordinate range of a grid coverage.
     */
    @UML(identifier="gridRange", obligation=MANDATORY, specification=OGC_01004)
    GridRange getGridRange();

    /**
     * Returns the conversion from grid coordinates to real world earth coordinates.
     * The transform is often an affine transform. The coordinate reference system
     * of the real world coordinates is given by the
     * {@link org.opengis.coverage.Coverage#getCoordinateReferenceSystem} method
     * and maps to {@linkplain PixelInCell#CELL_CENTER pixel center}.
     *
     * @return The conversion from grid coordinates to
     *         {@linkplain org.opengis.coverage.Coverage#getCoordinateReferenceSystem
     *         real world earth coordinates}.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="gridToCoordinateSystem", obligation=MANDATORY, specification=OGC_01004)
    MathTransform getGridToCRS();
}
