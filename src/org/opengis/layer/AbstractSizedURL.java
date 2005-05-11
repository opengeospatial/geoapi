/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.layer;


/**
 * Extends the {@code AbstractURL} by adding a width and height for the data retrievable
 * from the linkage.
 *
 * @author ISO 19128
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since 1.1
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
