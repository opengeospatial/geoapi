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

import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specifies the range of valid coordinates for each dimension of the coverage.
 * For example this data type is used to access a block of grid coverage data values.
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
 *
 * @deprecated Replaced by {@link GridEnvelope}.
 */
@Deprecated
@UML(identifier="CV_GridRange", specification=OGC_01004)
public interface GridRange extends GridEnvelope {
    /**
     * Returns the valid minimum inclusive grid coordinate.
     * The sequence contains a minimum value for each dimension of the grid coverage.
     *
     * @since GeoAPI 2.1
     *
     * @deprecated Renamed as {@link #getLow()}.
     */
    @Deprecated
    @UML(identifier="lo", obligation=MANDATORY, specification=OGC_01004)
    GridCoordinates getLower();

    /**
     * Returns the valid maximum exclusive grid coordinate.
     * The sequence contains a maximum value for each dimension of the grid coverage.
     *
     * @since GeoAPI 2.1
     *
     * @deprecated Replaced as {@link #getHigh()} with 1 added to all ordinate values.
     */
    @Deprecated
    @UML(identifier="hi", obligation=MANDATORY, specification=OGC_01004)
    GridCoordinates getUpper();

    /**
     * Returns the valid minimum inclusive grid
     * coordinate along the specified dimension.
     *
     * @deprecated Renamed as {@link #getLow(int)}.
     */
    @Extension
    @Deprecated
    int getLower(int dimension);

    /**
     * Returns the valid maximum exclusive grid
     * coordinate along the specified dimension.
     *
     * @deprecated Renamed as {@link #getHigh(int)} <strong>+ 1</strong>.
     */
    @Extension
    @Deprecated
    int getUpper(int dimension);

    /**
     * Returns the number of integer grid coordinates along the specified dimension.
     * This is equals to {@code getUpper(dimension)-getLower(dimension)}.
     *
     * @deprecated Renamed as {@link #getSpan(int)}.
     */
    @Extension
    @Deprecated
    int getLength(int dimension);
}
