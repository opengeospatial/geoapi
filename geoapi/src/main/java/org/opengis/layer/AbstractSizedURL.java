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
package org.opengis.layer;


/**
 * Extends the {@code AbstractURL} by adding a width and height for the data retrievable
 * from the linkage.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
public interface AbstractSizedURL extends AbstractURL {
    /**
     * Provides the expected width of the response data.
     *
     * @return the expected width.
     */
    int getWidth();

    /**
     * Provides the expected height of the response data.
     *
     * @return the expected height.
     */
    int getHeight();
}
