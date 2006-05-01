/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;


/**
 * Specifies the range of valid coordinates for each dimension of the coverage.
 * For example this data type is used to access a block of grid coverage data values.
 *
 * @UML datatype CV_GridRange
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 */
public interface GridRange {
    /**
     * The valid minimum inclusive grid coordinate.
     * The sequence contains a minimum value for each dimension of the grid coverage.
     * The lowest valid grid coordinate is zero.
     *
     * @return The valid minimum inclusive grid coordinate.
     * @UML mandatory lo
     */
    int[] getLower();

    /**
     * The valid maximum exclusive grid coordinate.
     * The sequence contains a maximum value for each dimension of the grid coverage.
     *
     * @return The valid maximum exclusive grid coordinate.
     * @UML mandatory hi
     */
    int[] getUpper();
}
