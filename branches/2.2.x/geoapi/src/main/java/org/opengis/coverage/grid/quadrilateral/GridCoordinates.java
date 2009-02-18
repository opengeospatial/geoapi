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

import org.opengis.annotation.Extension;


/**
 * Proposed extension of ISO {@link org.opengis.coverage.grid.GridCoordinates}.
 *
 * @issue http://jira.codehaus.org/browse/GEO-82
 *
 * @author  Alexander Petkov
 */
@Extension
public interface GridCoordinates extends org.opengis.coverage.grid.GridCoordinates {
    /**
     * This is a compromise method which loads the values of this GridCoordinates implementation
     * into the array provided by the user.
     * Use of this method should be encouraged by those desiring to access the grid coordinates as an array.
     */
    @Extension
    void loadCoordinateValues(int[] vals);
}
